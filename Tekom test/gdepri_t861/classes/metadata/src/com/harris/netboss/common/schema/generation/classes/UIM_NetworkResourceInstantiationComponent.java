package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_NetworkResourceInstantiationComponent
 * 	@author jkonicki
 */
public class UIM_NetworkResourceInstantiationComponent extends com.harris.netboss.common.schema.generation.classes.UIM_InstantiationComponent implements java.io.Serializable {


    private String ManagementAgentName
 ;

    public UIM_NetworkResourceInstantiationComponent() {


    }

   
    public String getManagementAgentName() {
        return this.ManagementAgentName;
    }
    
    public void setManagementAgentName(String ManagementAgentName) {
        this.ManagementAgentName = ManagementAgentName;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("ManagementAgentName").append("='").append(getManagementAgentName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


