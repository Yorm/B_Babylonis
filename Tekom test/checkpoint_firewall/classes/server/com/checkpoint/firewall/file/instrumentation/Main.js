

function Configuration() {

	var result=snmpWalk({"portIndex" : "1.3.6.1.2.1.2.2.1.1"});

	for (var index in result) {
		if(!shouldRun()) {
			return undefined;
		}
		if (parseInt(index) < 1000) {
			createElement({"Name" : "EthernetInterfaces",
						"DisplayName" : "Ethernet Interfaces",
						"ElementType" : "FirewallEthernetGrouping",//
						"CustomClassName" : "UIM_FirewallEthernetGrouping",
						"Parent" : ""});
			updateElement("EthernetInterfaces", {"Diagramm" : true});

			var uniqName = "Interface_"+result[index].portIndex;
			var displayName = uniqName;		
			
			createElement({"Name" : uniqName,
						"DisplayName" : displayName,
						"ElementType" : "FirewallEthernetPort",
						"CustomClassName" : "UIM_FirewallEthernetPort",
						"Parent" : "EthernetInterfaces"});
			updateElement(uniqName, {"Diagramm" : true});
			
			
		}
	}
	
	
	createElement({"Name" : "FanSpeed",
		"DisplayName" : "FanSpeed",
		"ElementType" : "FirewallFanGrouping",
		"CustomClassName" : "UIM_FirewallFanGrouping",
		"Parent" : ""});
	updateElement("FanSpeed", {"Diagramm" : true});
	
	var result=snmpWalk({"fanIndex" : "1.3.6.1.4.1.2620.1.6.7.8.2.1.1"});
	for (var index in result) {
		if(!shouldRun()) {
			return undefined;
		}

		var uniqName = "Sensor_"+result[index].fanIndex;
		var displayName = uniqName;		
		
		createElement({"Name" : uniqName,
					"DisplayName" : displayName,
					"ElementType" : "FirewallFanSpeed",
					"CustomClassName" : "UIM_FirewallFanSpeed",
					"Parent" : "FanSpeed"});
		updateElement(uniqName, {"Diagramm" : true});		
		
	}
	
	{
		createElement({"Name" : "Routes",
			"DisplayName" : "Routes",
			"ElementType" : "FirewallRouteGrouping",
			"CustomClassName" : "UIM_FirewallRouteGrouping",
			"Parent" : ""});
		updateElement("Routes", {"Diagramm" : true});
		
		//TODO узнавать количество роутов 
		result=snmpWalk({"routIndex" : "1.3.6.1.4.1.2620.1.6.6.1.1"});
		for (var i in result) {
			if(!shouldRun()) {
				return undefined;
			}
			
			var uniqName = "Route_"+i.substring(0,1);
			var displayName = uniqName;		
			createElement({"Name" : uniqName,
						"DisplayName" : displayName,
						"ElementType" : "FirewallRoute",
						"CustomClassName" : "UIM_FirewallRoutes",
						"Parent" : "Routes"});
			updateElement(uniqName, {"Diagramm" : true});
		}
	}
		
	
	
	var doNotDeleteList = [];

	return doNotDeleteList;
}


