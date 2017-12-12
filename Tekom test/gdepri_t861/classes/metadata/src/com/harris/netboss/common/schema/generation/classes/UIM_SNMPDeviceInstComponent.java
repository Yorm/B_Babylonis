package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_SNMPDeviceInstComponent
 * 	@author jkonicki
 */
public class UIM_SNMPDeviceInstComponent extends com.harris.netboss.common.schema.generation.classes.UIM_GenericDeviceInstComponent implements java.io.Serializable {


    private Long AlarmSleep
  = new java.lang.Long(0);
    private Long AttributeSleep
  = new java.lang.Long(0);
    private Long ConfigurationSleep
  = new java.lang.Long(0);
    private Integer SnmpSleepTimeout
  = new java.lang.Integer(0);
    private String SnmpWriteCommunity
  = "private";
    private Integer SnmpVersion
  = new java.lang.Integer(1);
    private Long SnmpTimeout
  = new java.lang.Long(5000);
    private Integer SnmpRetries
  = new java.lang.Integer(3);
    private String SnmpReadCommunity
  = "public";
    private Integer SnmpMaxSessions
  = new java.lang.Integer(3);
    private String SnmpTrapPort
  = "162";
    private String SnmpDevicePort
  = "161";
    private String SnmpDeviceIP
 ;
    private String SnmpAuthPassPhrase
  = "authPassphrase";
    private String SnmpAuthProtocol
  = "noAuth";
    private String SnmpContextEngineID
 ;
    private String SnmpContextName
 ;
    private String SnmpPrivPassPhrase
  = "privPassphrase";
    private String SnmpPrivProtocol
  = "noPriv";
    private Integer SnmpSecurityLevel
  = new java.lang.Integer(1);
    private String SnmpSecurityName
  = "securityName";
    private Integer SnmpMaxRepetitions
  = new java.lang.Integer(50);
    private Integer SnmpNonRepeaters
  = new java.lang.Integer(0);

    public UIM_SNMPDeviceInstComponent() {


    }

   
    public Long getAlarmSleep() {
        return this.AlarmSleep;
    }
    
    public void setAlarmSleep(Long AlarmSleep) {
        this.AlarmSleep = AlarmSleep;
    }
    public Long getAttributeSleep() {
        return this.AttributeSleep;
    }
    
    public void setAttributeSleep(Long AttributeSleep) {
        this.AttributeSleep = AttributeSleep;
    }
    public Long getConfigurationSleep() {
        return this.ConfigurationSleep;
    }
    
    public void setConfigurationSleep(Long ConfigurationSleep) {
        this.ConfigurationSleep = ConfigurationSleep;
    }
    public Integer getSnmpSleepTimeout() {
        return this.SnmpSleepTimeout;
    }
    
    public void setSnmpSleepTimeout(Integer SnmpSleepTimeout) {
        this.SnmpSleepTimeout = SnmpSleepTimeout;
    }
    public String getSnmpWriteCommunity() {
        return this.SnmpWriteCommunity;
    }
    
    public void setSnmpWriteCommunity(String SnmpWriteCommunity) {
        this.SnmpWriteCommunity = SnmpWriteCommunity;
    }
    public Integer getSnmpVersion() {
        return this.SnmpVersion;
    }
    
    public void setSnmpVersion(Integer SnmpVersion) {
        this.SnmpVersion = SnmpVersion;
    }
    public Long getSnmpTimeout() {
        return this.SnmpTimeout;
    }
    
    public void setSnmpTimeout(Long SnmpTimeout) {
        this.SnmpTimeout = SnmpTimeout;
    }
    public Integer getSnmpRetries() {
        return this.SnmpRetries;
    }
    
    public void setSnmpRetries(Integer SnmpRetries) {
        this.SnmpRetries = SnmpRetries;
    }
    public String getSnmpReadCommunity() {
        return this.SnmpReadCommunity;
    }
    
    public void setSnmpReadCommunity(String SnmpReadCommunity) {
        this.SnmpReadCommunity = SnmpReadCommunity;
    }
    public Integer getSnmpMaxSessions() {
        return this.SnmpMaxSessions;
    }
    
    public void setSnmpMaxSessions(Integer SnmpMaxSessions) {
        this.SnmpMaxSessions = SnmpMaxSessions;
    }
    public String getSnmpTrapPort() {
        return this.SnmpTrapPort;
    }
    
    public void setSnmpTrapPort(String SnmpTrapPort) {
        this.SnmpTrapPort = SnmpTrapPort;
    }
    public String getSnmpDevicePort() {
        return this.SnmpDevicePort;
    }
    
    public void setSnmpDevicePort(String SnmpDevicePort) {
        this.SnmpDevicePort = SnmpDevicePort;
    }
    public String getSnmpDeviceIP() {
        return this.SnmpDeviceIP;
    }
    
    public void setSnmpDeviceIP(String SnmpDeviceIP) {
        this.SnmpDeviceIP = SnmpDeviceIP;
    }
    public String getSnmpAuthPassPhrase() {
        return this.SnmpAuthPassPhrase;
    }
    
