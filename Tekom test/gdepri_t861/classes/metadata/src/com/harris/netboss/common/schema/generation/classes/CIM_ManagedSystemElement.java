package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA


import java.util.Date;

/**
 * Generated Javadocs for CIM_ManagedSystemElement
 * 	@author jkonicki
 */
public class CIM_ManagedSystemElement extends com.harris.netboss.common.schema.generation.classes.CIM_ManagedElement implements java.io.Serializable {


    private Date InstallDate
 ;
    private String Name
 ;
    private Integer[] OperationalStatus
 ;
    private String[] StatusDescriptions
 ;

    public CIM_ManagedSystemElement() {


    }

   
    public Date getInstallDate() {
        return this.InstallDate;
    }
    
    public void setInstallDate(Date InstallDate) {
        this.InstallDate = InstallDate;
    }
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    public Integer[] getOperationalStatus() {
        return this.OperationalStatus;
    }
    
    public void setOperationalStatus(Integer[] OperationalStatus) {
        this.OperationalStatus = OperationalStatus;
    }
    public String[] getStatusDescriptions() {
        return this.StatusDescriptions;
    }
    
    public void setStatusDescriptions(String[] StatusDescriptions) {
        this.StatusDescriptions = StatusDescriptions;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("InstallDate").append("='").append(getInstallDate()).append("' ");			
      buffer.append("Name").append("='").append(getName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


