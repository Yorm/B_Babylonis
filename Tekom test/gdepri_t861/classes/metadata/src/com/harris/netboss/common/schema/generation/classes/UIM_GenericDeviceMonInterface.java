package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA


import java.util.Date;

/**
 * Generated Javadocs for UIM_GenericDeviceMonInterface
 * 	@author jkonicki
 */
public class UIM_GenericDeviceMonInterface extends com.harris.netboss.common.schema.generation.classes.UIM_GenericAgentMonInterface implements java.io.Serializable {


    private Long StatusSleep
  = new java.lang.Long(900);
    private Date CheckConnectionTimeStamp
 ;

    public UIM_GenericDeviceMonInterface() {


    }

   
    public Long getStatusSleep() {
        return this.StatusSleep;
    }
    
    public void setStatusSleep(Long StatusSleep) {
        this.StatusSleep = StatusSleep;
    }
    public Date getCheckConnectionTimeStamp() {
        return this.CheckConnectionTimeStamp;
    }
    
    public void setCheckConnectionTimeStamp(Date CheckConnectionTimeStamp) {
        this.CheckConnectionTimeStamp = CheckConnectionTimeStamp;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("StatusSleep").append("='").append(getStatusSleep()).append("' ");			
      buffer.append("CheckConnectionTimeStamp").append("='").append(getCheckConnectionTimeStamp()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


