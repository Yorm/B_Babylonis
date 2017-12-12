package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for HNSD_GenericAgentProvider
 * 	@author jkonicki
 */
public class HNSD_GenericAgentProvider extends com.harris.netboss.common.schema.generation.classes.HNSD_Provider implements java.io.Serializable {


    private String CustomAgentVendor
  = "CustomAgentVendor";
    private String CustomAgentName
  = "CustomAgentName";
    private String DeviceClassName
  = "UIM_NetworkElement";
    private String MonitoringInterfaceClass
  = "None";

    public HNSD_GenericAgentProvider() {


    }

   
    public String getCustomAgentVendor() {
        return this.CustomAgentVendor;
    }
    
    public void setCustomAgentVendor(String CustomAgentVendor) {
        this.CustomAgentVendor = CustomAgentVendor;
    }
    public String getCustomAgentName() {
        return this.CustomAgentName;
    }
    
    public void setCustomAgentName(String CustomAgentName) {
        this.CustomAgentName = CustomAgentName;
    }
    public String getDeviceClassName() {
        return this.DeviceClassName;
    }
    
    public void setDeviceClassName(String DeviceClassName) {
        this.DeviceClassName = DeviceClassName;
    }
    public String getMonitoringInterfaceClass() {
        return this.MonitoringInterfaceClass;
    }
    
    public void setMonitoringInterfaceClass(String MonitoringInterfaceClass) {
        this.MonitoringInterfaceClass = MonitoringInterfaceClass;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("CustomAgentVendor").append("='").append(getCustomAgentVendor()).append("' ");			
      buffer.append("CustomAgentName").append("='").append(getCustomAgentName()).append("' ");			
      buffer.append("DeviceClassName").append("='").append(getDeviceClassName()).append("' ");			
      buffer.append("MonitoringInterfaceClass").append("='").append(getMonitoringInterfaceClass()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