function ethernetPortAttributes() {

	var result = snmpWalk({"index" : "1.3.6.1.2.1.2.2.1.1"});
	
	var inOctets,
	 	inUcastPkts,
	 	inNUcastPkts,
	 	inDiscards,
	 	inErrors,
	 	inUnknownProtos,
	 	outOctets,
	 	outUcastPkts,
	 	outNUcastPkts,
	 	outDiscards,
	 	outErrors,
	 	physicalAddress,
	 	lastChange;
	
	for (var i in result) {
		if(!shouldRun()) {
			return undefined;
		}

		inOctets = snmpGet("1.3.6.1.2.1.2.2.1.10." + i);
		inUcastPkts = snmpGet("1.3.6.1.2.1.2.2.1.11." + i);
		inNUcastPkts = snmpGet("1.3.6.1.2.1.2.2.1.12." + i);
		inDiscards = snmpGet("1.3.6.1.2.1.2.2.1.13." + i);
		inErrors = snmpGet("1.3.6.1.2.1.2.2.1.14." + i);
		inUnknownProtos = snmpGet("1.3.6.1.2.1.2.2.1.15." + i);
		outOctets = snmpGet("1.3.6.1.2.1.2.2.1.16." + i);
		outUcastPkts = snmpGet("1.3.6.1.2.1.2.2.1.17." + i);
		outNUcastPkts = snmpGet("1.3.6.1.2.1.2.2.1.18." + i);
		outDiscards = snmpGet("1.3.6.1.2.1.2.2.1.19." + i);
		outErrors = snmpGet("1.3.6.1.2.1.2.2.1.20." + i);
	 	physicalAddress= snmpGet("1.3.6.1.2.1.2.2.1.6." + i);
	 	lastChange=snmpGet("1.3.6.1.2.1.2.2.1.9." + i);
		
		updateElement("Interface_"+i, {"Alias" : result[i].index,//
											"Description" : 	snmpGet("1.3.6.1.2.1.2.2.1.2." + i),
											"TrafficType" : 	snmpGet("1.3.6.1.2.1.2.2.1.3." + i),//
											"MTU" : 			snmpGet("1.3.6.1.2.1.2.2.1.4." + i),
											"PortSpeed" :		snmpGet("1.3.6.1.2.1.2.2.1.5." + i),
											"PhysicalAddress" :(physicalAddress==null)?0:physicalAddress,
											"AdminStatus" :  	snmpGet("1.3.6.1.2.1.2.2.1.7." + i),
											"OperStatus" : 		snmpGet("1.3.6.1.2.1.2.2.1.8." + i),
											"LastChange" : 		(lastChange==null)?0:lastChange,//
											//Wrong Type (should be Counter32): Gauge32: 0
											//Все что дальше - возвращает null 
											"InOctets" : 		(inOctets==null)?0:inOctets,
											"InUcastPkts" : 	(inUcastPkts==null)?0:inUcastPkts,
											"InNUcastPkts" : 	(inNUcastPkts==null)?0:inNUcastPkts,//
											"InDiscards" :  	(inDiscards==null)?0:inDiscards,
											"InErrors" : 		(inErrors==null)?0:inErrors,
											"InUnknownProtos" : (inUnknownProtos==null)?0:inUnknownProtos,
											"OutOctets" : 		(outOctets==null)?0:outOctets,
											"OutUcastPkts" : 	(outUcastPkts==null)?0:outUcastPkts,
											"OutNUcastPkts" : 	(outNUcastPkts==null)?0:outNUcastPkts,//
											"OutDiscards" : 	(outDiscards==null)?0:outDiscards,
											"OutErrors" : 		(outErrors==null)?0:outErrors,
											//
											"OutQLen" : snmpGet("1.3.6.1.2.1.2.2.1.21." + i)//
											//22
											});
	}
}

function Attributes() {
	
	ethernetPortAttributes();
	
	var result=snmpWalk({"fanIndex" : "1.3.6.1.4.1.2620.1.6.7.8.2.1.1"});
	for (var i in result) {
		if(!shouldRun()) {
			return undefined;
		}

		updateElement("Sensor_"+i.substring(0,1), {"FanIndex" : i,
								"FanName" :snmpGet(".1.3.6.1.4.1.2620.1.6.7.8.2.1.2."+i),
								"FanValue" :snmpGet("1.3.6.1.4.1.2620.1.6.7.8.2.1.3."+i),//
								"FanUnit" :snmpGet("1.3.6.1.4.1.2620.1.6.7.8.2.1.4."+i),
								"FanType" :snmpGet("1.3.6.1.4.1.2620.1.6.7.8.2.1.5."+i),
								"FanStatus" :snmpGet("1.3.6.1.4.1.2620.1.6.7.8.2.1.6."+i)
		});
		
	}
	result=snmpWalk({"routIndex" : "1.3.6.1.4.1.2620.1.6.6.1.1"});
	for (var i in result) {
		if(!shouldRun()) {
			return undefined;
		}
		
		updateElement("Route_"+i.substring(0,1), {"RoutingIndex" : i,//
									"RoutingDest" : 	snmpGet("1.3.6.1.4.1.2620.1.6.6.1.2."+i),
									"RoutingMask" : 	snmpGet("1.3.6.1.4.1.2620.1.6.6.1.3."+i),//
									"RoutingGatweway" : snmpGet("1.3.6.1.4.1.2620.1.6.6.1.4."+i),
									"RoutingIntrfName" :snmpGet("1.3.6.1.4.1.2620.1.6.6.1.5."+i)
									});
	}

	return "Success";
}

