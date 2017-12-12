package com.checkpoint.firewall.file.instrumentation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.wst.jsdt.debug.rhino.debugger.RhinoDebugger;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.UniqueTag;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.TimeTicks;
import com.harris.netboss.buildit.server.mgmt.AgentManagementServiceImpl;
import com.harris.netboss.buildit.server.shared.BuildITAgentException;
import com.harris.netboss.common.provider.AlertPropertyNameConstants;
import com.harris.netboss.common.provider.PropertySetter;
import com.harris.netboss.common.provider.ScopedAlertAccessor;
import com.harris.netboss.common.schema.InstanceProperty;
import com.harris.netboss.common.schema.MetadataClass;
import com.harris.netboss.common.schema.MetadataProperty;
import com.harris.netboss.common.service.ClassService;
import com.harris.netboss.server.shared.AgentConstants;
import com.harris.netboss.server.shared.SharedAgentUtil;
import com.harris.netboss.server.shared.instrumentation.AgentLogger;
import com.harris.netboss.server.shared.instrumentation.CustomTask;
import com.harris.netboss.server.shared.instrumentation.DefaultAlarmHandler;
import com.harris.netboss.server.shared.instrumentation.DefaultAttributeHandler;
import com.harris.netboss.server.shared.instrumentation.DefaultConfigHandler;
import com.harris.netboss.server.shared.instrumentation.DefaultInstrumentationManager;
import com.harris.netboss.server.shared.instrumentation.Sleeper;

/**
 * InstrumentationManager required by the XT Agent Runtime system. This class
 * acts as a relay to the JavaScript engine, passing invokes, and
 * synchronizations.
 */
