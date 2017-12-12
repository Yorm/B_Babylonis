package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA


import java.math.BigInteger;

/**
 * Generated Javadocs for UIM_GDEpriT861Vlans
 * 	@author jkonicki
 */
public class UIM_GDEpriT861Vlans extends com.harris.netboss.common.schema.generation.classes.UIM_GenericLogicalDevice implements java.io.Serializable {


    private Integer PortVlanID
  = new java.lang.Integer(0);
    private BigInteger PortMember
  = new BigInteger("0", 10);
    private Integer PortVlanStatus
  = new java.lang.Integer(0);

    public UIM_GDEpriT861Vlans() {


    }

   
    public Integer getPortVlanID() {
        return this.PortVlanID;
    }
    
    public void setPortVlanID(Integer PortVlanID) {
        this.PortVlanID = PortVlanID;
    }
    public BigInteger getPortMember() {
        return this.PortMember;
    }
    
    public void setPortMember(BigInteger PortMember) {
        this.PortMember = PortMember;
    }
    public Integer getPortVlanStatus() {
        return this.PortVlanStatus;
    }
    
    public void setPortVlanStatus(Integer PortVlanStatus) {
        this.PortVlanStatus = PortVlanStatus;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("PortVlanID").append("='").append(getPortVlanID()).append("' ");			
      buffer.append("PortMember").append("='").append(getPortMember()).append("' ");			
      buffer.append("PortVlanStatus").append("='").append(getPortVlanStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


