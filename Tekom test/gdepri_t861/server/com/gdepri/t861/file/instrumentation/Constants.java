package com.gdepri.t861.file.instrumentation;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Define all constants
 */
public class Constants {

	/** */
	public static final String ELEMENT_TYPE_SUB_NETWORK = "3GPPSubNetwork";

	/**  */
	public static final String NOTIFICATION_CATEGORY_SET = "NotificationCategorySet";

	/**  */
	public static final String NOTIFICATION_AND_ALARM_FILTER = "NotificationAndAlarmFilter";

	/**  */
	public static final String NOTIFICATION_SUBSCRIPTION_TIMEOUT = "NotificationSubscriptionTimeout";

	/**  */
	public static final String SCRIPT_HANDLER = "ScriptHandler";

	/**  */
	public static final String NAMING_SERVICE_IOR_URL = "Naming_Service_IOR_URL";

	/** */
	public static final String ACK_ALERT_OPS_PARAM_ACTOR = "actor";

	/** */
	public static final String ACK_ALERT_OPS_PARAM_ALERT = "alert";

	/** */
	public static final String ACK_ALERT_OPS_PARAM_NAMESPACEPATH = "namespacePath";

	/** */
	public static final String ACK_ALERT_OPS_PARAM_SYSTEMID = "systemId";

	/** */
	public static final String PROPERTY_CREATE_ELEMENTS_ON_ALARMS = "CreateElementsOnAlarms";

	/** */
	public static final String REMOVE_OLD_ELEMENTS = "RemoveOldElements";

	/** */
	public static final String ERROR_HANDLER_FUNCTION = "ErrorHandler";

	public static String PARENT = "Parent";

	public static String INSTCOMP = "InstComp";

	public static String NO_PARENT = "";
	
	public static String MANGED_OBJECT = "ManagedObject";
	
	// @formatter:off
	public static final Map<String, String> ELEMENT_TYPES = mapOf(new Object[] {
			"SLOT", "GenericSlot", "CARD", "GenericCard", "RACK",
			"GenericRack", "SHELF", "GenericShelf", "PORT", "GenericPort", });

	public static final Map<String, String> CUSTOM_CLASS_NAMES = mapOf(new Object[] {
			"SLOT", "UIM_GenericSlot", "CARD", "UIM_GenericCard", "RACK",
			"UIM_GenericRack", "SHELF", "UIM_GenericShelf", "PORT",
			"UIM_GenericPort", "DEVCIE", "UIM_GenericLogicalDevice", "CHASSIS",
			"UIM_Chassis", "INTERFACE", "UIM_GenericInterface" });

	public static final Map<String, String> INST_COMPONENTS = mapOf(new Object[] {
			"SLOT", "UIM_GenericSlotInstComp", "CARD",
			"UIM_GenericCardInstComp", "INTERFACE", "UIM_GenericPortInstComp",
			"CHASSIS", "UIM_GenericRackInstComp", "UIM_GenericChassis",
			"UIM_GenericRackInstComp", "RACK", "UIM_GenericRackInstComp",
			"SHELF", "UIM_GenericShelfInstComp", "PORT",
			"UIM_GenericPortInstComp", "DEVICE",
			"UIM_GenericLogicalDeviceInstComp", "UIM_GenericSlot",
			"UIM_GenericSlotInstComp", "UIM_GenericCard",
			"UIM_GenericCardInstComp", "UIM_GenericRack",
			"UIM_GenericRackInstComp", "UIM_GenericShelf",
			"UIM_GenericShelfInstComp", "UIM_GenericPort",
			"UIM_GenericPortInstComp", "UIM_GenericLogicalDevice",
			"UIM_GenericLogicalDeviceInstComp", "UIM_GenericInterface",
			"UIM_GenericPortInstComp" });

