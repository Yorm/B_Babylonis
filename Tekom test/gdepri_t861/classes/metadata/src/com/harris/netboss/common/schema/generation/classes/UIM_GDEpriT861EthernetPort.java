package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA


import java.math.BigInteger;

/**
 * Generated Javadocs for UIM_GDEpriT861EthernetPort
 * 	@author jkonicki
 */
public class UIM_GDEpriT861EthernetPort extends com.harris.netboss.common.schema.generation.classes.UIM_GenericPort implements java.io.Serializable {


    private Integer MTU
  = new java.lang.Integer(0);
    private BigInteger PortSpeed
  = new BigInteger("0", 10);
    private String PhysicalAddress
  = "n/a";
    private Integer AdminStatus
  = new java.lang.Integer(1);
    private Integer OperStatus
  = new java.lang.Integer(1);
    private String LastChange
  = "n/a";
    private BigInteger InOctets
  = new BigInteger("0", 10);
    private BigInteger InUcastPkts
  = new BigInteger("0", 10);
    private BigInteger InNUcastPkts
  = new BigInteger("0", 10);
    private BigInteger InDiscards
  = new BigInteger("0", 10);
    private BigInteger InErrors
  = new BigInteger("0", 10);
    private BigInteger InUnknownProtos
  = new BigInteger("0", 10);
    private BigInteger OutOctets
  = new BigInteger("0", 10);
    private BigInteger OutUcastPkts
  = new BigInteger("0", 10);
    private BigInteger OutNUcastPkts
  = new BigInteger("0", 10);
    private BigInteger OutDiscards
  = new BigInteger("0", 10);
    private BigInteger OutErrors
  = new BigInteger("0", 10);
    private BigInteger OutQLen
  = new BigInteger("0", 10);
    private String Alias
  = "n/a";
    private Boolean Diagramm
  = new java.lang.Boolean(false);

    public UIM_GDEpriT861EthernetPort() {


    }

   
    public Integer getMTU() {
        return this.MTU;
    }
    
    public void setMTU(Integer MTU) {
        this.MTU = MTU;
    }
    public BigInteger getPortSpeed() {
        return this.PortSpeed;
    }
    
    public void setPortSpeed(BigInteger PortSpeed) {
        this.PortSpeed = PortSpeed;
    }
    public String getPhysicalAddress() {
        return this.PhysicalAddress;
    }
    
    public void setPhysicalAddress(String PhysicalAddress) {
        this.PhysicalAddress = PhysicalAddress;
    }
    public Integer getAdminStatus() {
        return this.AdminStatus;
    }
    
    public void setAdminStatus(Integer AdminStatus) {
        this.AdminStatus = AdminStatus;
    }
    public Integer getOperStatus() {
        return this.OperStatus;
    }
    
    public void setOperStatus(Integer OperStatus) {
        this.OperStatus = OperStatus;
    }
    public String getLastChange() {
        return this.LastChange;
    }
    
    public void setLastChange(String LastChange) {
        this.LastChange = LastChange;
    }
    public BigInteger getInOctets() {
        return this.InOctets;
    }
    
    public void setInOctets(BigInteger InOctets) {
        this.InOctets = InOctets;
    }
    public BigInteger getInUcastPkts() {
        return this.InUcastPkts;
    }
    
    public void setInUcastPkts(BigInteger InUcastPkts) {
        this.InUcastPkts = InUcastPkts;
    }
    public BigInteger getInNUcastPkts() {
        return this.InNUcastPkts;
    }
    
    public void setInNUcastPkts(BigInteger InNUcastPkts) {
        this.InNUcastPkts = InNUcastPkts;
    }
    public BigInteger getInDiscards() {
        return this.InDiscards;
    }
    
    public void setInDiscards(BigInteger InDiscards) {
        this.InDiscards = InDiscards;
    }
    public BigInteger getInErrors() {
        return this.InErrors;
    }
    
    public void setInErrors(BigInteger InErrors) {
        this.InErrors = InErrors;
    }
    public BigInteger getInUnknownProtos() {
        return this.InUnknownProtos;
    }
    
    public void setInUnknownProtos(BigInteger InUnknownProtos) {
        this.InUnknownProtos = InUnknownProtos;
    }
    public BigInteger getOutOctets() {
        return this.OutOctets;
    }
    
    public void setOutOctets(BigInteger OutOctets) {
        this.OutOctets = OutOctets;
    }
    public BigInteger getOutUcastPkts() {
        return this.OutUcastPkts;
    }
    
    public void setOutUcastPkts(BigInteger OutUcastPkts) {
        this.OutUcastPkts = OutUcastPkts;
    }
    public BigInteger getOutNUcastPkts() {
        return this.OutNUcastPkts;
    }
    
    public void setOutNUcastPkts(BigInteger OutNUcastPkts) {
        this.OutNUcastPkts = OutNUcastPkts;
    }
    public BigInteger getOutDiscards() {
        return this.OutDiscards;
    }
    
    public void setOutDiscards(BigInteger OutDiscards) {
        this.OutDiscards = OutDiscards;
    }
    public BigInteger getOutErrors() {
        return this.OutErrors;
    }
    
    public void setOutErrors(BigInteger OutErrors) {
        this.OutErrors = OutErrors;
    }
    public BigInteger getOutQLen() {
        return this.OutQLen;
    }
    
    public void setOutQLen(BigInteger OutQLen) {
        this.OutQLen = OutQLen;
    }
    public String getAlias() {
        return this.Alias;
    }
    
    public void setAlias(String Alias) {
        this.Alias = Alias;
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
      buffer.append("MTU").append("='").append(getMTU()).append("' ");			
      buffer.append("PortSpeed").append("='").append(getPortSpeed()).append("' ");			
      buffer.append("PhysicalAddress").append("='").append(getPhysicalAddress()).append("' ");			
      buffer.append("AdminStatus").append("='").append(getAdminStatus()).append("' ");			
      buffer.append("OperStatus").append("='").append(getOperStatus()).append("' ");			
      buffer.append("LastChange").append("='").append(getLastChange()).append("' ");			
      buffer.append("InOctets").append("='").append(getInOctets()).append("' ");			
      buffer.append("InUcastPkts").append("='").append(getInUcastPkts()).append("' ");			
      buffer.append("InNUcastPkts").append("='").append(getInNUcastPkts()).append("' ");			
      buffer.append("InDiscards").append("='").append(getInDiscards()).append("' ");			
      buffer.append("InErrors").append("='").append(getInErrors()).append("' ");			
      buffer.append("InUnknownProtos").append("='").append(getInUnknownProtos()).append("' ");			
      buffer.append("OutOctets").append("='").append(getOutOctets()).append("' ");			
      buffer.append("OutUcastPkts").append("='").append(getOutUcastPkts()).append("' ");			
      buffer.append("OutNUcastPkts").append("='").append(getOutNUcastPkts()).append("' ");			
      buffer.append("OutDiscards").append("='").append(getOutDiscards()).append("' ");			
      buffer.append("OutErrors").append("='").append(getOutErrors()).append("' ");			
      buffer.append("OutQLen").append("='").append(getOutQLen()).append("' ");			
      buffer.append("Alias").append("='").append(getAlias()).append("' ");			
      buffer.append("Diagramm").append("='").append(getDiagramm()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


