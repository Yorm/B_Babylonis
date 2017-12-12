package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_GDEpriT861NetworkElement
 * 	@author jkonicki
 */
public class UIM_GDEpriT861NetworkElement extends com.harris.netboss.common.schema.generation.classes.UIM_GenericNetworkElement implements java.io.Serializable {


    private String Script
 ;
    private String SystemUpTimeTxt
 ;
    private String HardwareVersion
 ;
    private String SoftwareVersion
 ;
    private Integer StackFlag
  = new java.lang.Integer(0);
    private String BootImgPrimary
 ;
    private String BootImgSecondary
 ;
    private String BootStartUpCfg
 ;
    private Integer StartUpStatus
  = new java.lang.Integer(0);
    private Integer NumberOfPorts
  = new java.lang.Integer(0);
    private Boolean Diagramm
  = new java.lang.Boolean(false);

    public UIM_GDEpriT861NetworkElement() {


    }

   
    public String getScript() {
        return this.Script;
    }
    
    public void setScript(String Script) {
        this.Script = Script;
    }
    public String getSystemUpTimeTxt() {
        return this.SystemUpTimeTxt;
    }
    
    public void setSystemUpTimeTxt(String SystemUpTimeTxt) {
        this.SystemUpTimeTxt = SystemUpTimeTxt;
    }
    public String getHardwareVersion() {
        return this.HardwareVersion;
    }
    
    public void setHardwareVersion(String HardwareVersion) {
        this.HardwareVersion = HardwareVersion;
    }
    public String getSoftwareVersion() {
        return this.SoftwareVersion;
    }
    
    public void setSoftwareVersion(String SoftwareVersion) {
        this.SoftwareVersion = SoftwareVersion;
    }
    public Integer getStackFlag() {
        return this.StackFlag;
    }
    
    public void setStackFlag(Integer StackFlag) {
        this.StackFlag = StackFlag;
    }
    public String getBootImgPrimary() {
        return this.BootImgPrimary;
    }
    
    public void setBootImgPrimary(String BootImgPrimary) {
        this.BootImgPrimary = BootImgPrimary;
    }
    public String getBootImgSecondary() {
        return this.BootImgSecondary;
    }
    
    public void setBootImgSecondary(String BootImgSecondary) {
        this.BootImgSecondary = BootImgSecondary;
    }
    public String getBootStartUpCfg() {
        return this.BootStartUpCfg;
    }
    
    public void setBootStartUpCfg(String BootStartUpCfg) {
        this.BootStartUpCfg = BootStartUpCfg;
    }
    public Integer getStartUpStatus() {
        return this.StartUpStatus;
    }
    
    public void setStartUpStatus(Integer StartUpStatus) {
        this.StartUpStatus = StartUpStatus;
    }
    public Integer getNumberOfPorts() {
        return this.NumberOfPorts;
    }
    
    public void setNumberOfPorts(Integer NumberOfPorts) {
        this.NumberOfPorts = NumberOfPorts;
    }
    public Boolean getDiagramm() {
        return this.Diagramm;
    }
    
    public void setDiagramm(Boolean Diagramm) {
        this.Diagramm = Diagramm;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("Script").append("='").append(getScript()).append("' ");			
      buffer.append("SystemUpTimeTxt").append("='").append(getSystemUpTimeTxt()).append("' ");			
      buffer.append("HardwareVersion").append("='").append(getHardwareVersion()).append("' ");			
      buffer.append("SoftwareVersion").append("='").append(getSoftwareVersion()).append("' ");			
      buffer.append("StackFlag").append("='").append(getStackFlag()).append("' ");			
      buffer.append("BootImgPrimary").append("='").append(getBootImgPrimary()).append("' ");			
      buffer.append("BootImgSecondary").append("='").append(getBootImgSecondary()).append("' ");			
      buffer.append("BootStartUpCfg").append("='").append(getBootStartUpCfg()).append("' ");			
      buffer.append("StartUpStatus").append("='").append(getStartUpStatus()).append("' ");			
      buffer.append("NumberOfPorts").append("='").append(getNumberOfPorts()).append("' ");			
      buffer.append("Diagramm").append("='").append(getDiagramm()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


