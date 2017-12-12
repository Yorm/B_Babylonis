package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_ManagedElement
 * 	@author jkonicki
 */
public class UIM_ManagedElement extends com.harris.netboss.common.schema.BaseAbstractEntity implements java.io.Serializable {


    private long id
 ;
    private String Caption
 ;
    private String Description
 ;
    private String DisplayName
 ;
    private Boolean DeletionAllowed
  = new java.lang.Boolean(false);
    private Short MonitoringStatus
  = new Short((short)0);
    private String classtype
 ;
    private String owningNamespace
 ;

    public UIM_ManagedElement() {


    }

   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getCaption() {
        return this.Caption;
    }
    
    public void setCaption(String Caption) {
        this.Caption = Caption;
    }
    public String getDescription() {
        return this.Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public String getDisplayName() {
        return this.DisplayName;
    }
    
    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }
    public Boolean getDeletionAllowed() {
        return this.DeletionAllowed;
    }
    
    public void setDeletionAllowed(Boolean DeletionAllowed) {
        this.DeletionAllowed = DeletionAllowed;
    }
    public Short getMonitoringStatus() {
        return this.MonitoringStatus;
    }
    
    public void setMonitoringStatus(Short MonitoringStatus) {
        this.MonitoringStatus = MonitoringStatus;
    }
    public String getClasstype() {
        return this.classtype;
    }
    
    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }
    public String getOwningNamespace() {
        return this.owningNamespace;
    }
    
    public void setOwningNamespace(String owningNamespace) {
        this.owningNamespace = owningNamespace;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("Caption").append("='").append(getCaption()).append("' ");			
      buffer.append("Description").append("='").append(getDescription()).append("' ");			
      buffer.append("DisplayName").append("='").append(getDisplayName()).append("' ");			
      buffer.append("DeletionAllowed").append("='").append(getDeletionAllowed()).append("' ");			
      buffer.append("MonitoringStatus").append("='").append(getMonitoringStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