public class InstrumentationManager extends DefaultInstrumentationManager
		implements CustomTask {

	private static final String PART_COMPONENT = "PartComponent";

	private static final String GROUP_COMPONENT = "GroupComponent";

	private static final String UIM_LOGICAL_COMPONENT = "UIM_LogicalComponent";

	final protected static String PERF_POLLING_FREQ = "PerformancePollingFrequency";

	final protected static String PERF_POLLING_TIME = "PerformancePollingTime";

	final protected static String PERF_POLLING_TIME_OLD = "PerformancePollingTimeOLD";

	private class AlarmHandler extends DefaultAlarmHandler {
		public AlarmHandler() throws BuildITAgentException {
			super(getInsAgentDomainName(), getAgentType());
		}

		@Override
		public void pollAllAlarms() {
			if (systemHandler.checkSystem()) {
				super.pollAllAlarms();
				getAllAlarms();
				final Object result = executeFunction("Alarm", new Object[0]);
				if (result != null) {
					reconcileAlarms();
				}
				allAlarmProperties.clear();
			}
		}
	}

	/**
	 * Pass through attribute handler that will invoke a JavaScript function.
	 */
	private class AttributeHandler extends DefaultAttributeHandler {
		public AttributeHandler() throws BuildITAgentException {
			super(getInsAgentDomainName(), getAgentType(), getPropertySetter());
		}

		@Override
		public Map<String, Object> pollAttrs() {
			return pollAttrs("attribute");
		}

		@Override
		public Map<String, Object> pollAttrs(final String type) {
			if (systemHandler.checkSystem()) {
				final Map<String, Object> resultMap = super.pollAttrs(type);
				executeFunction("Attributes", new Object[0]);

				try {
					Long systemUpTime = (Long) resultMap
							.get("1.3.6.1.2.1.1.3.0");

					if (systemUpTime != null) {
						final TimeTicks timeTicks = new TimeTicks(systemUpTime);
						getPropertySetter().setProperty(
								getSystemHandler().getUimNetworkElementId(),
								"SystemUpTimeTxt", timeTicks.toString());
					}

					HashMap<String, Object> properties = new HashMap<String, Object>();
					updateElement("", properties);
				} catch (Exception e) {
					logger.log(e);
				}
				return resultMap;
			}
			return null;
		}
	}

	/**
	 * Pass through configuration handler that will invoke a JavaScript
	 * function.
	 */
	private class ConfigHandler extends DefaultConfigHandler {
		public ConfigHandler(final InstrumentationManager manager)
				throws BuildITAgentException {
			super(getInsAgentDomainName(), getAgentType());
		}

		@Override
		public void pollConfig() {
			this.pollConfig(true);
		}

		@Override
		public void pollConfig(final boolean resyncAlerts) {
			String classMethod = "InstrumentationManager :: pollConfig()";
			if (systemHandler.checkSystem()) {
				super.pollConfig(resyncAlerts);
				getAllInventory();
				final Object result = executeFunction("Configuration",
						new Object[0]);
				if (result != null) {
					String name = ((Scriptable) result).getClassName();
					Set<Long> doNotDeleteList = new HashSet<Long>();
					boolean doReconcile = true;
					Object[] ignoreObjects = new Object[0];
					boolean isNativeArray = false;

					if (name.equals("String")) {
						doReconcile = !(result.toString().toLowerCase()
								.equals("false")
								|| result.toString().equals("0") || result
								.toString().toLowerCase().equals("no"));
					} else if (name.equals("Boolean")) {
						doReconcile = (Boolean) ((ScriptableObject) result)
								.getDefaultValue(Boolean.class);
					} else if (name.equals("Array")) {
						ignoreObjects = ((NativeArray) result).getAllIds();
						isNativeArray = true;
					} else if (name.equals("Object")) {
						ignoreObjects = ((NativeObject) result).getAllIds();
					}
					for (Object key : ignoreObjects) {
						name = "";
						if (isNativeArray) {
							Object value = ((NativeArray) result).get(key);
							name = value.toString();
						} else {
							name = key.toString();
						}
						Long topId = allInventory.get(name);
						if (topId != null) {
							logger.log("Adding " + name
									+ " and children to do not delete list",
									classMethod, AgentLogger.DEBUG);
							doNotDeleteList.add(topId);
							doNotDeleteList.addAll(getAllChildren(topId));
						}
					}
					if (doReconcile) {
						reconcileInventory(doNotDeleteList);
					}
				}
			}
		}
	}

	private static class NetworkElement {

		private final String name;

		public NetworkElement(final String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

	public class NetworkElementHierarchyBuilder {

		private final Map<String, List<NetworkElement>> elements = new HashMap<String, List<NetworkElement>>();

		private final Map<String, String> childToParent = new HashMap<String, String>();

		public void addElement(final String parent, final NetworkElement element) {
			childToParent.put(element.getName(), parent);
			List<NetworkElement> existingList = elements.get(parent);
			if (existingList == null) {
				elements.put(parent, new ArrayList<NetworkElement>());
				existingList = elements.get(parent);
			}
			existingList.add(element);
		}

		public void clear() {
			elements.clear();
			childToParent.clear();
		}

		public NetworkElement[] orderedElements() {
			final String classMethod = "NetworkElementHierarchyBuilder :: orderedElements()";

			final SortedMap<Integer, List<NetworkElement>> orderedElementsMap = new TreeMap<Integer, List<NetworkElement>>();

			for (final Map.Entry<String, List<NetworkElement>> entry : elements
					.entrySet()) {

				int nodeLevel = 0;
				boolean searching = true;
				String currentParent = entry.getKey();
				final HashSet<String> path = new HashSet<String>();
				while (searching) {

					if (Constants.NO_PARENT.equals(currentParent)) {
						searching = false;
						break;
					}

					currentParent = childToParent.get(currentParent);
					if (path.contains(currentParent)) {

						logger.log(
								entry.getKey()
										+ " Element Path has a Loop starting with "
										+ currentParent
										+ ". Branch will most likely be broken at the point of the second time element is called",
								classMethod, AgentLogger.ERROR);
						searching = false;
						break;
					}
					path.add(currentParent);
					if (currentParent == null) {
						searching = false;
						break;
					}

					nodeLevel++;
				}

				List<NetworkElement> nodeElements = orderedElementsMap
						.get(nodeLevel);
				if (nodeElements == null) {
					orderedElementsMap.put(nodeLevel,
							new ArrayList<NetworkElement>());
					nodeElements = orderedElementsMap.get(nodeLevel);
				}
				nodeElements.addAll(entry.getValue());
			}

			final ArrayList<NetworkElement> flatElements = new ArrayList<NetworkElement>();
			for (final List<NetworkElement> elementList : orderedElementsMap
					.values()) {
				for (final NetworkElement element : elementList) {
					flatElements.add(element);
				}
			}

			return flatElements.toArray(new NetworkElement[0]);
		}
	}

	/**
	 * This type tells the subsystem to look up the ID for a given name. It is
	 * utilized in the Javascript code when a UIM ID is not available.
	 */
	public static class IdReference {

		private String name;

		public IdReference(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * Function exposed in the Javascript runtime for creating ID references.
	 */
	public static class IdRefFunction extends BaseFunction {

		private static final long serialVersionUID = -7067443118488478601L;

		@Override
		public Object call(Context cx, Scriptable scope, Scriptable thisObj,
				Object[] args) {
			if (args[0] instanceof String)
				return new IdReference((String) args[0]);
			return args[0];
		}
	}

	public static class ToShortFunction extends BaseFunction {
		private static final long serialVersionUID = 1L;

		@Override
		public Object call(Context cx, Scriptable scope, Scriptable thisObj,
				Object[] args) {
			Short value = null;
			try {
				if (args[0] instanceof String) {
					value = Short.valueOf((String) args[0]);
				} else if (args[0] instanceof Double) {
					value = new Short(((Double) args[0]).shortValue());
				} else {
					value = Short.valueOf(args[0].toString());
				}
			} catch (Exception ex) {
				return null;
			}
			return new NativeJavaObject(scope, value, Short.class);
		}
	}

	public class ToLongFunction extends BaseFunction {
		private static final long serialVersionUID = 1L;

		@Override
		public Object call(Context cx, Scriptable scope, Scriptable thisObj,
				Object[] args) {
			Long value = null;
			try {
				if (args[0] instanceof String) {
					value = Long.parseLong((String) args[0]);
				} else if (args[0] instanceof Double) {
					value = ((Double) args[0]).longValue();
				} else {
					value = Long.parseLong(args[0].toString());
				}
			} catch (Exception ex) {
				logger.log(ex, "toLongFunction", AgentLogger.ERROR);
				return null;
			}
			return new NativeJavaObject(scope, value, Long.class);
		}
	}

	public class ToIntegerFunction extends BaseFunction {
		private static final long serialVersionUID = 1L;

		@Override
		public Object call(Context cx, Scriptable scope, Scriptable thisObj,
				Object[] args) {
			Integer value = null;
			try {
				if (args[0] instanceof String) {
					value = Integer.parseInt((String) args[0]);
				} else if (args[0] instanceof Double) {
					value = new Integer(((Double) args[0]).intValue());
				} else {
					value = Integer.parseInt(args[0].toString());
				}
			} catch (Exception ex) {
				logger.log(ex, "toIntegerFunction", AgentLogger.ERROR);
				return null;
			}
			return new NativeJavaObject(scope, value, Integer.class);
		}
	}

	public class ToFloatFunction extends BaseFunction {
		private static final long serialVersionUID = 1L;

		@Override
		public Object call(Context cx, Scriptable scope, Scriptable thisObj,
				Object[] args) {
			Float value = null;
			try {
				if (args[0] instanceof String) {
					value = Float.parseFloat((String) args[0]);
				} else if (args[0] instanceof Double) {
					value = ((Double) args[0]).floatValue();
				} else {
					value = Float.parseFloat(args[0].toString());
				}
			} catch (Exception ex) {
				logger.log(ex, "toFloatFunction", AgentLogger.ERROR);
				return null;
			}
			return new NativeJavaObject(scope, value, Float.class);
		}
	}

	public class ToDateFunction extends BaseFunction {

		private static final long serialVersionUID = 1L;

		@Override
		public Object call(Context cx, Scriptable scope, Scriptable thisObj,
				Object[] args) {
			Date value = new Date();

			if (args.length == 2) {
				try {
					value = new SimpleDateFormat(args[1].toString())
							.parse(args[0].toString());

				} catch (Exception ex) {
					logger.log(ex, "toDateFunction", AgentLogger.ERROR);
					return null;
				}
			}
			return new NativeJavaObject(scope, value, Date.class);
		}
	}

	/**
	 * Relay alarm information to the manager so that the appropriate script can
	 * be invoked.
	 */

	static class SharedContextFactory extends ContextFactory {
		@Override
		protected boolean hasFeature(final Context cx, final int featureIndex) {
			if (featureIndex == Context.FEATURE_DYNAMIC_SCOPE) {
				return true;
			}
			return super.hasFeature(cx, featureIndex);
		}
	}

	public String readFile(String filename) {
		String content = "";
		if (!filename.contains("\\") && !filename.contains("/")) {
			File file = new File(pathToScript);
			filename = file.getParent() + File.separator + filename;
		}
		try {
			final BufferedReader bufferedReader = new BufferedReader(
					new FileReader(filename));

			// the readLine method returns null when there is nothing else to
			// read.
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				content += line + "\n";
			}

			// close the BufferedReader when we're done
			bufferedReader.close();
		} catch (final Exception e) {

		}
		return content;
	}

	protected HashMap<String, String> nameToClass = new HashMap<String, String>();

	protected HashMap<String, HashMap<String, Object>> elements = new HashMap<String, HashMap<String, Object>>();

	NetworkElementHierarchyBuilder builder = new NetworkElementHierarchyBuilder();

	protected String currentScript = "";
	protected String pathToScript;
	protected String instrumentationPath;
	protected Long topLevelID;
	// javascript execution related
	protected Scriptable scope;
	protected Context context;
	protected SharedContextFactory contextFactory;
	protected RhinoDebugger debugger = null;
	protected static final String CIM_COMPUTER_SYSTEM = "CIM_ComputerSystem";
	// cache objects to hold inventory and alarms
	protected final Map<String, Long> allInventory = Collections
			.synchronizedMap(new HashMap<String, Long>());
	protected final Map<String, Long> allInventoryCopy = Collections
			.synchronizedMap(new HashMap<String, Long>());
	protected final Set<Map<String, Object>> allAlarmProperties = new HashSet<Map<String, Object>>();

	private int debugPort = 0;

	private ClassService classService = null;

	private Map<String, Map<String, MetadataProperty>> metaClassMap = new HashMap<String, Map<String, MetadataProperty>>();

	private String topLevelClassName;

	private Set<String> secondaryIps = new HashSet<String>();

	private Set<String> resyncClasses= new HashSet<String>();
	
	public InstrumentationManager(final String insAgentDomainName,
			final PropertySetter propertySetter, final String systemName,
			final String agentHome, final String agentVendor,
			final String agentName, final String agentVersion,
			final String customAgentHome, final String namespacePath,
			final Integer debugPort) throws BuildITAgentException {
		super(insAgentDomainName, propertySetter, systemName, agentHome,
				agentVendor, agentName, agentVersion, customAgentHome,
				namespacePath);
		this.debugPort = debugPort;

	}

	public InstrumentationManager(final String insAgentDomainName,
			final PropertySetter propertySetter, final String systemName,
			final String agentHome, final String agentVendor,
			final String agentName, final String agentVersion,
			final String customAgentHome, final String namespacePath)
			throws BuildITAgentException {
		super(insAgentDomainName, propertySetter, systemName, agentHome,
				agentVendor, agentName, agentVersion, customAgentHome,
				namespacePath);

	}

	/**
	 * @return Returns the agent full path to agent homt plus agent vendor,
	 *         name, version
	 */
	public String buildAgentPath() {
		final StringBuilder agentPath = new StringBuilder();
		if (!customAgentHome.isEmpty() && !"None".equals(customAgentHome)) {
			agentPath.append(customAgentHome);
		} else {
			agentPath.append(agentHome);
		}
		agentPath.append(File.separator);
		agentPath.append(agentVendor);
		agentPath.append(File.separator);
		agentPath.append(agentName);
		agentPath.append(File.separator);
		agentPath.append(agentVersion);
		return agentPath.toString();
	}

	private String capitalizeString(String str) {
		return new StringBuilder().append(Character.toUpperCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	private void populateMetadataClassProperties(String metadataClassName,
			Map<String, MetadataProperty> metadataAllProperties) {
		if (classService != null) {
			MetadataClass metadata = classService
					.getClassByName(metadataClassName);
			Map<String, MetadataProperty> metadataProperties = metadata
					.getPropertiez();
			if (metadataAllProperties == null) {
				metadataAllProperties = metadataProperties;
			} else {
				metadataAllProperties.putAll(metadataProperties);
			}
			if (metadata.getSuperclass() != null
					&& metadata.getSuperclass().getName() != null) {
				populateMetadataClassProperties(metadata.getSuperclass()
						.getName(), metadataAllProperties);
			}
		}
	}

	protected void convertMap(String className, Map<String, Object> properties)
			throws Exception {
		final String classMethod = "InstrumentationManager :: convertMap()";
		HashMap<String, Object> tmp = new HashMap<String, Object>();
		for (String key : properties.keySet()) {
			if (!shouldRun(classMethod)) {
				// Agent is stopping, exception will prevent partial data from
				// being used
				throw new Exception(
						"Agent is stopping, exception will cause data to be thrown away");
			}
			tmp.put(key, convertObject(className, key, properties.get(key)));
		}
		properties.putAll(tmp);
	}

	protected Object convertObject(String className, String property,
			Object value) {
		final String classMethod = "InstrumentationManager :: convertObject()";
		Object result = value;
		Map<String, MetadataProperty> properties = metaClassMap.get(className);
		if (properties == null) {
			properties = new HashMap<String, MetadataProperty>();
			populateMetadataClassProperties(className, properties);
			metaClassMap.put(className, properties);
		}

		MetadataProperty meta = properties.get(property);
		if (meta != null) {
			String name = meta.getType().getName();
			try {
				if (name.contains("String")) {
					result = value + "";
				} else if (name.contains("Date")) {
					// main.js has access to toDate function that would convert
					// a string to date based off of format so there is no need
					// to convert it here
				} else if (name.contains("Boolean")) {
					// java script to java object works well for booleans
				} else if (name.contains("BigInteger")) {

					value = value + "";
					value = value.toString().replaceAll("\\..*", "");
					result = new BigInteger(value.toString());
				} else {

					Double doubleValue = Double.parseDouble(value + "");
					result = doubleValue;
					if (name.contains("Long")) {
						result = doubleValue.longValue();
					} else if (name.contains("Integer")) {
						result = doubleValue.intValue();
					} else if (name.contains("Float")) {
						result = doubleValue.floatValue();
					} else if (name.contains("Short")) {
						result = doubleValue.shortValue();
					} else {
						throw new Exception("Data type not handled");
					}
				}
			} catch (Exception e) {
				logger.log("Unable to convert " + value + " to a " + name,
						classMethod, AgentLogger.ERROR);
				logger.log(e);
				result = null;
			}
		}
		return result;
	}

	private Map<String, Set<String>> shortcutsMap = new HashMap<String, Set<String>>();

	/**
	 * This method provides a way to create UIM_LogicalComponent association
	 * between a parent and a child. IMPORTANT: This will only work with
	 * 3.7.6.2+ versions of XT.
	 * 
	 * @param parentProperties
	 * @param childProperties
	 */
	public void createShortcut(final String parentName, final String childName) {
		Set<String> children = shortcutsMap.get(parentName);
		if (children == null) {
			children = new HashSet<String>();
			shortcutsMap.put(parentName, children);
		}
		children.add(childName);
	}

	private void processShortcuts() {
		for (Entry<String, Set<String>> entry : shortcutsMap.entrySet()) {
			try {
				final Long parentId = allInventory.get(entry.getKey());
				for (String childName : entry.getValue()) {
					final Long childId = allInventory.get(childName);
					getElementService().createAssociation(getNamespacePath(),
							UIM_LOGICAL_COMPONENT, GROUP_COMPONENT, parentId,
							PART_COMPONENT, childId);
				}
			} catch (Exception e) {
				logger.log(e, "processShortcuts", AgentLogger.ERROR);
				logError("Failed to create shortcut: " + entry.getKey() + ":"
						+ entry.getValue());
			}
		}
		shortcutsMap.clear();
	}

	/**
	 * CreateElement creates the instance
	 * 
	 * @param properties
	 *            - must contain parent and InstComp
	 * @return instance id of the new Element
	 */
	public Long createActualElement(final HashMap<String, Object> properties) {
		final String classMethod = "InstrumentationManager :: createInstance()";
		String name = getNetworkElementName(properties);
		if (name == null) {
			return null;
		}
		String className = getNetworkElementClassName(properties);
		if (className == null) {
			return null;
		}
		// Checking InstComp
		String instComp = Constants.INST_COMPONENTS.get(className);
		if (instComp != null) {
			logger.log(
					"Picking up Instantiation component from agent constants.",
					AgentLogger.INFO);
		} else {
			instComp = "UIM_GenericLogicalDeviceInstComp";
			logger.log("Instantiation component for " + className
					+ " is not found, use the default one.", AgentLogger.WARN);
		}

		// updating name to Class hash.
		this.nameToClass.put(name, className);
		if (this.allInventory.containsKey(name)) {
			allInventoryCopy.remove(name);
			return allInventory.get(name);
		}
		if (properties.containsKey(Constants.INSTCOMP)) {
			instComp = properties.get(Constants.INSTCOMP) + "";
			if (instComp.length() == 0) {
				logger.log("Properties contained an empty instcomp entry",
						classMethod, AgentLogger.ERROR);
				return null;
			}
		}

		resolveIdReferences(properties);

		// getting the parent ID
		Long parID = null;
		final Object parTemp = properties.get(Constants.PARENT);
		if (parTemp == null || parTemp.toString().length() == 0) {
			logger.log(
					"Properties did not contain a parent to create the managed object for",
					classMethod, AgentLogger.DEBUG);
			parID = this.systemHandler.getUimNetworkElementId();

		} else {

			if (parTemp.getClass().equals(String.class)) {
				if (nameToClass.containsKey(parTemp)) {
					parID = getInstanceID((String) parTemp,
							nameToClass.get(parTemp));
				}

			} else if (parTemp.getClass().equals(Long.class)) {
				parID = (Long) parTemp;
			}
		}
		// if parentID is null then it sets it to the top level
		Long instanceID = null;
		if (parID == null) {
			parID = this.systemHandler.getUimNetworkElementId();
			logger.log("The parent name " + parTemp
					+ " was not found using top level instance", classMethod,
					AgentLogger.WARN);
		}
		if (parID != null) {
			logger.log("Parent ID  = " + parID, classMethod, AgentLogger.INFO);
			try {
				properties.remove(Constants.PARENT);
				properties.remove(Constants.INSTCOMP);
				// converting property types to the right classes
				convertMap(className, properties);
				properties.put(AgentConstants.CUSTOM_CLASS_NAME, className);
				instanceID = getAgentService().createComponentInstance(
						instComp, parID, properties);
			} catch (final Exception e) {
				logger.log(e);
				logger.log("Dump of properties ", classMethod,
						AgentLogger.ERROR);
				for (final String key : properties.keySet()) {
					logger.log("property: " + key + "=>" + properties.get(key),
							classMethod, AgentLogger.ERROR);
				}
			}
		} else {
			logger.log("Parent ID  Lookup Failed " + parTemp, classMethod,
					AgentLogger.ERROR);
		}
		allInventory.put(name, instanceID);
		return instanceID;
	}

	public void createElement(final HashMap<String, Object> properties) {
		String name = getNetworkElementName(properties);
		String parent = Constants.NO_PARENT;

		if (properties.containsKey(Constants.PARENT)) {
			parent = properties.get(Constants.PARENT) + "";
		}
		builder.addElement(parent, new NetworkElement(name));
		elements.put(name, properties);
		String className = getNetworkElementClassName(properties);
		// updating name to Class hash.
		this.nameToClass.put(name, className);
	}

	private String decapitalizeString(String str) {
		return new StringBuilder().append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * deleteElement deletes the instance
	 * 
	 * @param name
	 *            instance name
	 */
	public void deleteElement(final String name) {
		final String classMethod = "InstrumentationManager :: deleteElement()";
		final Long instID = this.getInstanceID(name, nameToClass.get(name));
		if (instID == null) {
			logger.log("Name " + name + " did not resolve to instance id",
					classMethod, AgentLogger.ERROR);
			return;
		}
		try {
			if (!instID.equals(getSystemHandler().getUimNetworkElementId())) {

				this.getAgentService().deleteModelInstances(instID, true);
			}
		} catch (final Exception e) {
			logger.log(e);
		}
	}

	public void deleteManagedObject(final String id) {
		final Long instID = Long.parseLong(id);
		try {
			if (this.getElementService().doesInstanceExistById(instID)
					&& !instID.equals(getSystemHandler()
							.getUimNetworkElementId())) {

				this.getAgentService().deleteModelInstances(instID, false);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether two indication hashes represent same alert. Indication
	 * hash equality rules:
	 * <ol>
	 * <li><b>CimAlertingManagedElementId</b>s should be equal</li>
	 * <li>If <b>AlertingManagedElementNameExtension</b>s are not null and not
	 * empty, they should be equal</li>
	 * <li>One of the following rules should pass (descending priority):</li>
	 * <ul>
	 * <li>If <b>RemoteId</b>s are not null, they should be equal</li>
	 * <li>If <b>Name</b>s are not null, they should be equal</li>
	 * <li>If both <b>ProbableCause</b>s and <b>ThresholdIdentifier</b>s are not
	 * null, they should be equal respectively</li>
	 * <li>If both <b>ProbableCause</b>s and <b>SpecificCause</b>s are not null,
	 * they should be equal respectively</li>
	 * <li>If any of <b>ThresholdIdentifier</b>s and <b>SpecificCause</b>s are
	 * null, <b>ProbableCause</b>s should be equal</li>
	 * </ul>
	 * </ol>
	 * 
	 */
	private boolean equalAlerts(Map<String, Object> indHash1,
			Map<String, Object> indHash2) {
		if (indHash1
				.get(AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID)
				.equals(indHash2
						.get(AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID))) {

			String thisAmene = (String) indHash1
					.get(AlertPropertyNameConstants.ALERTING_MANAGED_ELEMENT_NAME_EXTENSION);
			String oppositeAmene = (String) indHash2
					.get(AlertPropertyNameConstants.ALERTING_MANAGED_ELEMENT_NAME_EXTENSION);
			if (thisAmene != null && oppositeAmene != null) {
				if (!thisAmene.equals(oppositeAmene)) {
					return false;
				}
			} else if (!(thisAmene == null && oppositeAmene == null)) {
				// one of the Amenes is null the other is not. Empty and Null
				// are okay
				if ((thisAmene == null && !oppositeAmene.isEmpty())
						|| (oppositeAmene == null && !thisAmene.isEmpty())) {
					return false;
				}
			}

			String thisRemoteId = (String) indHash1
					.get(AlertPropertyNameConstants.REMOTE_ID);
			String oppositeRemoteId = (String) indHash2
					.get(AlertPropertyNameConstants.REMOTE_ID);
			if (thisRemoteId != null && oppositeRemoteId != null) {
				if (thisRemoteId.equals(oppositeRemoteId)) {
					return true;
				} else {
					return false;
				}
			}

			String thisName = (String) indHash1
					.get(AlertPropertyNameConstants.NAME);
			String oppositeName = (String) indHash2
					.get(AlertPropertyNameConstants.NAME);
			if (thisName != null && oppositeName != null) {
				if (thisName.equals(oppositeName)) {
					return true;
				} else {
					return false;
				}
			}

			Integer thisProbableCause = (Integer) indHash1
					.get(AlertPropertyNameConstants.PROBABLE_CAUSE);
			Integer oppositeProbableCause = (Integer) indHash2
					.get(AlertPropertyNameConstants.PROBABLE_CAUSE);
			if (thisProbableCause != null && oppositeProbableCause != null) {
				if (thisProbableCause.equals(oppositeProbableCause)) {

					String thisThresholdIdentifier = (String) indHash1
							.get(AlertPropertyNameConstants.THRESHOLD_IDENTIFIER);
					String oppositeThresholdIdentifier = (String) indHash2
							.get(AlertPropertyNameConstants.THRESHOLD_IDENTIFIER);
					if (thisThresholdIdentifier != null
							&& oppositeThresholdIdentifier != null) {
						if (thisThresholdIdentifier
								.equals(oppositeThresholdIdentifier)) {
							return true;
						} else {
							return false;
						}
					}

					String thisSpecificCause = (String) indHash1
							.get(AlertPropertyNameConstants.SPECIFIC_CAUSE);
					String oppositeSpecificCause = (String) indHash2
							.get(AlertPropertyNameConstants.SPECIFIC_CAUSE);
					if (thisSpecificCause != null
							&& oppositeSpecificCause != null) {
						if (thisSpecificCause.equals(oppositeSpecificCause)) {
							return true;
						} else {
							return false;
						}
					}

					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public void load(String scriptName) {
		if (!scriptName.contains("\\") && !scriptName.contains("/")) {
			File file = new File(pathToScript);
			scriptName = file.getParent() + File.separator + scriptName;
		}
		final File file = new File(scriptName);
		if (file.exists()) {

			notifySystemOfMissingScript(scriptName, false);
			this.logError("current script = this " + scriptName
					+ " currently it is at " + file.getName(),
					AgentLogger.DEBUG);

			FileReader fileReader;
			try {
				fileReader = new FileReader(scriptName);
				context.evaluateReader(scope, fileReader, scriptName, 1, null);
			} catch (FileNotFoundException e) {
				System.out.println("file not found" + scriptName);
				logger.log(e);
			} catch (IOException e) {
				System.out.println("IO error " + e.getMessage());
				e.printStackTrace();
				logger.log(e);
			}
		} else {
			System.out.println(scriptName);
			notifySystemOfMissingScript(scriptName, true);
		}
	}

	public synchronized Object executeFunction(final String functionName,
			final Object[] args) {
		final String classMethod = "InstrumentationManager :: executeFunction()";
		boolean editModeState = false;
		final boolean runErrorHandler = true;
		String exceptionStr = "";
		Object result = null;

		for (int i = 0; i < args.length; i++) {
			if (args.length > 0) {
				logger.log(
						"Function parameter " + i + " " + args[i].toString(),
						classMethod, AgentLogger.DEBUG);
			}
		}
		logger.log("Running Function =" + functionName, classMethod,
				AgentLogger.DEBUG);
		do {
			if (!shouldRun(classMethod)) {
				return "";
			}
			builder = new NetworkElementHierarchyBuilder();
			elements = new HashMap<String, HashMap<String, Object>>();
			if (editModeState) {
				// Create context and run.
				scope = new org.mozilla.javascript.ImporterTopLevel(context);

				setupScope(scope);

				FileReader fileReader;
				try {
					fileReader = new FileReader(this.pathToScript);

					@SuppressWarnings("unused")
					final Object exec = context.evaluateReader(scope,
							fileReader, this.pathToScript, 0, null);
				} catch (final FileNotFoundException e) {
					logger.log(e);
				} catch (final IOException e) {
					logger.log(e);
				}
			} else {

				context = contextFactory.enterContext();
			}
			final Object fObj = scope.get(functionName, scope);
			if (!(fObj instanceof Function)) {

				logger.log(functionName + " is undefined or not a function.",
						classMethod, AgentLogger.WARN);
				result = null;
			} else {
				try {
					final Function f = (Function) fObj;

					result = f.call(context, scope, scope, args);
					final String report = Context.toString(result);
					if (report.equals("undefined")) {
						result = null;
					} else {
						result = Context.toObject(result, scope);
					}
					logger.log("function " + functionName + ";return= "
							+ report, classMethod, AgentLogger.DEBUG);
				} catch (final Exception e) {
					logger.log(e);
					result = null;

					if (!functionName.equals("ErrorHandler")) {

						logger.log(
								"Exception occured calling Error Handler function call for  "
										+ functionName, classMethod,
								AgentLogger.WARN);
						exceptionStr = SharedAgentUtil.getStackTraceAsString(e);
					}
				}

			}
			editModeState = isEditMode(functionName);
			processElements();
			builder.clear();
			elements.clear();
			processShortcuts();
			if (result == null) {
				// returned nothing, Calling errorHandler in case there really
				// is an issue
				if (!functionName.equals("ErrorHandler") && runErrorHandler) {
					logger.log("Running Error Handler function call for  "
							+ functionName + " return was null", classMethod,
							AgentLogger.WARN);
					final Object[] newArgs = new Object[3];
					newArgs[0] = functionName;
					newArgs[1] = args;
					newArgs[2] = exceptionStr;
					executeFunction(Constants.ERROR_HANDLER_FUNCTION, newArgs);
				}
			}
		} while (editModeState);

		return result;
	}

	public HashMap<String, Object> executeScript(final String script,
			final HashMap<String, Object> parameters,
			final HashMap<String, Object> results) {
		final String classMethod = getClass().getName() + "::executeScript()";
		logger.log("Loading script =" + script, classMethod, AgentLogger.DEBUG);

		contextFactory = new SharedContextFactory();
		boolean editModeState;

		do {
			if (!shouldRun(classMethod)) {
				return results;
			}

			if (debugPort > 0) {

				try {
					System.out.println("Running Script " + debugPort);
					final String rhino = "transport=socket,suspend=y,address="
							+ debugPort;
					debugger = new RhinoDebugger(rhino);

					getLogger().log("Start Rhino Debugger " + rhino, "",
							AgentLogger.WARN);
					debugger.start();

					contextFactory.addListener(debugger);
				} catch (final Exception e) {
					getLogger().log(e);

				}
			}
			try {
				final File file = new File(script);
				context = contextFactory.enterContext();
				if (file.exists()) {

					notifySystemOfMissingScript(script, false);

					// setting current Script
					currentScript = file.getName();
					getLogger().log(
							"current script = this " + script
									+ " currently it is at " + file.getName(),
							AgentLogger.DEBUG);
					// Create context and run.
					scope = context.initStandardObjects();
					setupScope(scope);

					for (final String key : parameters.keySet()) {
						if (!shouldRun(classMethod)) {
							return results;
						}

						final Object param = Context.javaToJS(
								parameters.get(key), scope);
						ScriptableObject.putProperty(scope, key, param);
					}
					final FileReader fileReader = new FileReader(script);
					@SuppressWarnings("unused")
					final Object exec = context.evaluateReader(scope,
							fileReader, script, 1, null);
					for (final String key : results.keySet()) {
						if (!shouldRun(classMethod)) {
							return results;
						}
						final Object var = scope.get(key, scope);
						Object result = "";
						if (var != null) {
							result = var;
							if (var.getClass().equals(NativeJavaObject.class)) {
								final String tmp = (String) ((NativeJavaObject) var)
										.getDefaultValue(String.class);

								if (tmp.equals("false") || tmp.equals("true")) {
									result = ((NativeJavaObject) var)
											.getDefaultValue(Boolean.class);
								}
							}
						}
						results.put(key, result);
					}

				} else {
					notifySystemOfMissingScript(script, false);
					break;
				}
			} catch (final Exception e) {
				logger.log(e);

			} finally {

			}
			editModeState = isEditMode(script);

		} while (editModeState);
		return results;
	}

	private void setupScope(Scriptable scope) {
		ScriptableObject.putProperty(scope, "idref", new IdRefFunction());
		ToShortFunction shortFunction = new ToShortFunction();
		ScriptableObject.putProperty(scope, "getShort", shortFunction);
		ScriptableObject.putProperty(scope, "toShort", shortFunction);
		ToDateFunction dateFunction = new ToDateFunction();
		ScriptableObject.putProperty(scope, "getDate", dateFunction);
		ScriptableObject.putProperty(scope, "toDate", dateFunction);
		ToLongFunction longFunction = new ToLongFunction();
		ScriptableObject.putProperty(scope, "getLong", longFunction);
		ScriptableObject.putProperty(scope, "toLong", longFunction);
		ToIntegerFunction integerFunction = new ToIntegerFunction();
		ScriptableObject.putProperty(scope, "getInt", integerFunction);
		ScriptableObject.putProperty(scope, "toInt", integerFunction);
		ToFloatFunction floatFunction = new ToFloatFunction();
		ScriptableObject.putProperty(scope, "getFloat", floatFunction);
		ScriptableObject.putProperty(scope, "toFloat", floatFunction);
		final Object out = Context.javaToJS(System.out, scope);
		ScriptableObject.putProperty(scope, "out", out);
		final Object log = Context.javaToJS(this.logger, scope);
		ScriptableObject.putProperty(scope, "logger", log);
		final Object manager = Context.javaToJS(this, scope);
		ScriptableObject.putProperty(scope, "manager", manager);

	}

	/**
	 * Retrieves specified properties for all active alerts on this
	 * NetworkElement and its subclasses
	 * 
	 * @param alertPropertyNames
	 * @return Maps of the active alert properties on the specified CIM managed
	 *         elements.
	 */
	public Map<Long, Map<String, Object>> getAllActiveAlertProperties(
			final Set<String> alertPropertyNames) {
		final String classMethod = "InstrumentationManager :: getAllActiveAlertProperties()";
		final Set<Long> uimIdSet = getInstanceIds(
				AgentConstants.UIM_NETWORK_ELEMENT, true);
		final Map<Long, Map<String, Object>> result = new HashMap<Long, Map<String, Object>>();

		if (uimIdSet != null) {
			for (Long uimId : uimIdSet) {
				if (!shouldRun(classMethod)) {
					break;
				}
				try {
					final ScopedAlertAccessor alertAccessor = AgentManagementServiceImpl
							.getAlertAccessor();
					final Map<Long, Map<String, Object>> uimAlerts = alertAccessor
							.getActiveAlertPropertiesOnManagedElement(uimId,
									false, alertPropertyNames);
					if (uimAlerts.size() > 0) {
						result.putAll(uimAlerts);
						logger.log("Network element [" + uimId
								+ "]. Adding next " + uimAlerts.size()
								+ " XT alerts into result set", classMethod,
								AgentLogger.DEBUG);
					}
				} catch (Exception ex) {
					logger.log("Issues getting alert for id: " + uimId
							+ ". Exception: " + ex.getMessage(),
							AgentLogger.ERROR);
					logger.log(ex, classMethod, AgentLogger.DEBUG);
				}
			}
		}
		logger.log("Total: " + result.size() + " existing XT alerts found",
				classMethod, AgentLogger.DEBUG);
		return result;
	}

	public Set<Map<String, Object>> getAllAlarms() {
		final String compFunc = "InstrumentationManager :: getAllAlarms()";
		allAlarmProperties.clear();
		Set<String> properties = new HashSet<String>();
		properties
				.add(decapitalizeString(AlertPropertyNameConstants.ALERTING_MANAGED_ELEMENT_NAME_EXTENSION));
		properties
				.add(decapitalizeString(AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID));
		properties.add(decapitalizeString(AlertPropertyNameConstants.NAME));
		properties
				.add(decapitalizeString(AlertPropertyNameConstants.PROBABLE_CAUSE));
		properties
				.add(decapitalizeString(AlertPropertyNameConstants.REMOTE_ID));
		properties
				.add(decapitalizeString(AlertPropertyNameConstants.SPECIFIC_CAUSE));
		properties
				.add(decapitalizeString(AlertPropertyNameConstants.THRESHOLD_IDENTIFIER));
		for (Map<String, Object> alarm : getAllActiveAlertProperties(properties)
				.values()) {
			if (!shouldRun(compFunc)) {
				return this.allAlarmProperties;
			}
			String alarmName = alarm
					.get(decapitalizeString(AlertPropertyNameConstants.NAME))
					+ "";

			if (!alarmName.startsWith("Connection Loss")) {
				Map<String, Object> convertedAlarm = new HashMap<String, Object>();
				for (String key : alarm.keySet()) {
					convertedAlarm.put(capitalizeString(key), alarm.get(key));
				}
				this.allAlarmProperties.add(convertedAlarm);
			}
		}

		return null;
	}

	private Set<Long> getAllChildren(Long instId) {
		Set<Long> result = new HashSet<Long>();
		Set<Long> ids = getElementService().getAssociatorIds(instId,
				AgentConstants.UIM_COMPONENT, AgentConstants.GROUP_COMPONENT,
				AgentConstants.PART_COMPONENT);
		if (ids != null) {
			result.addAll(ids);
			for (Long id : ids) {
				Set<Long> childrenIds = getAllChildren(id);
				if (childrenIds != null) {
					result.addAll(childrenIds);
				}
			}
		}
		return result;
	}

	public Map<String, Long> getAllInventory() {
		synchronized (allInventory) {
			allInventory.clear();
			if(resyncClasses.isEmpty()){
				allInventory.putAll(this.getInventory("UIM_ManagedSystemElement"));
			}else{
				for(String resyncClass: resyncClasses){
					//if (!shouldRun(compFunc)) {
					//	break;
					//}
					allInventory.putAll(this.getInventory(resyncClass));
				}
			}
			allInventory
					.putAll(this.getInventory("UIM_StatisticalInformation"));
			synchronized (allInventoryCopy) {
				allInventoryCopy.clear();
				allInventoryCopy.putAll(allInventory);
			}
		}
		return allInventory;

	}

	/**
	 * Debugging method for JavaScript. It tries to convert objects to the wrong
	 * type sometimes
	 * 
	 * @param obj
	 * @return a string saying what object it is.
	 */
	public String getClassName(final Object obj) {

		return obj.getClass().getName();
	}

	/**
	 * retrieves an instanceID for a string;
	 * 
	 * @param name
	 * @param type
	 * @return id of the element being looked up
	 */
	public Long getInstanceID(final String name, String type) {
		final String classMethod = "InstrumentationManager :: getInstanceID()";
		logger.log("Name lookup for " + name, classMethod, AgentLogger.INFO);
		Long id = null;
		if (name == null || name.isEmpty()) {
			id = systemHandler.getUimNetworkElementId();
		} else {
			final HashMap<String, Object> queryProperties = new HashMap<String, Object>();
			queryProperties.put("Name", name);
			final Set<Long> instIDs = getElementService()
					.getInstanceIdsByNamespacePathAndProperties(
							getNamespacePath(), type, queryProperties);
			for (final Long inst : instIDs) {
				if (!shouldRun(classMethod)) {
					return null;
				}
				id = inst;
			}
		}
		return id;
	}

	/**
	 * 
	 * @param parTemp
	 * @return
	 */
	public Long getManagedSystemElementId(final String parTemp) {
		return getInstanceID(parTemp, "UIM_ManagedSystemElement");
	}

	/**
	 * Retrieves the set of instance ids for a given class name.
	 * 
	 * @param uimClassName
	 *            - the name of the class
	 * @param deep
	 *            - whether to get instance ids of the class' subclasses
	 * @return The set of instance ids.
	 */
	public Set<Long> getInstanceIds(final String uimClassName,
			final boolean deep) {
		final String classMethod = "InstrumentationManager :: getInstanceIds()";
		Set<Long> uimIdSet = null;
		try {
			uimIdSet = getElementService()
					.getInstanceIdsByClassNameAndNamespacePath(uimClassName,
							getNamespacePath(), deep);
			if (logger.isLogLevelEnabled(AgentLogger.DEBUG)) {
				StringBuilder logMsg = new StringBuilder("Found ")
						.append(uimIdSet.size()).append(" instances of class ")
						.append(uimClassName);
				logger.log(logMsg.toString(), classMethod, AgentLogger.DEBUG);
			}
		} catch (Exception ex) {
			logger.log("Issues getting uim ids list: " + ex.getMessage(),
					AgentLogger.ERROR);
			logger.log(ex, classMethod, AgentLogger.DEBUG);
		}
		return uimIdSet;
	}

	/**
	 * 
	 * @return Instrumentation Directory location
	 */
	public String getInstrumentDir() {
		return buildAgentPath() + "/instrumentation/";

	}

	/**
	 * Converts a string to an int allowing the Javascript to convert it to int
	 * 
	 * @param convert
	 * @return an int
	 */
	public Integer getInt(final String convert) {
		try {
			return Integer.parseInt(convert);
		} catch (final Exception e) {
			logger.log(e);
		}
		return null;
	}

	/**
	 * Converts a string to an int array allowing the JavaScript to convert it
	 * to int
	 * 
	 * @param convert
	 * @return an int
	 */
	public Integer[] getIntArray(final String convert) {
		final String[] array = convert.split(",");
		final Integer[] intArray = new Integer[array.length];
		try {
			for (int i = 0; i < array.length; i++) {
				intArray[i] = Integer.parseInt(array[i]);
			}

			return intArray;
		} catch (final Exception e) {
			logger.log(e);
		}
		return null;
	}

	public HashMap<String, Long> getInventory(final String uimClass) {
		return getInventory(uimClass, null, null);
	}

	public HashMap<String, Long> getInventory(final String uimClass,
			final String property) {
		return getInventory(uimClass, property, null);
	}

	public HashMap<String, Long> getInventory(final String uimClass,
			String property, final String elementType) {
		final String classMethod = "InstrumentationManager :: getInventory()";
		final HashMap<String, Long> result = new HashMap<String, Long>();
		if (property == null) {
			property = "Name";
		}
		final Set<Long> instIDs = getElementService()
				.getInstanceIdsByClassNameAndNamespacePath(uimClass,
						getNamespacePath(), true);
		for (final Long instID : instIDs) {
			if (!shouldRun(classMethod)) {
				return result;
			}

			final HashSet<String> getProps = new HashSet<String>();
			getProps.add(AgentConstants.ELEMENT_TYPE);
			getProps.add(property);
			final Map<String, Object> properties = getElementService()
					.getInstancePropertiesById(instID, getProps);

			if (properties != null) {
				if (elementType != null) {
					if (properties.get(AgentConstants.ELEMENT_TYPE).toString()
							.equals(elementType)) {
						result.put(properties.get(property).toString(), instID);
					}
				} else {

					result.put(properties.get(property).toString(), instID);
				}
			}
		}
		return result;
	}

	/**
	 * Converts a string to long making it accessible to JavaScript
	 * 
	 * @param convert
	 * @return a long value
	 */
	public Long getLong(final String convert) {
		try {
			return Long.parseLong(convert);
		} catch (final Exception e) {
			logger.log(e);
		}
		return null;
	}

	private String getNetworkElementClassName(
			final HashMap<String, Object> properties) {
		// Checking Class Name
		String className = null;
		if (properties != null) {
			Object customClassNameProperty = properties
					.get(AgentConstants.CUSTOM_CLASS_NAME);
			if (customClassNameProperty != null) {
				className = customClassNameProperty.toString();
				// if class name is a nick name like rack = UIM_GenericRack
				String classShortcut = Constants.CUSTOM_CLASS_NAMES
						.get(className);
				if (classShortcut != null) {
					className = classShortcut;
					properties.put(AgentConstants.CUSTOM_CLASS_NAME, className);
				}
			} else {
				logger.log(
						"Create Element properties  did not contain required "
								+ AgentConstants.CUSTOM_CLASS_NAME + " field",
						AgentLogger.ERROR);
			}
		}
		return className;
	}

	private String getNetworkElementName(
			final HashMap<String, Object> properties) {
		String name = null;
		if (properties != null) {
			// confirm the name of the agent
			Object nameProperty = properties.get(AgentConstants.NAME);
			if (nameProperty == null) {
				logger.log(
						"Create Element properties  did not contain required Name field",
						AgentLogger.ERROR);
			} else {
				// setting the name used to do look ups into the inventory
				name = nameProperty.toString();
			}
		}
		return name;
	}

	public String getPathToScript() {
		return pathToScript;
	}

	public Object getProperty(final String name, final String propertyName) {
		Long instid = this.topLevelID;
		if (!name.equals("")) {
			instid = this.getInstanceID(name, nameToClass.get(name));
		}
		Object result = null;
		if (instid != null) {
			final InstanceProperty instProp = getElementService()
					.getInstanceProperty(instid, propertyName);
			if (instProp != null) {
				result = instProp.getValue();
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object invoke(final String methodName, final Map methodParameters) {
		final LinkedList<Object> param = new LinkedList<Object>();

		param.add(methodParameters);

		Object result = null;
		final HashMap requestData = (HashMap) methodParameters
				.get("requestData");

		if ("setScriptMethod".equals(methodName)) {
			pathToScript = requestData.get("attributeValue").toString();
			setPathToScript(pathToScript);
			return result;
		} else if ("setIpAddress".equals(methodName)) {
			setIpAddress(requestData);
			updateAssociatedIpAddresses();
		} else if ("setSecondaryIp".equals(methodName)) {
			updateAssociatedIpAddresses();
		} else if ("synchronizeAlarms".equals(methodName)) {
			this.getAlarmHandler().pollAllAlarms();
		} else if ("synchronizeAttributes".equals(methodName)) {
			this.getAttributeHandler().pollAttrs();
		} else if ("synchronizeConfiguration".equals(methodName)) {
			this.getConfigHandler().pollConfig();
		} else if ("synchronizePerformance".equals(methodName)) {
			synchronizePerformance();
		} else if ("checkConnectivity".equals(methodName)) {
			result = this.checkConnectivity(methodParameters);
		} else if ("synchronizeDevice".equals(methodName)) {
			this.synchronizeDevice(new HashMap());
		} else {
			// must be in the javascript or in the invoke above
			result = executeFunction(methodName, param.toArray());
			// result was in the javascript
			if (result != null) {
				if (result.getClass().equals(String.class)) {
					if (result.toString().length() == 0) {
						result = new HashMap<String, Object>();
					}
				}
				if (methodName.toLowerCase().contains("trap")) {

					result = new LinkedList();
				}
				return result;
			}
			// method not found, need to try the super class
			return super.invoke(methodName, methodParameters);
		}
		return result;
	}

	private boolean isEditMode(final String script) {
		final String classMethod = "InstrumentationManager :: isEditMode()";
		final String display = getElementService()
				.getInstanceProperty(this.topLevelID,
						AgentConstants.DISPLAY_NAME).getValue().toString();
		final String filename = this.instrumentationPath + File.separator
				+ display + "_edit.lock";
		final File file = new File(filename);
		if (file.exists()) {
			final File fileScript = new File(this.pathToScript);
			final String name = fileScript.getName();
			final String result = readFile(filename);
			if (result.contains(name) && script.contains(name)
					|| result.contains(script)) {
				final long originalModDate = fileScript.lastModified();
				long curModDate = originalModDate;
				while (originalModDate == curModDate) {
					if (!shouldRun(classMethod)) {
						return false;
					}
					curModDate = fileScript.lastModified();
					try {
						Thread.sleep(1000);
					} catch (final InterruptedException e) {
						logger.log(e);
					}
				}
				return true;
			}

		}
		return false;
	}

	/**
	 * methods log* Script Logging methods make it easyier to log messages
	 */

	public void logDebug(final String message) {
		logger.log(message, currentScript, AgentLogger.DEBUG);
	}

	public void logDebug(final String message, final String script) {
		logger.log(message, script, AgentLogger.DEBUG);
	}

	public void logError(final String message) {
		logger.log(message, currentScript, AgentLogger.ERROR);
	}

	public void logError(final String message, final String script) {
		logger.log(message, script, AgentLogger.ERROR);
	}

	public void logFatal(final String message) {
		logger.log(message, currentScript, AgentLogger.FATAL);
	}

	public void logFatal(final String message, final String script) {
		logger.log(message, script, AgentLogger.FATAL);
	}

	public void logInfo(final String message) {
		logger.log(message, currentScript, AgentLogger.INFO);
	}

	public void logInfo(final String message, final String script) {
		logger.log(message, script, AgentLogger.INFO);
	}

	public void logWarn(final String message) {
		logger.log(message, currentScript, AgentLogger.WARN);
	}

	public void logWarn(final String message, final String script) {
		logger.log(message, script, AgentLogger.WARN);
	}

	public void logJSON(Object obj) {
		logWarn(getJSON(obj));
	}

	public String getJSON(Object obj) {
		return getJSON(obj, "");
	}

	/**
	 * getJSON navigates the json object passed it to recreate the string
	 * version of it
	 * 
	 * @param obj
	 * @param pre
	 * @return string version of the json object
	 */
	public String getJSON(Object obj, String pre) {
		StringBuilder sb = new StringBuilder();
		if (obj instanceof NativeObject) {
			NativeObject nativeObj = (NativeObject) obj;
			sb.append("{");
			for (Object key : nativeObj.getAllIds()) {
				Object value = nativeObj.get(key.toString(), nativeObj);
				if (value instanceof UniqueTag
						&& key.toString().matches("\\d+")) {
					value = nativeObj.get(Integer.parseInt(key.toString()),
							nativeObj);
				}
				if (value instanceof NativeJavaObject) {
					value = ((NativeJavaObject) value).unwrap();
				}

				sb.append("\n").append(pre).append("\"").append(key)
						.append("\"").append(":");
				if (value instanceof NativeObject) {
					sb.append(getJSON(value, pre + "\t"));
				} else if (value instanceof String) {
					sb.append("\"").append(value).append("\"");

				} else if (value instanceof UniqueTag) {
					sb.append(((UniqueTag) value).readResolve());
				} else if (value instanceof NativeJavaObject) {
					value = ((NativeJavaObject) value).unwrap();
					sb.append(value.toString());
				} else if (value instanceof NativeArray) {
					sb.append(getJSON(value, pre + "\t"));
				} else {
					sb.append(value);
				}
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n").append(pre).append("}");
		} else if (obj instanceof NativeArray) {
			NativeArray arrayObj = (NativeArray) obj;
			sb.append("[");
			for (Object key : arrayObj.getAllIds()) {
				if (key.toString().equals("length")) {
					continue;
				}
				Object value = arrayObj.get(key);
				if (value instanceof UniqueTag
						&& key.toString().matches("\\d+")) {
					value = arrayObj.get(Integer.parseInt(key.toString()));
				}
				if (value instanceof NativeJavaObject) {
					value = ((NativeJavaObject) value).unwrap();
				}

				if (value instanceof NativeObject) {
					sb.append(getJSON(value, pre + "\t"));
				} else if (value instanceof String) {
					sb.append("\"").append(value).append("\"");
				} else if (value instanceof UniqueTag) {
					sb.append(((UniqueTag) value).readResolve());
				} else if (value instanceof NativeJavaObject) {
					value = ((NativeJavaObject) value).unwrap();
					sb.append(value.toString());
				} else {
					sb.append(value);
				}
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n").append(pre).append("]");
		}
		return sb.toString();
	}

	private void notifySystemOfMissingScript(final String script,
			final boolean raise) {
		// int type, String name, String description,
		// int probCause, int severity, final String message,
		// final boolean raise
		// Magic numbers?
		sendSystemAlarm(4, "Missing Script",
				String.format("%s does not exist", script), 47, 6,
				String.format("%s does not exist", script), raise);
	}

	protected void OnStop() {
		super.onStop();
		try {
			debugger.stop();
			contextFactory.removeListener(debugger);
		} catch (final Exception e) {
			logger.log(e);
		}
		try {

			Context.exit();
		} catch (final Exception e) {
			logger.log(e);
		}
	}

	@Override
	public void performCustomInitialization() {

		try {
			secondaryIps.add("IPAddress");
			updateAssociatedIpAddresses();
			topLevelID = this.getSystemHandler().getUimNetworkElementId();
			this.configHandler = new ConfigHandler(this);
			instanceStore.put(AgentConstants.CONFIG_HANDLER, configHandler);
			this.alarmHandler = new AlarmHandler();
			instanceStore.put(AgentConstants.ALARM_HANDLER, this.alarmHandler);
			this.attributeHandler = new AttributeHandler();
			instanceStore.put(AgentConstants.ATTRIBUTE_HANDLER,
					attributeHandler);
			classService = AgentManagementServiceImpl.getClassService();
			this.instrumentationPath = this.buildAgentPath()
					+ File.separatorChar + "instrumentation"
					+ File.separatorChar;

			pathToScript = getElementService()
					.getInstanceProperty(topLevelID, "Script").getValue()
					.toString();
			this.setPathToScript(pathToScript);
			this.topLevelClassName = getElementService().getInstanceClassName(
					topLevelID);
			executeScript(getPathToScript(), new HashMap<String, Object>(),
					new HashMap<String, Object>());

		} catch (final Exception exc) {
			if (logger != null) {
				logger.log(exc, AgentLogger.FATAL);
			} else {
				System.out.println("test"
						+ SharedAgentUtil.getStackTraceAsString(exc));
			}
			indicationHandler.reportAgentException();
		}
	}

	public void synchronizePerformance() {
		final String compFunc = "InstrumentationManager :: pollAllPerformance()";
		if (systemHandler.checkSystem()) {
			try {
				final Object result = executeFunction("Performance",
						new Object[0]);
				logger.log("Performance Poll returned " + result, compFunc,
						AgentLogger.DEBUG);
			} catch (Exception e) {
				logger.log(e, compFunc, AgentLogger.ERROR);
			}
		}
	}

	public void processElements() {
		final NetworkElement[] elements = builder.orderedElements();
		for (final NetworkElement ne : elements) {
			this.createActualElement(this.elements.get(ne.getName()));
		}
	}

	/**
	 * Method to remove all elements that were not discovered earlier
	 */
	public void reconcileAlarms() {
		final String classMethod = "InstrumentationManager :: reconcileAlarms()";
		logger.log("Clearing " + allAlarmProperties.size() + " alerts",
				classMethod, AgentLogger.INFO);
		for (Map<String, Object> alarm : allAlarmProperties) {
			if (!shouldRun(classMethod)) {
				return;
			}
			this.sendAlarmClear(alarm);
		}
	}

	/**
	 * Method to remove all elements that were not discovered earlier
	 */
	public void reconcileInventory(Set<Long> doNotDeleteList) {
		final String classMethod = "InstrumentationManager :: reconcileInventory()";
		final Map<String, Long> inventory;
		synchronized (allInventoryCopy) {
			inventory = new HashMap<String, Long>(allInventoryCopy);
		}

		for (final String key : inventory.keySet()) {
			if (!shouldRun(classMethod)) {
				return;
			}
			final Long id = inventory.get(key);
			if (this.getElementService().doesInstanceExistById(id)
					&& !id.equals(this.systemHandler.getUimNetworkElementId())
					&& !doNotDeleteList.contains(id)) {
				logger.log("Deleting " + key + " object", classMethod,
						AgentLogger.ERROR);
				this.getAgentService().deleteModelInstances(inventory.get(key),
						true);
				allInventory.remove(key);
			}
		}
	}

	/**
	 * Method to send alarms into the system
	 * 
	 * @param indHash
	 */
	public void sendAlarm(final HashMap<String, Object> indHash) {
		final String classMethod = "InstrumentationManager :: sendAlarm()";
		resolveIdReferences(indHash);
		if (!indHash.containsKey(AlertPropertyNameConstants.STATE)) {
			indHash.put(AlertPropertyNameConstants.STATE, 1);
		}
		if (!indHash.containsKey(AlertPropertyNameConstants.PROBABLE_CAUSE)) {
			indHash.put(AlertPropertyNameConstants.PROBABLE_CAUSE, 1);
		}
		if (!indHash
				.containsKey(AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID)) {
			if (indHash.containsKey("ManagedObject")) {
				final String name = (String) indHash.get("ManagedObject");
				if (name != null) {
					String className = nameToClass.get(name);
					Long instID = null;
					if (className != null) {
						instID = this.getInstanceID(name, className);
					}
					Long cimID = null;
					if (instID == null) {
						logger.log(
								"Lookup of ManagedObject did not return  an instance id",
								classMethod, AgentLogger.WARN);
						indHash.put(
								AlertPropertyNameConstants.ALERTING_MANAGED_ELEMENT_NAME_EXTENSION,
								name);
						cimID = systemHandler
								.getAlertingManagedElementInstanceId();

					} else {
						cimID = getElementService().getCimIdByUimId((instID));
					}
					indHash.put(
							AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID,
							cimID);
					indHash.remove("ManagedObject");
				}
			} else {
				logger.log(
						"Alarm did not contain information to where to send the alarm sending it to top level",
						classMethod, AgentLogger.ERROR);
				indHash.put(
						AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID,
						systemHandler.getAlertingManagedElementInstanceId());
			}
		}

		String amene = (String) indHash
				.get(AlertPropertyNameConstants.ALERTING_MANAGED_ELEMENT_NAME_EXTENSION);

		if (amene != null && amene.isEmpty()) {
			indHash.remove(AlertPropertyNameConstants.ALERTING_MANAGED_ELEMENT_NAME_EXTENSION);
		}

		for (Iterator<Map<String, Object>> i = allAlarmProperties.iterator(); i
				.hasNext();) {
			if (!shouldRun(classMethod)) {
				return;
			}
			Map<String, Object> alarm = i.next();
			if (equalAlerts(alarm, indHash)) {
				this.allAlarmProperties.remove(alarm);
				break;
			}
		}

		this.indicationHandler.sendIndication(indHash);
	}

	public void sendAlarm(final Long alID, final int state,
			final String displayName, final String description,
			final String caption, final int sev, final int pcause,
			final String scause, final int alertType) {
		final HashMap<String, Object> indHash = new HashMap<String, Object>();
		indHash.put(AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID,
				alID);

		indHash.put(AlertPropertyNameConstants.STATE, state);
		indHash.put(AlertPropertyNameConstants.DISPLAY_NAME, displayName);
		indHash.put(AlertPropertyNameConstants.DESCRIPTION, description);
		indHash.put(AlertPropertyNameConstants.CAPTION, caption);

		indHash.put(AlertPropertyNameConstants.PERCEIVED_SEVERITY, sev);
		indHash.put(AlertPropertyNameConstants.PROBABLE_CAUSE, pcause);
		indHash.put(AlertPropertyNameConstants.SPECIFIC_CAUSE, scause);
		indHash.put(AlertPropertyNameConstants.ALERT_TYPE, alertType);

		this.indicationHandler.sendIndication(indHash);

	}

	public void sendAlarm(final String managedObject, final int state,
			final String displayName, final String description,
			final String caption, final int sev, final int pcause,
			final String scause, final int alertType) {

		Long alID = this.getInstanceID(managedObject,
				nameToClass.get(managedObject));
		alID = getElementService().getCimIdByUimId(alID);
		sendAlarm(alID, state, displayName, description, caption, sev, pcause,
				scause, alertType);

	}

	/**
	 * clears alarms during a resync
	 * 
	 * @param al
	 */
	private void sendAlarmClear(Map<String, Object> alarm) {
		logger.log("called", null, AgentLogger.DEBUG);

		final Map<String, Object> indicationMap = new HashMap<String, Object>();
		indicationMap.putAll(alarm);
		indicationMap.put(AlertPropertyNameConstants.STATE, 3);
		indicationHandler.sendIndication(indicationMap);
	}

	private void sendSystemAlarm(final int type, final String name,
			final String description, final int probCause, final int severity,
			final String message, final boolean raise) {
		final String classMethod = "InstrumentationManager :: sendSystemAlarm()";
		logger.log("called", classMethod, AgentLogger.DEBUG);
		logger.log((raise ? "raising" : "clearing") + " " + name + " alarm",
				classMethod, AgentLogger.INFO);

		final Map<String, Object> indicationMap = new HashMap<String, Object>();
		indicationMap.put(AlertPropertyNameConstants.DISPLAY_NAME, name);
		indicationMap.put(
				AlertPropertyNameConstants.CIM_ALERTING_MANAGED_ELEMENT_ID,
				systemHandler.getCimComputerSystemId());
		indicationMap.put(
				AlertPropertyNameConstants.UIM_ALERTING_MANAGED_ELEMENT_ID,
				systemHandler.getUimNetworkElementId());
		indicationMap.put(AlertPropertyNameConstants.PROBABLE_CAUSE, probCause);
		indicationMap.put(AlertPropertyNameConstants.DESCRIPTION, description);
		indicationMap.put(AlertPropertyNameConstants.CAPTION, name);
		indicationMap.put(AlertPropertyNameConstants.STATE, (raise ? 1 : 3));
		indicationMap.put(AlertPropertyNameConstants.ALERT_TYPE, type);
		indicationMap.put(AlertPropertyNameConstants.SPECIFIC_CAUSE, message);
		indicationMap.put(AlertPropertyNameConstants.PERCEIVED_SEVERITY,
				severity);

		indicationHandler.sendIndication(indicationMap);
	}

	/**
	 * @param pathToScript
	 *            the pathToScript to set
	 */
	public void setPathToScript(final String pathToScript) {
		this.pathToScript = pathToScript;
		if (!pathToScript.contains("\\") && !pathToScript.contains("/")) {
			this.pathToScript = instrumentationPath + pathToScript;
		}
	}

	public Object snmpGet(final String oid) {
		final ArrayList<String> oidList = new ArrayList<String>();
		oidList.add(oid);
		final HashMap<String, Object> temp = (HashMap<String, Object>) this
				.getSnmpHandler().snmpGet(oidList);
		for (final String key : temp.keySet()) {
			return temp.get(key);
		}
		return null;
	}

	/**
	 * Execute snmp get next operation
	 * 
	 * @param oid
	 * @return
	 */
	public Object snmpGetNext(final String oid) {
		final HashMap<String, Object> temp = (HashMap<String, Object>) this
				.getSnmpHandler().snmpGetNext(oid);
		return temp == null || temp.isEmpty() ? null : temp.values().iterator()
				.next();
	}

	/**
	 * Execute snmp set operation
	 * 
	 * @param oid
	 * @param value
	 */
	public Object snmpSet(final String oid, final Object value) {
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put(oid, value);
		final HashMap<String, Object> temp = (HashMap<String, Object>) this
				.getSnmpHandler().snmpSet(values);

		return temp == null || temp.isEmpty() ? null : temp.values().iterator()
				.next();
	}

	public Map<String, Object> snmpWalk(final String oid) {
		final Map<String, Object> result = new TreeMap<String, Object>(
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						OID oid1 = new OID(o1);
						OID oid2 = new OID(o2);
						return oid1.compareTo(oid2);
					}
				});
		result.putAll(this.getSnmpHandler().snmpWalk(oid));
		return result;
	}

	public Map<String, Object> snmpWalk(final String[] oids) {
		final Map<String, Object> result = new TreeMap<String, Object>(
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						OID oid1 = new OID(o1);
						OID oid2 = new OID(o2);
						return oid1.compareTo(oid2);
					}
				});
		for (final String oid : oids) {
			result.putAll(snmpWalk(oid));
		}
		return result;
	}

	/**
	 * updateElement updates the instance property
	 * 
	 * @param name
	 *            - name of the instance
	 * @param properties
	 */

	public void updateElement(final String name,
			final HashMap<String, Object> properties) {
		final String classMethod = "InstrumentationManager :: updateElement()";

		Long instID = null;
		String className = nameToClass.get(name);
		if (name.equals("")) {
			instID = this.systemHandler.getUimNetworkElementId();
			className = topLevelClassName;
		} else if (className != null) {
			instID = this.getInstanceID(name, className);
		} else {
			logger.log("ClassName from name " + name + " was null",
					classMethod, AgentLogger.ERROR);
			return;
		}
		if (instID == null) {
			logger.log("Name " + name + " did not resolve to instance id",
					classMethod, AgentLogger.ERROR);
			return;
		}
		try {
			if (className != null) {
				convertMap(className, properties);
			}
			this.getElementService().setInstanceProperties(instID, properties);

		} catch (final Exception e) {
			logger.log(e);
		}
	}

	/**
	 * Search all values in the properties for a type of IdReference. This type
	 * denotes that it needs to be replaced with a ID lookup. This is not very
	 * OO at the moment and can be fixed by allowing all types to be references
	 * then get resolved at usage.
	 * 
	 * @param properties
	 *            The properties to process.
	 */
	private void resolveIdReferences(Map<String, Object> properties) {
		for (Entry<String, Object> entry : properties.entrySet()) {
			if (entry.getValue() instanceof IdReference) {
				String referenceName = ((IdReference) entry.getValue())
						.getName();
				entry.setValue(getInstanceID(referenceName,
						nameToClass.get(properties.get(AgentConstants.NAME))));
			}
		}
	}

	@Override
	public void performCustomTask() {
		final String compFunc = "InstrumentationManager :: performCustomTask()";
		final Sleeper sleeper = (Sleeper) instanceStore.get("CustomSleeper");
		final String performancePollingFrequency = (String) instanceStore
				.get(PERF_POLLING_FREQ);
		final Integer performancePollingTime = (Integer) instanceStore
				.get(PERF_POLLING_TIME);
		final Object tempPollingTimeOLD = instanceStore
				.get(PERF_POLLING_TIME_OLD);
		Integer performancePollingTimeOLD = null;
		if (tempPollingTimeOLD != null) {
			performancePollingTimeOLD = (Integer) instanceStore
					.get(PERF_POLLING_TIME_OLD);
		}
		final Integer firstCustomTask = (Integer) instanceStore
				.get("FirstCustomTask");
		long numSeconds;
		if (firstCustomTask.intValue() == 1) {
			final Integer firstQueryCfg = (Integer) instanceStore
					.get("FirstQueryCfg");
			if (firstQueryCfg.intValue() == 0) {
				if (!systemHandler.getIsOnline()
						&& !systemHandler.checkSystem()) {
					numSeconds = 90;
					logger.log(
							"Device not online, sleep without Polling Performance Counters...",
							compFunc);
				} else {
					instanceStore.put(PERF_POLLING_TIME_OLD,
							performancePollingTime);
					numSeconds = sleeper.computeSleepTime(
							performancePollingTime.intValue(),
							performancePollingFrequency, true);
					// Poll Performance Counters and Sleep when FirstTime
					this.synchronizePerformance();
					instanceStore.put("FirstCustomTask", Integer.valueOf(0));
					logger.log("First CustomTask Completed...", compFunc,
							"INFO");
				}

			} else {
				numSeconds = 90;
				logger.log("Configuration Poll not Complete Sleeping...",
						compFunc);
			}
		} else if (performancePollingFrequency.equals("Never")) {
			// Sleep without Polling Performance Counters
			logger.log("Sleep without Polling Performance Counters...",
					compFunc);
			numSeconds = 90;
		} else if (!performancePollingTime.equals(performancePollingTimeOLD)) {
			// Sleep when Performance Polling Time changed between sleeps
			logger.log("Performance Polling Time changed between sleeps...",
					compFunc);
			instanceStore.put(PERF_POLLING_TIME_OLD, performancePollingTime);
			numSeconds = sleeper.computeSleepTime(performancePollingTime,
					performancePollingFrequency, true);
		} else {
			// Compute new sleep time when waking from sleep
			logger.log("waking from sleep...", compFunc);
			numSeconds = sleeper.computeSleepTime(performancePollingTime,
					performancePollingFrequency, false);

			if (!systemHandler.getIsOnline() && !systemHandler.checkSystem()) {
				numSeconds = 90;
				logger.log(
						"Device not online, sleep without Polling Performance Counters...",
						compFunc);
			} else {
				this.synchronizePerformance();
			}

		}
		// Sleep
		sleeper.setSeconds(numSeconds);
		sleeper.sleep();

	}

	public NativeObject loadSheet(String sheet) {
		Map<String, Map<String, String>> result = guideReader.loadSheet(sheet);
		return convertMapToNative(result);
	}

	@SuppressWarnings("rawtypes")
	public NativeObject convertMapToNative(Map map) {

		NativeObject nobj = new NativeObject();
		for (Object key : map.keySet()) {
			Object obj = map.get(key);
			if (obj instanceof Map) {
				try {
					obj = convertMapToNative((Map) obj);
				} catch (Exception e) {
					logger.log(e, AgentLogger.ERROR);
				}
			}
			nobj.defineProperty(key.toString(), obj, NativeObject.READONLY);
		}

		return nobj;
	}
	
	public void addResyncClass(String className){
		resyncClasses.add(className);
	}
	
	public void addSecondaryIpAttribute(String ip) {
		this.secondaryIps.add(ip);
		updateAssociatedIpAddresses();
	}

	protected void updateAssociatedIpAddresses() {
		final String classMethod = "AbstractSharedEngineInstMgr::updateAssociatedIpAddresses()";

		logger.log(classMethod, "called", AgentLogger.DEBUG);

		final Set<String> associatedIpAddresses = new HashSet<String>();
		for (String attribute : this.secondaryIps) {
			logError("Secondary attribute = " + attribute);
			InstanceProperty standByIPAddress = elementService
					.getInstanceProperty(getSystemHandler()
							.getUimNetworkElementId(), attribute);
			if (standByIPAddress != null) {
				associatedIpAddresses.add((String) standByIPAddress.getValue());
			}

		}

		/* Logging */
		if (associatedIpAddresses.isEmpty()) {
			logger.log(classMethod,
					"AssotiatedIpAddresses is empty. Will clear all IPs",
					AgentLogger.DEBUG);
		} else {
			for (final String ip : associatedIpAddresses) {
				logger.log(classMethod, "ASSOCIATED_IP=" + ip,
						AgentLogger.DEBUG);
			}
		}

		if (associatedIpAddresses != null) {
			try {
				final java.lang.reflect.Method setIpAddresses = getAgentService()
						.getClass().getMethod("setAssociatedIpAddresses",
								String.class, Set.class);
				final Object arglist[] = new Object[2];
				arglist[0] = namespacePath;
				arglist[1] = associatedIpAddresses;
				setIpAddresses.invoke(getAgentService(), arglist);
			} catch (final NoSuchMethodException nme) {
				logger.log(
						classMethod,
						"Current version of XT doesn't support multiple IP address feature for traps",
						AgentLogger.WARN);
				logger.log(nme, classMethod, AgentLogger.DEBUG);
			} catch (final SecurityException se) {
				logger.log(
						classMethod,
						"Current version of XT doesn't support multiple IP address feature for traps",
						AgentLogger.WARN);
				logger.log(se, classMethod, AgentLogger.DEBUG);
			} catch (final IllegalArgumentException e) {
				logger.log(
						classMethod,
						"Current version of XT doesn't support multiple IP address feature for traps",
						AgentLogger.WARN);
				logger.log(e, classMethod, AgentLogger.DEBUG);
			} catch (final IllegalAccessException e) {
				logger.log(
						classMethod,
						"Current version of XT doesn't support multiple IP address feature for traps",
						AgentLogger.WARN);
				logger.log(e, classMethod, AgentLogger.DEBUG);
			} catch (final InvocationTargetException e) {
				logger.log(
						classMethod,
						"Current version of XT doesn't support multiple IP address feature for traps",
						AgentLogger.WARN);
				logger.log(e, classMethod, AgentLogger.DEBUG);
			}
		}
	}

}