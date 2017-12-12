package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for CIM_Service
 * 	@author jkonicki
 */
public class CIM_Service extends com.harris.netboss.common.schema.generation.classes.CIM_EnabledLogicalElement implements java.io.Serializable {


    private String SystemCreationClassName
 ;
    private String SystemName
 ;
    private String CreationClassName
 ;
    private String PrimaryOwnerName
 ;
    private String PrimaryOwnerContact
 ;
    private Boolean Started
 ;

    public CIM_Service() {


    }

   
    public String getSystemCreationClassName() {
        return this.SystemCreationClassName;
    }
    
    public void setSystemCreationClassName(String SystemCreationClassName) {
        this.SystemCreationClassName = SystemCreationClassName;
    }
    public String getSystemName() {
        return this.SystemName;
    }
    
    public void setSystemName(String SystemName) {
        this.SystemName = SystemName;
    }
    public String getCreationClassName() {
        return this.CreationClassName;
    }
    
    public void setCreationClassName(String CreationClassName) {
        this.CreationClassName = CreationClassName;
    }
    public String getPrimaryOwnerName() {
        return this.PrimaryOwnerName;
    }
    
    public void setPrimaryOwnerName(String PrimaryOwnerName) {
        this.PrimaryOwnerName = PrimaryOwnerName;
    }
    public String getPrimaryOwnerContact() {
        return this.PrimaryOwnerContact;
    }
    
    public void setPrimaryOwnerContact(String PrimaryOwnerContact) {
        this.PrimaryOwnerContact = PrimaryOwnerContact;
    }
    public Boolean getStarted() {
        return this.Started;
    }
    
    public void setStarted(Boolean Started) {
        this.Started = Started;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("SystemCreationClassName").append("='").append(getSystemCreationClassName()).append("' ");			
      buffer.append("SystemName").append("='").append(getSystemName()).append("' ");			
      buffer.append("CreationClassName").append("='").append(getCreationClassName()).append("' ");			
      buffer.append("PrimaryOwnerName").append("='").append(getPrimaryOwnerName()).append("' ");			
      buffer.append("PrimaryOwnerContact").append("='").append(getPrimaryOwnerContact()).append("' ");			
      buffer.append("Started").append("='").append(getStarted()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


