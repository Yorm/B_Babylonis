package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_InstantiationComponent
 * 	@author jkonicki
 */
public class UIM_InstantiationComponent extends com.harris.netboss.common.schema.generation.classes.UIM_ManagedElement implements java.io.Serializable {


    private String UIMClassName
 ;
    private String ManagedElementType
 ;
    private String Package
 ;
    private Boolean Autonomous
 ;
    private String ProviderClassName
 ;

    public UIM_InstantiationComponent() {


    }

   
    public String getUIMClassName() {
        return this.UIMClassName;
    }
    
    public void setUIMClassName(String UIMClassName) {
        this.UIMClassName = UIMClassName;
    }
    public String getManagedElementType() {
        return this.ManagedElementType;
    }
    
    public void setManagedElementType(String ManagedElementType) {
        this.ManagedElementType = ManagedElementType;
    }
    public String getPackage() {
        return this.Package;
    }
    
    public void setPackage(String Package) {
        this.Package = Package;
    }
    public Boolean getAutonomous() {
        return this.Autonomous;
    }
    
    public void setAutonomous(Boolean Autonomous) {
        this.Autonomous = Autonomous;
    }
    public String getProviderClassName() {
        return this.ProviderClassName;
    }
    
    public void setProviderClassName(String ProviderClassName) {
        this.ProviderClassName = ProviderClassName;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("UIMClassName").append("='").append(getUIMClassName()).append("' ");			
      buffer.append("ManagedElementType").append("='").append(getManagedElementType()).append("' ");			
      buffer.append("Package").append("='").append(getPackage()).append("' ");			
      buffer.append("Autonomous").append("='").append(getAutonomous()).append("' ");			
      buffer.append("ProviderClassName").append("='").append(getProviderClassName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


