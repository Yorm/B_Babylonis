//----------------------------------------------------------------------------
//Ќовый человек дергает за рычаги - и машина его благодарит


function Configuration() {
	var shortcut = snmpWalk({"portVID": "1.3.6.1.4.1.31258.1.2.1.5.3.3.2.1"});
	manager.createShortcut(shortcut.portVID,snmpGet(".1.3.6.1.4.1.31258.1.2.1.5.2.1.1.2.1"));
	
	createEthernetPorts();
	//
	var doNotDeleteList = [];
	return doNotDeleteList;
}

function createEthernetPorts() {

	var result=snmpWalk({"portIndex" : "1.3.6.1.2.1.2.2.1.1"});

	for (var index in result) {
		if(!shouldRun()) {
			return undefined;
		}
		if (parseInt(index) < 1000) {
			createElement({"Name" : "EthernetInterfaces",
						"DisplayName" : "Ethernet Interfaces",
						"ElementType" : "GDEpriEthernetGrouping",//
						"CustomClassName" : "UIM_GDEpriT861EthernetGrouping",
						"Parent" : ""});
			updateElement("EthernetInterfaces", {"Diagramm" : true});

			var uniqName = "Interface_"+result[index].portIndex;
			var displayName = uniqName;		
			
			createElement({"Name" : uniqName,
						"DisplayName" : displayName,
						"ElementType" : "GDEpriEthernetPort",
						"CustomClassName" : "UIM_GDEpriT861EthernetPort",
						"Parent" : "EthernetInterfaces"});
			updateElement(uniqName, {"Diagramm" : true});
		}
	}
	//
	{
		createElement({"Name" : "Ports",
					"DisplayName" : "Ports",
					"ElementType" : "GDEpriGenericLogicalDevice",//
					"CustomClassName" : "UIM_GenericLogicalDevice",
					"Parent" : ""});
		updateElement("EthernetInterfaces", {"Diagramm" : true});
		for (var i=1;i<=10;i++) {
			if(!shouldRun()) {
				return undefined;
			}
			
			var uniqName = "Port_"+i;
			var displayName = uniqName;		
			createElement({"Name" : uniqName,
						"DisplayName" : displayName,
						"ElementType" : "GDEpriPort",
						"CustomClassName" : "UIM_GDEpriT861Port",
						"Parent" : "Ports"});
			updateElement(uniqName, {"Diagramm" : true});
		}
	}
	//
	{
		createElement({"Name" : "Vlans",
				"DisplayName" : "Vlans",
				"ElementType" : "GDEpriGenericLogicalDevice",
				"CustomClassName" : "UIM_GenericLogicalDevice",
				"Parent" : ""});
		updateElement("Vlans", {"Diagramm" : true});
		
		createElement({"Name" : "Vlan_1",
					"DisplayName" : "Vlan_1",
					"ElementType" : "GDEpriVlan",
					"CustomClassName" : "UIM_GDEpriT861Vlans",
					"Parent" : "Vlans"});
		updateElement("Vlan_1", {"Diagramm" : true});
	}
	
	
	//
}
//----------------------------------------------------------------------------
function Attributes() {
	ethernetPortAttributes();
	
	var i = 1;
	while(true){
		if(snmpGet("1.3.6.1.4.1.31258.1.2.1.2.1.1.2." + i)==null) break;
		else i++;
	}
	i-=1;
	
	for (var j=1;j<=i;j++) {
		if(!shouldRun()) {
			return undefined;
		}
		portLinkStatus(j);
		portSetting(j);
		portbandwidthManager(j);
		ingressPortbandwidthTable(j);
		portTypeAndVidTable(j);
		recvFrameTable(j);
		sendFrameTable(j);
	}
	updateElement("",{"NumberOfPorts":i});	
	vlanAttributes();
	

	return "Success";
}

