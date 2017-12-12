package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA


import java.util.Date;

/**
 * Generated Javadocs for CIM_EnabledLogicalElement
 * 	@author jkonicki
 */
public class CIM_EnabledLogicalElement extends com.harris.netboss.common.schema.generation.classes.CIM_LogicalElement implements java.io.Serializable {


    private Integer EnabledState
  = new java.lang.Integer(5);
    private String OtherEnabledState
 ;
    private Integer RequestedState
  = new java.lang.Integer(12);
    private Integer EnabledDefault
  = new java.lang.Integer(2);
    private Date TimeOfLastStateChange
 ;

    public CIM_EnabledLogicalElement() {


    }

   
    public Integer getEnabledState() {
        return this.EnabledState;
    }
    
    public void setEnabledState(Integer EnabledState) {
        this.EnabledState = EnabledState;
    }
    public String getOtherEnabledState() {
        return this.OtherEnabledState;
    }
    
    public void setOtherEnabledState(String OtherEnabledState) {
        this.OtherEnabledState = OtherEnabledState;
    }
    public Integer getRequestedState() {
        return this.RequestedState;
    }
    
    public void setRequestedState(Integer RequestedState) {
        this.RequestedState = RequestedState;
    }
    public Integer getEnabledDefault() {
        return this.EnabledDefault;
    }
    
    public void setEnabledDefault(Integer EnabledDefault) {
        this.EnabledDefault = EnabledDefault;
    }
    public Date getTimeOfLastStateChange() {
        return this.TimeOfLastStateChange;
    }
    
    public void setTimeOfLastStateChange(Date TimeOfLastStateChange) {
        this.TimeOfLastStateChange = TimeOfLastStateChange;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("EnabledState").append("='").append(getEnabledState()).append("' ");			
      buffer.append("OtherEnabledState").append("='").append(getOtherEnabledState()).append("' ");			
      buffer.append("RequestedState").append("='").append(getRequestedState()).append("' ");			
      buffer.append("EnabledDefault").append("='").append(getEnabledDefault()).append("' ");			
      buffer.append("TimeOfLastStateChange").append("='").append(getTimeOfLastStateChange()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


