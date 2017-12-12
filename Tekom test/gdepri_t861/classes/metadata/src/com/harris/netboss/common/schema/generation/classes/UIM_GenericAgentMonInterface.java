package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_GenericAgentMonInterface
 * 	@author jkonicki
 */
public class UIM_GenericAgentMonInterface extends com.harris.netboss.common.schema.generation.classes.UIM_MonitoringInterface implements java.io.Serializable {


    private Integer AlertSeverityLevel
  = new java.lang.Integer(3);
    private String AgentHome
 ;
    private String SystemName
 ;
    private Boolean LogFileEnabled
  = new java.lang.Boolean(false);
    private Integer LogLevel
  = new java.lang.Integer(-1);
    private Boolean LogConsoleEnabled
  = new java.lang.Boolean(false);
    private String LogDirectory
  = "<AgentHome>\\logs";

    public UIM_GenericAgentMonInterface() {


    }

   
    public Integer getAlertSeverityLevel() {
        return this.AlertSeverityLevel;
    }
    
    public void setAlertSeverityLevel(Integer AlertSeverityLevel) {
        this.AlertSeverityLevel = AlertSeverityLevel;
    }
    public String getAgentHome() {
        return this.AgentHome;
    }
    
    public void setAgentHome(String AgentHome) {
        this.AgentHome = AgentHome;
    }
    public String getSystemName() {
        return this.SystemName;
    }
    
    public void setSystemName(String SystemName) {
        this.SystemName = SystemName;
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

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("AlertSeverityLevel").append("='").append(getAlertSeverityLevel()).append("' ");			
      buffer.append("AgentHome").append("='").append(getAgentHome()).append("' ");			
      buffer.append("SystemName").append("='").append(getSystemName()).append("' ");			
      buffer.append("LogFileEnabled").append("='").append(getLogFileEnabled()).append("' ");			
      buffer.append("LogLevel").append("='").append(getLogLevel()).append("' ");			
      buffer.append("LogConsoleEnabled").append("='").append(getLogConsoleEnabled()).append("' ");			
      buffer.append("LogDirectory").append("='").append(getLogDirectory()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


