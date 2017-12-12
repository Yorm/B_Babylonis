package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for HNSD_Provider
 * 	@author jkonicki
 */
public class HNSD_Provider extends com.harris.netboss.common.schema.generation.classes.CIM_WBEMService implements java.io.Serializable {


    private Boolean AutoStart
  = new java.lang.Boolean(false);
    private String ServerId
 ;

    public HNSD_Provider() {


    }

   
    public Boolean getAutoStart() {
        return this.AutoStart;
    }
    
    public void setAutoStart(Boolean AutoStart) {
        this.AutoStart = AutoStart;
    }
    public String getServerId() {
        return this.ServerId;
    }
    
    public void setServerId(String ServerId) {
        this.ServerId = ServerId;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("AutoStart").append("='").append(getAutoStart()).append("' ");			
      buffer.append("ServerId").append("='").append(getServerId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


