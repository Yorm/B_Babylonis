package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_AutonomousDeviceInstantiationComponent
 * 	@author jkonicki
 */
public class UIM_AutonomousDeviceInstantiationComponent extends com.harris.netboss.common.schema.generation.classes.UIM_AutonomousNetworkResourceInstantiationComponent implements java.io.Serializable {


    private String Version
 ;
    private Boolean LogFileEnabled
  = new java.lang.Boolean(false);
    private Integer LogLevel
  = new java.lang.Integer(3);
    private Boolean LogConsoleEnabled
  = new java.lang.Boolean(true);
    private String LogDirectory
  = "<AgentHome>\\logs";
    private Boolean AutoStart
  = new java.lang.Boolean(false);

    public UIM_AutonomousDeviceInstantiationComponent() {


    }

   
    public String getVersion() {
        return this.Version;
    }
    
    public void setVersion(String Version) {
        this.Version = Version;
    }
    public Boolean getLogFileEnabled() {
        return this.LogFileEnabled;
    }
    
    public void setLogFileEnabled(Boolean LogFileEnabled) {
        this.LogFileEnabled = LogFileEnabled;
    }
    public Integer getLogLevel() {
        return this.LogLevel;
    }
    
    public void setLogLevel(Integer LogLevel) {
        this.LogLevel = LogLevel;
    }
    public Boolean getLogConsoleEnabled() {
        return this.LogConsoleEnabled;
    }
    
    public void setLogConsoleEnabled(Boolean LogConsoleEnabled) {
        this.LogConsoleEnabled = LogConsoleEnabled;
    }
    public String getLogDirectory() {
        return this.LogDirectory;
    }
    
    public void setLogDirectory(String LogDirectory) {
        this.LogDirectory = LogDirectory;
    }
    public Boolean getAutoStart() {
        return this.AutoStart;
    }
    
    public void setAutoStart(Boolean AutoStart) {
        this.AutoStart = AutoStart;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("Version").append("='").append(getVersion()).append("' ");			
      buffer.append("LogFileEnabled").append("='").append(getLogFileEnabled()).append("' ");			
      buffer.append("LogLevel").append("='").append(getLogLevel()).append("' ");			
      buffer.append("LogConsoleEnabled").append("='").append(getLogConsoleEnabled()).append("' ");			
      buffer.append("LogDirectory").append("='").append(getLogDirectory()).append("' ");			
      buffer.append("AutoStart").append("='").append(getAutoStart()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