    public void setSnmpAuthPassPhrase(String SnmpAuthPassPhrase) {
        this.SnmpAuthPassPhrase = SnmpAuthPassPhrase;
    }
    public String getSnmpAuthProtocol() {
        return this.SnmpAuthProtocol;
    }
    
    public void setSnmpAuthProtocol(String SnmpAuthProtocol) {
        this.SnmpAuthProtocol = SnmpAuthProtocol;
    }
    public String getSnmpContextEngineID() {
        return this.SnmpContextEngineID;
    }
    
    public void setSnmpContextEngineID(String SnmpContextEngineID) {
        this.SnmpContextEngineID = SnmpContextEngineID;
    }
    public String getSnmpContextName() {
        return this.SnmpContextName;
    }
    
    public void setSnmpContextName(String SnmpContextName) {
        this.SnmpContextName = SnmpContextName;
    }
    public String getSnmpPrivPassPhrase() {
        return this.SnmpPrivPassPhrase;
    }
    
    public void setSnmpPrivPassPhrase(String SnmpPrivPassPhrase) {
        this.SnmpPrivPassPhrase = SnmpPrivPassPhrase;
    }
    public String getSnmpPrivProtocol() {
        return this.SnmpPrivProtocol;
    }
    
    public void setSnmpPrivProtocol(String SnmpPrivProtocol) {
        this.SnmpPrivProtocol = SnmpPrivProtocol;
    }
    public Integer getSnmpSecurityLevel() {
        return this.SnmpSecurityLevel;
    }
    
    public void setSnmpSecurityLevel(Integer SnmpSecurityLevel) {
        this.SnmpSecurityLevel = SnmpSecurityLevel;
    }
    public String getSnmpSecurityName() {
        return this.SnmpSecurityName;
    }
    
    public void setSnmpSecurityName(String SnmpSecurityName) {
        this.SnmpSecurityName = SnmpSecurityName;
    }
    public Integer getSnmpMaxRepetitions() {
        return this.SnmpMaxRepetitions;
    }
    
    public void setSnmpMaxRepetitions(Integer SnmpMaxRepetitions) {
        this.SnmpMaxRepetitions = SnmpMaxRepetitions;
    }
    public Integer getSnmpNonRepeaters() {
        return this.SnmpNonRepeaters;
    }
    
    public void setSnmpNonRepeaters(Integer SnmpNonRepeaters) {
        this.SnmpNonRepeaters = SnmpNonRepeaters;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("AlarmSleep").append("='").append(getAlarmSleep()).append("' ");			
      buffer.append("AttributeSleep").append("='").append(getAttributeSleep()).append("' ");			
      buffer.append("ConfigurationSleep").append("='").append(getConfigurationSleep()).append("' ");			
      buffer.append("SnmpSleepTimeout").append("='").append(getSnmpSleepTimeout()).append("' ");			
      buffer.append("SnmpWriteCommunity").append("='").append(getSnmpWriteCommunity()).append("' ");			
      buffer.append("SnmpVersion").append("='").append(getSnmpVersion()).append("' ");			
      buffer.append("SnmpTimeout").append("='").append(getSnmpTimeout()).append("' ");			
      buffer.append("SnmpRetries").append("='").append(getSnmpRetries()).append("' ");			
      buffer.append("SnmpReadCommunity").append("='").append(getSnmpReadCommunity()).append("' ");			
      buffer.append("SnmpMaxSessions").append("='").append(getSnmpMaxSessions()).append("' ");			
      buffer.append("SnmpTrapPort").append("='").append(getSnmpTrapPort()).append("' ");			
      buffer.append("SnmpDevicePort").append("='").append(getSnmpDevicePort()).append("' ");			
      buffer.append("SnmpDeviceIP").append("='").append(getSnmpDeviceIP()).append("' ");			
      buffer.append("SnmpAuthPassPhrase").append("='").append(getSnmpAuthPassPhrase()).append("' ");			
      buffer.append("SnmpAuthProtocol").append("='").append(getSnmpAuthProtocol()).append("' ");			
      buffer.append("SnmpContextEngineID").append("='").append(getSnmpContextEngineID()).append("' ");			
      buffer.append("SnmpContextName").append("='").append(getSnmpContextName()).append("' ");			
      buffer.append("SnmpPrivPassPhrase").append("='").append(getSnmpPrivPassPhrase()).append("' ");			
      buffer.append("SnmpPrivProtocol").append("='").append(getSnmpPrivProtocol()).append("' ");			
      buffer.append("SnmpSecurityLevel").append("='").append(getSnmpSecurityLevel()).append("' ");			
      buffer.append("SnmpSecurityName").append("='").append(getSnmpSecurityName()).append("' ");			
      buffer.append("SnmpMaxRepetitions").append("='").append(getSnmpMaxRepetitions()).append("' ");			
      buffer.append("SnmpNonRepeaters").append("='").append(getSnmpNonRepeaters()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