function Alarm() {

	return "Success";
}
function Performance() {

	return "Success";
}


function TrapParser(trapId, varbinds, alarmId) {

	return "success";
}


function ErrorHandler(functionName, params, exception) {
	logDebug("Handling Error on " + functionName);
	// TODO Update how you handle a failure in the different Poll Cycles
	if (functionName == "Configuration") {
		// TODO Update Configuration Error rHandling

	} else if (functionName == "Attributes") {
		// TODO Update Attributes Error Handling

	} else if (functionName == "Alarm") {
		// TODO Update Alarm Error Handling

	} else if (functionName == "Performance") {
		// TODO Update Performance Error Handling

	} else if (functionName == "trapParser") {
		// TODO Update TrapParsing Error Handling
		// var varbindArray = trapArray(params[0]);
		// var trapId = params[0].get("trapId");
		// var alarmId = params[0].get("alarmId");
	}
	return "success";
}

//----------------------------------
//addResyncClass	
//limit the number of classes that will be reconciled
//----------------------------------
function addResyncClass(className){
	manager.addResyncClass(className);
}

// --------------
// Get Instrument Directory path
// --------------
function getInstrumentDir() {
	return manager.getInstrumentDir();

}
// --------------
// Check to see if agent should be running
// --------------
function shouldRun() {
	return manager.shouldRun("");

}

// ---------------
// Trap Paser don't touch
// ---------------
function trapParser(params) {
	var varbindArray = trapArray(params);
	var trapId = params.get("trapId") + "";
	var alarmId = params.get("alarmId") + "";
	return TrapParser(trapId, varbindArray, alarmId);
}
function trapArray(params) {
	var trapBinding = new Array();
	var indexes = new Array();
	var trapBinds = params.get("trapBindVars");
	indexes = trapBinds.toArray();
	for ( var index in indexes) {
		if (!shouldRun()) {
			return undefined;
		}
		hashMap = trapBinds.get(index);
		var tmp = hashMap.keySet().toArray();
		trapBinding[Number(index) + 1] = {
			"value" : hashMap.get(tmp[0]),
			"oid" : tmp[0] + ""
		};
	}
	return trapBinding;

}

function trapOidArray(params) {
	var trapBinding = new Array();
	var indexes = new Array();
	var trapBinds = params.get("trapBindVars");
	indexes = trapBinds.toArray();
	for ( var index in indexes) {
		if (!shouldRun()) {
			return undefined;
		}
		hashMap = trapBinds.get(index);
		var tmp = hashMap.keySet().toArray();
		trapBinding[tmp[0]] = hashMap.get(tmp[0]);
	}
	return trapBinding;

}
// -----------------
// Load a JSON File
// Create elements from the JSON
// ------------------
function createElementsFromFile(file) {
	var result = loadJSONFile(file);
	for (index in result) {
		createElement(result[index]);
	}
}

// -----------------
// Load a JSON File
// -----------------
function loadJSONFile(file) {
	var src = readFile(file);
	var tmp = {};
	eval("tmp=" + src + ";");
	return tmp;
}

// -----------
// Read a file into a string
// -----------
function readFile(file) {
	return manager.readFile(file) + "";
}
// -----------------
// Converts a file to an array
// -----------------
function arrayNewLineFile(file) {
	var src = readFile(file);
	return src.split("\n");
}