	public static final Map<String, Integer> ALERT_TYPE = mapOf(new Object[] {
			"Other", 1, "Communications Alert", 2, "Quality of Service Alert",
			3, "Processing Error", 4, "Device Alert", 5, "Environmental Alert",
			6, "Model Change", 7, "Security Alert", 8 });

	public static final Map<String, Integer> PERCEIVED_SEVERITY = mapOf(new Object[] {
			"Unknown", 0, "Other", 1, "Information", 2, "Warning", 3, "Minor",
			4, "Major", 5, "Critical", 6, "Fatal", 7 });

	public static final Map<String, Integer> PROBABLE_CAUSE = mapOf(new Object[] {
			"Unknown", 0, "Other", 1, "Adapter/Card Error", 2,
			"Application Subsystem Failure", 3, "Bandwidth Reduced", 4,
			"Connection Establishment Error", 5,
			"Communications Protocol Error", 6,
			"Communications Subsystem Failure", 7,
			"Configuration/Customization Error", 8, "Congestion", 9,
			"Corrupt Data", 10, "CPU Cycles Limit Exceeded", 11,
			"Dataset/Modem Error", 12, "Degraded Signal", 13,
			"DTE-DCE Interface Error", 14, "Enclosure Door Open", 15,
			"Equipment Malfunction", 16, "Excessive Vibration", 17,
			"File Format Error", 18, "Fire Detected", 19, "Flood Detected", 20,
			"Framing Error", 21, "HVAC Problem", 22, "Humidity Unacceptable",
			23, "I/O Device Error", 24, "Input Device Error", 25, "LAN Error",
			26, "Non-Toxic Leak Detected", 27, "Local Node Transmission Error",
			28, "Loss of Frame", 29, "Loss of Signal", 30,
			"Material Supply Exhausted", 31, "Multiplexer Problem", 32,
			"Out of Memory", 33, "Output Device Error", 34,
			"Performance Degraded", 35, "Power Problem", 36,
			"Pressure Unacceptable", 37,
			"Processor Problem (Internal Machine Error)", 38, "Pump Failure",
			39, "Queue Size Exceeded", 40, "Receive Failure", 41,
			"Receiver Failure", 42, "Remote Node Transmission Error", 43,
			"Resource at or Nearing Capacity", 44, "Response Time Excessive",
			45, "Retransmission Rate Excessive", 46, "Software Error", 47,
			"Software Program Abnormally Terminated", 48,
			"Software Program Error (Incorrect Results)", 49,
			"Storage Capacity Problem", 50, "Temperature Unacceptable", 51,
			"Threshold Crossed", 52, "Timing Problem", 53,
			"Toxic Leak Detected", 54, "Transmit Failure", 55,
			"Transmitter Failure", 56, "Underlying Resource Unavailable", 57,
			"Version MisMatch", 58, "Previous Alert Cleared", 59,
			"Login Attempts Failed", 60, "Software Virus Detected", 61,
			"Hardware Security Breached", 62, "Denial of Service Detected", 63,
			"Security Credential MisMatch", 64, "Unauthorized Access", 65,
			"Alarm Received", 66, "Loss of Pointer", 67, "Payload Mismatch",
			68, "Transmission Error", 69, "Excessive Error Rate", 70,
			"Trace Problem", 71, "Element Unavailable", 72, "Element Missing",
			73, "Loss of Multi Frame", 74, "Broadcast Channel Failure", 75,
			"Invalid Message Received", 76, "Routing Failure", 77,
			"Backplane Failure", 78, "Identifier Duplication", 79,
			"Protection Path Failure", 80, "Sync Loss or Mismatch", 81,
			"Terminal Problem", 82, "Real Time Clock Failure", 83,
			"Antenna Failure", 84, "Battery Charging Failure", 85,
			"Disk Failure", 86, "Frequency Hopping Failure", 87,
			"Loss of Redundancy", 88, "Power Supply Failure", 89,
			"Signal Quality Problem", 90, "Battery Discharging", 91,
			"Battery Failure", 92, "Commercial Power Problem", 93,
			"Fan Failure", 94, "Engine Failure", 95, "Sensor Failure", 96,
			"Fuse Failure", 97, "Generator Failure", 98, "Low Battery", 99,
			"Low Fuel", 100, "Low Water", 101, "Explosive Gas", 102,
			"High Winds", 103, "Ice Buildup", 104, "Smoke", 105,
			"Memory Mismatch", 106, "Out of CPU Cycles", 107,
			"Software Environment Problem", 108, "Software Download Failure",
			109, "Element Reinitialized", 110, "Timeout", 111,
			"Logging Problems", 112, "Leak Detected", 113,
			"Protection Mechanism Failure", 114, "Protecting Resource Failure",
			115, "Database Inconsistency", 116, "Authentication Failure", 117,
			"Breach of Confidentiality", 118, "Cable Tamper", 119,
			"Delayed Information", 120, "Duplicate Information", 121,
			"Information Missing", 122, "Information Modification", 123,
			"Information Out of Sequence", 124, "Key Expired", 125,
			"Non-Repudiation Failure", 126, "Out of Hours Activity", 127,
			"Out of Service", 128, "Procedural Error", 129,
			"Unexpected Information", 130 });

