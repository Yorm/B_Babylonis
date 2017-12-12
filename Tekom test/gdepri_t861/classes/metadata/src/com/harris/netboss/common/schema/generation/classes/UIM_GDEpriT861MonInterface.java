package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_GDEpriT861MonInterface
 * 	@author jkonicki
 */
public class UIM_GDEpriT861MonInterface extends com.harris.netboss.common.schema.generation.classes.UIM_GenericSNMPDeviceMonInterface implements java.io.Serializable {


    private Integer PerformancePollingTime
  = new java.lang.Integer(0);
    private String PerformancePollingFrequency
  = "Never";

    public UIM_GDEpriT861MonInterface() {

		setCustomAgentName("T861");setCustomAgentVendor("GDEpri");

    }

   
    public Integer getPerformancePollingTime() {
        return this.PerformancePollingTime;
    }
    
    public void setPerformancePollingTime(Integer PerformancePollingTime) {
        this.PerformancePollingTime = PerformancePollingTime;
    }
    public String getPerformancePollingFrequency() {
        return this.PerformancePollingFrequency;
    }
    
    public void setPerformancePollingFrequency(String PerformancePollingFrequency) {
        this.PerformancePollingFrequency = PerformancePollingFrequency;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("PerformancePollingTime").append("='").append(getPerformancePollingTime()).append("' ");			
      buffer.append("PerformancePollingFrequency").append("='").append(getPerformancePollingFrequency()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