function portLinkStatus(i){
		updateElement("Port_"+i, {"Alias" : i,//
											"LinkStatus" :snmpGet("1.3.6.1.4.1.31258.1.2.1.2.1.1.2." + i),
											"DuplexStatus" :snmpGet("1.3.6.1.4.1.31258.1.2.1.2.1.1.3." + i),//
											"PortSpeed" :snmpGet("1.3.6.1.4.1.31258.1.2.1.2.1.1.4." + i),
											"PortMedia" :snmpGet("1.3.6.1.4.1.31258.1.2.1.2.1.1.5." + i)
											});
}
function portSetting(i){
		updateElement("Port_"+i, {"Alias" : i,//
											"MediaMode" :snmpGet(".1.3.6.1.4.1.31258.1.2.1.3.1.1.1." + i),
											"SpeedMode" :snmpGet(".1.3.6.1.4.1.31258.1.2.1.3.1.1.2." + i),//
											"DuplexMode" :snmpGet("1.3.6.1.4.1.31258.1.2.1.3.1.1.3." + i),
											"PortDisable" :snmpGet("1.3.6.1.4.1.31258.1.2.1.3.1.1.4." + i),
											"FlowCtrl" :snmpGet("1.3.6.1.4.1.31258.1.2.1.3.1.1.5." + i)
											});
}
function portbandwidthManager(i){
		updateElement("Port_"+i, {"Alias" : i,//
											"EgressRate" :snmpGet("1.3.6.1.4.1.31258.1.2.1.4.1.1.2." + i),
											});
}
function ingressPortbandwidthTable(i){
		updateElement("Port_"+i, {"Alias" : i,//
											"PackType" :snmpGet("1.3.6.1.4.1.31258.1.2.1.4.2.1.1." + i),
											"LowProbandwidth" :snmpGet("1.3.6.1.4.1.31258.1.2.1.4.2.1.2." + i),//
											"NormalProbandwidth" :snmpGet("1.3.6.1.4.1.31258.1.2.1.4.2.1.3." + i),
											"MediumProbandwidth" :snmpGet("1.3.6.1.4.1.31258.1.2.1.4.2.1.4." + i),
											"HighProbandwidth" :snmpGet("1.3.6.1.4.1.31258.1.2.1.4.2.1.5." + i)
											});
}
function portTypeAndVidTable(i){
		updateElement("Port_"+i, {"Alias" : i,//
											"PortType" :snmpGet("1.3.6.1.4.1.31258.1.2.1.5.3.3.1.1." + i),
											"PortVID" :snmpGet("1.3.6.1.4.1.31258.1.2.1.5.3.3.1.1." + i)
											});
}
function recvFrameTable(i){
		updateElement("Port_"+i, {"Alias" : i,//
											"InGoodOctets" : snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.1." + i),
											"InBadOctets" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.2." + i),
											"InUnicasts" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.3." + i),
											"InBroadCasts" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.4." + i),
											
											"InMultiCasts" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.5." + i),
											"InPause" : snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.6." + i),
											"InUndersize" : snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.7." + i),
											"InFragments" :	snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.8." + i),
											"InOversize" : snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.9." + i),
											"InJabber" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.10." + i),
											"InRxErr" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.11." + i),
											"InFCSErr" :snmpGet("1.3.6.1.4.1.31258.1.2.1.11.1.1.12." + i)
											});
}
function sendFrameTable(i){
		updateElement("Port_"+i, {"Alias" : i,
											"OutOctets":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.1. " + i),
											"OutUnicast":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.2. " + i),
											"OutBroadCasts":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.3." + i),
											"OutMulticasts":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.4. " + i),
											"OutPause":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.5. " + i),
											"Excessive":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.6. " + i),
											"Collisions":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.7. " + i),
											"Deferred":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.8. " + i),
											"Single":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.9. " + i),
											"Multple":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.10. " + i),
											"Late":snmpGet("1.3.6.1.4.1.31258.1.2.1.11.2.1.12. " + i)
											});
}
function vlanAttributes(){

		updateElement("Vlan_1", {"Alias" : 1,//
											"PortVlanID" : snmpGet(".1.3.6.1.4.1.31258.1.2.1.5.2.1.1.2.1"),
											"PortMember" :snmpGet(".1.3.6.1.4.1.31258.1.2.1.5.2.1.1.3.1"),
											"PortVlanStatus" :snmpGet(".1.3.6.1.4.1.31258.1.2.1.5.2.1.1.4.1")
											
											});
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
											//¬се что дальше - возвращает null 
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
//----------------------------------------------------------------------------
function Alarm() {
	//все ал€рмы заккоментированы из за каких то багов
	
	linkStatusAlarm();
	return "Success";
}

function linkStatusAlarm() {
	var result = snmpWalk({"linkStatus" : "1.3.6.1.4.1.31258.1.2.1.2.1.1.2"});
	
	logInfo("_________alarmStart");

	for (var i in result) { 
		logInfo("ALLLLLLLLLRM"+result[i].linkStatus)
		if(result[i].linkStatus==0){
			sendAlarm({
				   "Name" : "link Down Alarm",
				   "ProbableCause" : 1,
				   "DisplayName": "Port_"+i,
				   "AlertType" : 1,
				   "PerceivedSeverity" : 6,
				   "SpecificCause": "UIM_GDEpriT861Port",
				   "Description" : "The configuration was changed",
				   "State" : 1,
				   "Caption" : "LinkDown",
				   "UserResponsible" : "Device",
				   "Clearable" : false,
				   "RemoteId" : "1234",
				   "ManagedObject": "Port_"+i
				   });
		}
		if(result[i].linkStatus==1){
			sendAlarm({
				   "Name" : "link Up Alarm",
				   "ProbableCause" : 1,
				   "DisplayName":  "Port_"+i,
				   "AlertType" : 1,
				   "PerceivedSeverity" : 2,
				   "SpecificCause": "UIM_GDEpriT861Port",
				   "Description" : "The configuration was changed",
				   "State" : 3,
				   "Caption" : "linkUp",
				   "UserResponsible" : "Device",
				   "Clearable" : false,
				   "RemoteId" : "1234",
				   "ManagedObject":  "Port_"+i
				   });
		}
		
	}
	logInfo("_________alarmEnd");

	return "Success";
}


function coldStartAlarm(){
	logInfo("ColdStart");
}

function warmStartAlarm(){
	logInfo("WarmStart");
}

function linkDownAlarm(name){
	sendAlarm({
		   "Name" : "link Down Alarm",
		   "ProbableCause" : 1,
		   "DisplayName": name,
		   "AlertType" : 1,
		   "PerceivedSeverity" : 6,
		   "SpecificCause": "UIM_GDEpriT861Port",
		   "Description" : "The configuration was changed",
		   "State" : 1,
		   "Caption" : "LinkDown",
		   "UserResponsible" : "Device",
		   "Clearable" : false,
		   "RemoteId" : "1234",
		   "ManagedObject": name
		   });
	logInfo("LinkDown");
}

//Caption=Error starting trap receiver, so unable to receive traps, 
//Description=Error starting trap receiver, 

//SpecificCause=Unable to Receive Traps,
//DisplayName=Unable to Receive Traps

function linkUpAlarm(name){
	
	sendAlarm({
		   "Name" : "link Up Alarm",
		   "ProbableCause" : 1,
		   "DisplayName": name,
		   "AlertType" : 1,
		   "PerceivedSeverity" : 2,
		   "SpecificCause": "UIM_GDEpriT861Port",
		   "Description" : "The configuration was changed",
		   "State" : 3,
		   "Caption" : "linkUp",
		   "UserResponsible" : "Device",
		   "Clearable" : false,
		   "RemoteId" : "1234",
		   "ManagedObject": name
		   });
	logInfo("LinkUp");
}


function authenticationFailureAlarm(){
	logInfo("AuthenticationFailure");
}

/*
<symbol id="TextLabelSquare_4_3_2" layoutColumnCount="10" layoutHGap="5" layoutRowCount="10" layoutVGap="5" modelObject="/UIM_GDEpriT861Port[ElementType=GDEpriT861]">
<propertyMapping metadataProperty="PortSpeed" symbolParameter="PortSpeed"/>
</symbol>
 */

//----------------------------------------------------------------------------
function Performance() {
	// TODO Add code to poll and update properties
	return "Success";
}

//----------------------------------------------------------------------------
function TrapParser(trapId, varbinds, alarmId) {
	
	switch (trapId) {
	  case "1.3.6.1.6.3.1.1.5.1":{
		  coldStartAlarm();
	  }
	  break;
	  case "1.3.6.1.6.3.1.1.5.2":{
		  warmStartAlarm();
	  }
	  break;
	  case "1.3.6.1.6.3.1.1.5.3":{
		  logInfo("LINNNNNNNNNNNNNNNNNNKDOOOOOOOOWWWWWWWNNNNNNNNNNNNNNNNNNNNNN");
		  linkDownAlarm("Port_"+varbinds[1].value);

	  }
	  break;
	  case "1.3.6.1.6.3.1.1.5.4":{
		  linkUpAlarm("Port_"+varbinds[1].value);
	  }
	  break;
	  case "1.3.6.1.6.3.1.1.5.5":{
		  authenticationFailureAlarm();
	  }
	  break;
	
	}
	return "success";
}

//============================================================================================================================
// -------------
// Error Handling function called when an exception or now return on a method
// FunctionName = Name of the offending function
// params = array of parameters that were passed to the function
// exception = string of the stack trace if any that caused the problem
// -------------
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
//lol
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


function updateElement(name, json) {
	var hashmap = new java.util.HashMap();
	for (key in json) {
		hashmap.put(key, json[key]);
		manager.logInfo(key + " :" + json[key]);
	}
	manager.updateElement(name, hashmap);

}

function createElement(json) {
	var hashmap = new java.util.HashMap();
	for (key in json) {

		hashmap.put(key, json[key]);
		manager.logInfo(key + " :" + json[key]);
	}
	return manager.createElement(hashmap);

}


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
