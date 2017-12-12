package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for HNSD_GenericProvider
 * 	@author jkonicki
 */
public class HNSD_GenericProvider extends com.harris.netboss.common.schema.generation.classes.HNSD_GenericAgentProvider implements java.io.Serializable {


    private String DeviceDefaultVersion
  = "None";
    private String DeviceVersionOID
  = "None";
    private String DeviceSysObjectID
  = "None";

    public HNSD_GenericProvider() {


    }

   
    public String getDeviceDefaultVersion() {
        return this.DeviceDefaultVersion;
    }
    
    public void setDeviceDefaultVersion(String DeviceDefaultVersion) {
        this.DeviceDefaultVersion = DeviceDefaultVersion;
    }
    public String getDeviceVersionOID() {
        return this.DeviceVersionOID;
    }
    
    public void setDeviceVersionOID(String DeviceVersionOID) {
        this.DeviceVersionOID = DeviceVersionOID;
    }
    public String getDeviceSysObjectID() {
        return this.DeviceSysObjectID;
    }
    
    public void setDeviceSysObjectID(String DeviceSysObjectID) {
        this.DeviceSysObjectID = DeviceSysObjectID;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("DeviceDefaultVersion").append("='").append(getDeviceDefaultVersion()).append("' ");			
      buffer.append("DeviceVersionOID").append("='").append(getDeviceVersionOID()).append("' ");			
      buffer.append("DeviceSysObjectID").append("='").append(getDeviceSysObjectID()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


