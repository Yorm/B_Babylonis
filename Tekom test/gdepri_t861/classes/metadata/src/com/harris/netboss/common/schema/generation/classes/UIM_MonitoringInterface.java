package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_MonitoringInterface
 * 	@author jkonicki
 */
public class UIM_MonitoringInterface extends com.harris.netboss.common.schema.generation.classes.UIM_ManagedElement implements java.io.Serializable {


    private String Name
 ;
    private Short Status
  = new Short((short)0);
    private Boolean AutoStart
  = new java.lang.Boolean(false);
    private String IPAddress
 ;
    private String Version
 ;

    public UIM_MonitoringInterface() {


    }

   
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    public Short getStatus() {
        return this.Status;
    }
    
    public void setStatus(Short Status) {
        this.Status = Status;
    }
    public Boolean getAutoStart() {
        return this.AutoStart;
    }
    
    public void setAutoStart(Boolean AutoStart) {
        this.AutoStart = AutoStart;
    }
    public String getIPAddress() {
        return this.IPAddress;
    }
    
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }
    public String getVersion() {
        return this.Version;
    }
    
    public void setVersion(String Version) {
        this.Version = Version;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("Name").append("='").append(getName()).append("' ");			
      buffer.append("Status").append("='").append(getStatus()).append("' ");			
      buffer.append("AutoStart").append("='").append(getAutoStart()).append("' ");			
      buffer.append("IPAddress").append("='").append(getIPAddress()).append("' ");			
      buffer.append("Version").append("='").append(getVersion()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