	private static final Map<String, String> DATE_FORMAT_REGEXPS = mapOf(new Object[] {
			"^\\d{8}$", "yyyyMMdd", "^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy",
			"^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd",
			"^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy",
			"^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd",
			"^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy",
			"^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy", "^\\d{12}$",
			"yyyyMMddHHmm", "^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm",
			"^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm",
			"^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm",
			"^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm",
			"^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm",
			"^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$",
			"dd MMM yyyy HH:mm",
			"^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$",
			"dd MMMM yyyy HH:mm", "^\\d{14}$", "yyyyMMddHHmmss",
			"^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss",
			"^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$",
			"dd-MM-yyyy HH:mm:ss",
			"^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$",
			"yyyy-MM-dd HH:mm:ss",
			"^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$",
			"MM/dd/yyyy HH:mm:ss",
			"^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$",
			"yyyy/MM/dd HH:mm:ss",
			"^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$",
			"dd MMM yyyy HH:mm:ss",
			"^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$",
			"dd MMMM yyyy HH:mm:ss" });

	// @formatter:on

	/**
	 * Determine SimpleDateFormat pattern matching with the given date string.
	 * Returns null if format is unknown. You can simply extend DateUtil with
	 * more formats if needed.
	 * 
	 * @param dateString
	 *            The date string to determine the SimpleDateFormat pattern for.
	 * @return The matching SimpleDateFormat pattern, or null if format is
	 *         unknown.
	 * @see SimpleDateFormat
	 */
	public static String determineDateFormat(final String dateString) {
		for (final String regexp : DATE_FORMAT_REGEXPS.keySet()) {
			if (dateString.toLowerCase().matches(regexp)) {
				return DATE_FORMAT_REGEXPS.get(regexp);
			}
		}
		return null; // Unknown format.

	}

	/**
	 * Create a static map of name/value pairs without having to create a new
	 * anonymous inner class.
	 * 
	 * @param items
	 *            Array of the name/value pairs. item[0] = name, item[1] =
	 *            value, etc.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <K, V> Map<K, V> mapOf(final Object[] items) {
		assert ((items.length % 2) == 0);
		final HashMap<K, V> map = new HashMap<K, V>();
		for (int index = 0; index < items.length; index += 2) {
			final K key = (K) items[index];
			final V value = (V) items[index + 1];
			map.put(key, value);
		}
		return map;
	}
	public static final String IP_ADDRESS = "IpAddress";
	public static final String MESSAGE_DELIMITER = "MessageDelimiter";
	public static final String PASSWORD = "Password";
	public static final String PORT = "Port";
	public static final String USERNAME = "Username";
	
}
