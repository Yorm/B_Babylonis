package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_NetworkElement
 * 	@author jkonicki
 */
public class UIM_NetworkElement extends com.harris.netboss.common.schema.generation.classes.UIM_PhysicalElement implements java.io.Serializable {


    private String IPAddress
 ;
    private Short ManagementStatus
  = new Short((short)0);

    public UIM_NetworkElement() {


    }

   
    public String getIPAddress() {
        return this.IPAddress;
    }
    
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }
    public Short getManagementStatus() {
        return this.ManagementStatus;
    }
    
    public void setManagementStatus(Short ManagementStatus) {
        this.ManagementStatus = ManagementStatus;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("IPAddress").append("='").append(getIPAddress()).append("' ");			
      buffer.append("ManagementStatus").append("='").append(getManagementStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