// -----------------
// Description:Updates attributes of an elements
// converts it to java hashmap and calls
// manager updateElements
// -----------------
function updateElement(name, json) {
	var hashmap = new java.util.HashMap();
	for (key in json) {
		hashmap.put(key, json[key]);
		manager.logInfo(key + " :" + json[key]);
	}
	manager.updateElement(name, hashmap);

}
// -----------------
// Description: Creates an Element from json object
// converts it to java hashmap and calls
// manager createElements
// returns: instanceID
// -----------------
function createElement(json) {
	var hashmap = new java.util.HashMap();
	for (key in json) {

		hashmap.put(key, json[key]);
		manager.logInfo(key + " :" + json[key]);
	}
	return manager.createElement(hashmap);

}

// ---------------
// Description: Send Alarms using a json object
// converts it to java Hashmap and call manager
// to update
//
// ---------------
function sendAlarm(json) {
	var hashmap = new java.util.HashMap();
	if (json.DisplayName == undefined && json.Name != undefined) {
		json.DisplayName = json.Name;
	}
	for (key in json) {
		switch(key) {
		case "ProbableCause":
		case "AlertType":
		case "PerceivedSeverity":
		case "State":
			hashmap.put(key, manager.getInt(json[key] + ""));
			break;
		default: 
			hashmap.put(key, json[key]);	
		}
		manager.logDebug(key + " :" + json[key]);
	}
	loadDefaults(hashmap);
	manager.sendAlarm(hashmap);
}

function loadDefaults(hashmap) {
	if(!hashmap.containsKey("UserResponsible")) {
		hashmap.put("UserResponsible", "Device");
	}
	if(!hashmap.containsKey("Clearable")) {
		hashmap.put("Clearable", java.lang.Boolean("false"));
	}
	
}

function snmpGet(oid) {

	return manager.snmpGet(oid);
}

/**
 * Execute snmp get next operation
 * 
 * @param oid
 * @returns
 */
function snmpGetNext(oid) {
	return manager.snmpGetNext(oid);
}

/**
 * Execute snmp set operation
 * 
 * @param oid
 * @param value
 * @returns
 */
function snmpSet(oid, value) {
	return manager.snmpSet(oid, value);
}

// ---------------
// Walks oids
// returns a 2d array where the second part of
// 2d array is json accessable
// ---------------
function snmpWalk(fields) {
	var oids = {};
	var size = 0;
	for (field in fields) {
		size++;
	}
	for (field in fields) {
		if (!shouldRun()) {
			return undefined;
		}
		var walkOid = fields[field];
		var map = manager.snmpWalk(walkOid);
		var myArray = map.keySet().toArray();
		for ( var index in myArray) {
			if (!shouldRun()) {
				return undefined;
			}

			var oid = myArray[index] + "";

			var index = oid.substring(walkOid.length);
			index = index.replace(/^\./, "");
			if (size == 1) {
				if (fields[field].prototype == "String") {
					var reg = /^(\d+)\.(.*)/;
					var test = index.match(reg);
					if (test) {
						field = test[1];
						index = test[2];
					}
				}
			}
			if (oids[index] === undefined) {
				oids[index] = {};
			}
			if (index == "") {
				oids[field] = map.get(oid);
			} else {
				oids[index][field] = map.get(oid);
			}

		}

	}
	return oids;
}
// ------------------
// Get the specified property
// -----------------
function getProperty(name, property) {
	return manager.getProperty(name, property);
}
// ------------------
// Delete an element by name
//
// ------------------
function deleteElement(element) {
	manager.deleteElement(element);
}

// --------------
// Logging functions
// ---------------
function logInfo(message) {
	manager.logInfo(message);
}

function logDebug(message) {
	manager.logDebug(message);
}
function logWarn(message) {
	manager.logWarn(message);
}
function logError(message) {
	manager.logError(message);
}
function logFatal(message) {
	manager.logFatal(message);
}
// --------------
// Passed a result of a walk and converts it to JSON
// and puts it in the log
// --------------
function logWalk(result) {
	logWarn(manager.getJSON(result));
}

function logJSON(json) {
	logWarn(manager.getJSON(json));
}
