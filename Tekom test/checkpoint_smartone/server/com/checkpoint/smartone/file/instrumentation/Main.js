
function Configuration() {

	var result=snmpWalk({"portIndex" : "1.3.6.1.2.1.2.2.1.1"});

	for (var index in result) {
		if(!shouldRun()) {
			return undefined;
		}
		if (parseInt(index) < 1000) {
			createElement({"Name" : "EthernetInterfaces",
						"DisplayName" : "Ethernet Interfaces",
						"ElementType" : "SmartOneEthernetGrouping",//
						"CustomClassName" : "UIM_SmartOneEthernetGrouping",
						"Parent" : ""});


			var uniqName = "Interface_"+result[index].portIndex;
			var displayName = uniqName;		
			
			createElement({"Name" : uniqName,
						"DisplayName" : displayName,
						"ElementType" : "SmartOneEthernetPort",
						"CustomClassName" : "UIM_SmartOneEthernetPort",
						"Parent" : "EthernetInterfaces"});

			
			
		}
	}
		
	{
		createElement({"Name" : "Routes",
			"DisplayName" : "Routes",
			"ElementType" : "SmartOneRouteGrouping",
			"CustomClassName" : "UIM_SmartOneRouteGrouping",
			"Parent" : ""});

		
		
		result=snmpWalk({"routIndex" : "1.3.6.1.4.1.2620.1.6.6.1.1"});
		for (var i in result) {
			if(!shouldRun()) {
				return undefined;
			}
			
			var uniqName = "Route_"+i;
			var displayName = uniqName;		
			createElement({"Name" : uniqName,
						"DisplayName" : displayName,
						"ElementType" : "SmartOneRoute",
						"CustomClassName" : "UIM_SmartOneRoutes",
						"Parent" : "Routes"});

		}	
	}

	{
		createElement({"Name" : "Statistic",
			"DisplayName" : "Statistic",
			"ElementType" : "SmartOneStatisticGrouping",
			"CustomClassName" : "UIM_SmartOneStatisticGrouping",
			"Parent" : ""});
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
	 	outErrors;
	
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
		
		updateElement("Interface_"+i, {"Alias" : result[i].index,//
											"Description" : 	snmpGet("1.3.6.1.2.1.2.2.1.2." + i),
											"TrafficType" : 	snmpGet("1.3.6.1.2.1.2.2.1.3." + i),//
											"MTU" : 			snmpGet("1.3.6.1.2.1.2.2.1.4." + i),
											"PortSpeed" :		snmpGet("1.3.6.1.2.1.2.2.1.5." + i),
											"PhysicalAddress" : snmpGet("1.3.6.1.2.1.2.2.1.6." + i),
											"AdminStatus" :  	snmpGet("1.3.6.1.2.1.2.2.1.7." + i),
											"OperStatus" : 		snmpGet("1.3.6.1.2.1.2.2.1.8." + i),
											"LastChange" : 		snmpGet("1.3.6.1.2.1.2.2.1.9." + i),//
											//Wrong Type (should be Counter32): Gauge32: 0
											//return  null 
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
	updateElement(uniqName, {"Diagramm" : true});
	updateElement("Routes", {"Diagramm" : true});
	updateElement("EthernetInterfaces", {"Diagramm" : true});
	updateElement(uniqName, {"Diagramm" : true});
	updateElement("Statistic", {"Diagramm" : true});
	
	
	updateElement("",{"SvnProdName":snmpGet("1.3.6.1.4.1.2620.1.6.1.0")});	
	updateElement("",{"SvnProdVerMajor":snmpGet("1.3.6.1.4.1.2620.1.6.2.0")});	
	updateElement("",{"SvnProdVerMinor":snmpGet("1.3.6.1.4.1.2620.1.6.3.0")});	
	updateElement("",{"SvnVersion":snmpGet("1.3.6.1.4.1.2620.1.6.4.1.0")});	
	updateElement("",{"SvnBuild":snmpGet("1.3.6.1.4.1.2620.1.6.4.2.0")});
	
	updateElement("",{"OsName":snmpGet("1.3.6.1.4.1.2620.1.6.5.1.0")});
	updateElement("",{"OsMajorVer":snmpGet("1.3.6.1.4.1.2620.1.6.5.2.0")});
	updateElement("",{"OsMinorVer":snmpGet("1.3.6.1.4.1.2620.1.6.5.3.0")});
	updateElement("",{"OsVersionLevel":(snmpGet("1.3.6.1.4.1.2620.1.6.4.7.0")==null)?"null":snmpGet("1.3.6.1.4.1.2620.1.6.4.7.0")});
	
	result=snmpWalk({"routIndex" : "1.3.6.1.4.1.2620.1.6.6.1.1"});
	for (var i in result) {
		if(!shouldRun()) {
			return undefined;
		}
		
		updateElement("Route_"+i, {"RoutingIndex" : i,//
									"RoutingDest" : 	snmpGet("1.3.6.1.4.1.2620.1.6.6.1.2."+i+".0"),
									"RoutingMask" : 	snmpGet("1.3.6.1.4.1.2620.1.6.6.1.3."+i+".0"),//
									"RoutingGatweway" : snmpGet("1.3.6.1.4.1.2620.1.6.6.1.4."+i+".0"),
									"RoutingIntrfName" :snmpGet("1.3.6.1.4.1.2620.1.6.6.1.5."+i+".0")
									});
	}
	
	updateElement("",{"MemTotalVirtual":snmpGet("1.3.6.1.4.1.2620.1.6.7.1.1.0")});	
	updateElement("",{"MemActiveVirtual":snmpGet("1.3.6.1.4.1.2620.1.6.7.1.2.0")});	
	updateElement("",{"MemTotalReal":snmpGet("1.3.6.1.4.1.2620.1.6.7.1.3.0")});	
	updateElement("",{"MemActiveReal":snmpGet("1.3.6.1.4.1.2620.1.6.7.1.4.0")});	
	updateElement("",{"MemFreeReal":snmpGet("1.3.6.1.4.1.2620.1.6.7.1.5.0")});
	
	updateElement("",{"ProcUsrTime":snmpGet("1.3.6.1.4.1.2620.1.6.7.2.1.0")});	
	updateElement("",{"ProcSysTime":snmpGet("1.3.6.1.4.1.2620.1.6.7.2.2.0")});	
	updateElement("",{"ProcIdleTime":snmpGet("1.3.6.1.4.1.2620.1.6.7.2.3.0")});	
	updateElement("",{"ProcUsage":snmpGet("1.3.6.1.4.1.2620.1.6.7.2.4.0")});	
	updateElement("",{"ProcInterrupts":snmpGet("1.3.6.1.4.1.2620.1.6.7.2.6.0")});
	updateElement("",{"ProcNum":snmpGet("1.3.6.1.4.1.2620.1.6.7.2.7.0")});
	
	updateElement("",{" diskPercent":snmpGet("1.3.6.1.4.1.2620.1.6.7.3.3.0")});	
	updateElement("",{" diskFreeTotal":snmpGet("1.3.6.1.4.1.2620.1.6.7.3.4.0")});	
	updateElement("",{" diskFreeAvail":snmpGet("1.3.6.1.4.1.2620.1.6.7.3.5.0")});	
	updateElement("",{" diskTotal":snmpGet("1.3.6.1.4.1.2620.1.6.7.3.6.0")});
	//updateElement("",{"?????????":snmpGet("1.3.6.1.4.1.2620.1.6.7.3.7.0")});
	
	updateElement("",{"MemTotalVirtual64":snmpGet("1.3.6.1.4.1.2620.1.6.7.4.1.0")});	
	updateElement("",{"MemActiveVirtual64":snmpGet("1.3.6.1.4.1.2620.1.6.7.4.2.0")});	
	updateElement("",{"MemTotalReal64":snmpGet("1.3.6.1.4.1.2620.1.6.7.4.3.0")});	
	updateElement("",{"MemActiveReal64":snmpGet("1.3.6.1.4.1.2620.1.6.7.4.4.0")});
	updateElement("",{"MemFreeReal64":snmpGet("1.3.6.1.4.1.2620.1.6.7.4.5.0")});
	
	
	//////////////
	
	updateElement("",{"MgProdName":snmpGet("1.3.6.1.4.1.2620.1.7.1.0")});	
	updateElement("",{"MgVerMajor":snmpGet("1.3.6.1.4.1.2620.1.7.2.0")});	
	updateElement("",{"MgVerMinor":snmpGet("1.3.6.1.4.1.2620.1.7.3.0")});	
	updateElement("",{"MgBuildNumber":snmpGet("1.3.6.1.4.1.2620.1.7.4.0")});	
	updateElement("",{"MgActiveStatus":snmpGet("1.3.6.1.4.1.2620.1.7.5.0")});
	updateElement("",{"MgFwmIsAlive":snmpGet("1.3.6.1.4.1.2620.1.7.6.0")});
	updateElement("",{"MgMgmtHAJournals":snmpGet("1.3.6.1.4.1.2620.1.7.9.0")});	
	updateElement("",{"MgIsLicenseViolation":snmpGet("1.3.6.1.4.1.2620.1.7.10.0")});	
//	updateElement("",{"MgLicenseViolationMsg":snmpGet("1.3.6.1.4.1.2620.1.7.11.0")});
	updateElement("",{"MgStatCode":snmpGet("1.3.6.1.4.1.2620.1.7.101.0")});	
	updateElement("",{"MgStatShortDescr":snmpGet("1.3.6.1.4.1.2620.1.7.102.0")});
	//updateElement("",{"MgStatLongDescr":snmpGet("1.3.6.1.4.1.2620.1.7.6.103")});
	

//	updateElement("",{"DtpsProdName":snmpGet("1.3.6.1.4.1.2620.1.9.1.0")});
	updateElement("",{"DtpsVerMajor":snmpGet("1.3.6.1.4.1.2620.1.9.2.0")});
	updateElement("",{"DtpsVerMinor":snmpGet("1.3.6.1.4.1.2620.1.9.3.0")});	
	updateElement("",{"DtpsLicensedUsers":snmpGet("1.3.6.1.4.1.2620.1.9.4.0")});	
	updateElement("",{"DtpsConnectedUsers":snmpGet("1.3.6.1.4.1.2620.1.9.5.0")});
	updateElement("",{"DtpsStatCode":snmpGet("1.3.6.1.4.1.2620.1.9.101.0")});	
	updateElement("",{"DtpsStatShortDescr":snmpGet("1.3.6.1.4.1.2620.1.9.102.0")});
	updateElement("",{"DtpsStatLongDescr":snmpGet("1.3.6.1.4.1.2620.1.9.103.0")});	
	
	
	
	//.1.3.6.1.4.1.2620.1.50
	//statistic
	updateElement("Statistic",{"TreatExtarctionSubscriptionStatus":/*snmpGet("1.3.6.1.4.1.2620.1.50.1.1")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionSubscriptionExpDate":/*snmpGet("1.3.6.1.4.1.2620.1.50.1.2")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionSubscriptionDesc":/*snmpGet("1.3.6.1.4.1.2620.1.50.1.3")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionTotalScannedAttachments":/*snmpGet("1.3.6.1.4.1.2620.1.50.2.1")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionCleanedAttachments":/*snmpGet("1.3.6.1.4.1.2620.1.50.2.2")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionOriginalAttachmentsAccesses":/*snmpGet("1.3.6.1.4.1.2620.1.50.2.3")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionStatusCode":/*snmpGet("1.3.6.1.4.1.2620.1.50.101")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionStatusShortDesc":/*snmpGet("1.3.6.1.4.1.2620.1.50.102")*/"Null"});
	updateElement("Statistic",{"TreatExtarctionStatusLongDesc":/*snmpGet("1.3.6.1.4.1.2620.1.50.103")*/"Null"});
	
	ethernetPortAttributes();
	return "Success";
}

function Alarm() {
	ifAlarm();
	return "Success";
}

function ifAlarm(){
	logDebug("!!!!!linkAlarm()!!!!!");
	var adminStatusOid = "1.3.6.1.2.1.2.2.1.7";
	var operStatusOid = "1.3.6.1.2.1.2.2.1.8";
	
	var result = snmpWalk({"adminStatus" : adminStatusOid,
							"operStatus" : operStatusOid});
	
	for (var index in result) {
		logDebug("__AS__"+result[index].adminStatus+"__");
		if (result[index].adminStatus == 1) {
			logDebug("__OS__"+result[index].operStatus+"__");
			if (result[index].operStatus == 1) {
				sendAlarm({
					   "Name" : "operStatus",
					   "ProbableCause" : 1,
					   "DisplayName": "Interface_"+index,
					   "AlertType" : 1,
					   "PerceivedSeverity" : 2,
					   "SpecificCause": "UIM_SmartOneEthernetPort",
					   "Description" : "Address=" + manager.getProperty("Interface_"+index, "PhysicalAddress")  + ", Description=" + manager.getProperty("Interface_"+index, "Description"),
					   "State" : 3,
					   "Caption" : "OperStatus",
					   "UserResponsible" : "Device",
					   "Clearable" : false,
					   "RemoteId" : "1234",
					   "ManagedObject": "Interface_"+index
					   });

			}
			
		}
	}
}
function Performance() {

	return "Success";
}


function TrapParser(trapId, varbinds, alarmId) {
	logInfo(trapId+" TRAPID")
	
	return "success";
}


function ErrorHandler(functionName, params, exception) {
	logDebug("Handling Error on " + functionName);

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