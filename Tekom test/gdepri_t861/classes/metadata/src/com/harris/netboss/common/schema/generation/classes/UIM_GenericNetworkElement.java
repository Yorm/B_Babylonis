package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_GenericNetworkElement
 * 	@author jkonicki
 */
public class UIM_GenericNetworkElement extends com.harris.netboss.common.schema.generation.classes.UIM_NetworkElement implements java.io.Serializable {


    private Long SystemUpTime
 ;
    private String SystemLocation
 ;
    private String SystemContact
 ;
    private Integer SnmpSleepTimeout
  = new java.lang.Integer(0);
    private String DeviceName
 ;
    private Boolean IsElementInEMS
  = new java.lang.Boolean(false);

    public UIM_GenericNetworkElement() {


    }

   
    public Long getSystemUpTime() {
        return this.SystemUpTime;
    }
    
    public void setSystemUpTime(Long SystemUpTime) {
        this.SystemUpTime = SystemUpTime;
    }
    public String getSystemLocation() {
        return this.SystemLocation;
    }
    
    public void setSystemLocation(String SystemLocation) {
        this.SystemLocation = SystemLocation;
    }
    public String getSystemContact() {
        return this.SystemContact;
    }
    
    public void setSystemContact(String SystemContact) {
        this.SystemContact = SystemContact;
    }
    public Integer getSnmpSleepTimeout() {
        return this.SnmpSleepTimeout;
    }
    
    public void setSnmpSleepTimeout(Integer SnmpSleepTimeout) {
        this.SnmpSleepTimeout = SnmpSleepTimeout;
    }
    public String getDeviceName() {
        return this.DeviceName;
    }
    
    public void setDeviceName(String DeviceName) {
        this.DeviceName = DeviceName;
    }
    public Boolean getIsElementInEMS() {
        return this.IsElementInEMS;
    }
    
    public void setIsElementInEMS(Boolean IsElementInEMS) {
        this.IsElementInEMS = IsElementInEMS;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("SystemUpTime").append("='").append(getSystemUpTime()).append("' ");			
      buffer.append("SystemLocation").append("='").append(getSystemLocation()).append("' ");			
      buffer.append("SystemContact").append("='").append(getSystemContact()).append("' ");			
      buffer.append("SnmpSleepTimeout").append("='").append(getSnmpSleepTimeout()).append("' ");			
      buffer.append("DeviceName").append("='").append(getDeviceName()).append("' ");			
      buffer.append("IsElementInEMS").append("='").append(getIsElementInEMS()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


