package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA


import java.math.BigInteger;

/**
 * Generated Javadocs for UIM_GDEpriT861Port
 * 	@author jkonicki
 */
public class UIM_GDEpriT861Port extends com.harris.netboss.common.schema.generation.classes.UIM_GenericLogicalDevice implements java.io.Serializable {


    private Boolean Diagramm
  = new java.lang.Boolean(false);
    private Integer LinkStatus
  = new java.lang.Integer(0);
    private Integer DuplexStatus
  = new java.lang.Integer(0);
    private Integer PortSpeed
  = new java.lang.Integer(0);
    private Integer PortMedia
  = new java.lang.Integer(0);
    private Integer MediaMode
  = new java.lang.Integer(0);
    private Integer SpeedMode
  = new java.lang.Integer(0);
    private Integer DuplexMode
  = new java.lang.Integer(0);
    private Integer PortDisable
  = new java.lang.Integer(0);
    private Integer FlowCtrl
  = new java.lang.Integer(0);
    private Integer EgressRate
  = new java.lang.Integer(0);
    private Integer PackType
  = new java.lang.Integer(0);
    private Integer LowProbandwidth
  = new java.lang.Integer(0);
    private Integer NormalProbandwidth
  = new java.lang.Integer(0);
    private Integer MediumProbandwidth
  = new java.lang.Integer(0);
    private Integer HighProbandwidth
  = new java.lang.Integer(0);
    private Integer PortType
  = new java.lang.Integer(0);
    private Integer PortVID
  = new java.lang.Integer(0);
    private BigInteger InGoodOctets
  = new BigInteger("0", 10);
    private BigInteger InBadOctets
  = new BigInteger("0", 10);
    private BigInteger InUnicasts
  = new BigInteger("0", 10);
    private BigInteger InBroadCasts
  = new BigInteger("0", 10);
    private BigInteger InMultiCasts
  = new BigInteger("0", 10);
    private BigInteger InPause
  = new BigInteger("0", 10);
    private BigInteger InUndersize
  = new BigInteger("0", 10);
    private BigInteger InFragments
  = new BigInteger("0", 10);
    private BigInteger InOversize
  = new BigInteger("0", 10);
    private BigInteger InJabber
  = new BigInteger("0", 10);
    private BigInteger InRxErr
  = new BigInteger("0", 10);
    private BigInteger InFCSErr
  = new BigInteger("0", 10);
    private BigInteger OutOctets
  = new BigInteger("0", 10);
    private BigInteger OutUnicast
  = new BigInteger("0", 10);
    private BigInteger OutBroadCasts
  = new BigInteger("0", 10);
    private BigInteger OutMulticasts
  = new BigInteger("0", 10);
    private BigInteger OutPause
  = new BigInteger("0", 10);
    private BigInteger Excessive
  = new BigInteger("0", 10);
    private BigInteger Collisions
  = new BigInteger("0", 10);
    private BigInteger Deferred
  = new BigInteger("0", 10);
    private BigInteger Single
  = new BigInteger("0", 10);
    private BigInteger Multple
  = new BigInteger("0", 10);
    private BigInteger Late
  = new BigInteger("0", 10);

    public UIM_GDEpriT861Port() {


    }

   
    public Boolean getDiagramm() {
        return this.Diagramm;
    }
    
    public void setDiagramm(Boolean Diagramm) {
        this.Diagramm = Diagramm;
    }
    public Integer getLinkStatus() {
        return this.LinkStatus;
    }
    
    public void setLinkStatus(Integer LinkStatus) {
        this.LinkStatus = LinkStatus;
    }
    public Integer getDuplexStatus() {
        return this.DuplexStatus;
    }
    
    public void setDuplexStatus(Integer DuplexStatus) {
        this.DuplexStatus = DuplexStatus;
    }
    public Integer getPortSpeed() {
        return this.PortSpeed;
    }
    
    public void setPortSpeed(Integer PortSpeed) {
        this.PortSpeed = PortSpeed;
    }
    public Integer getPortMedia() {
        return this.PortMedia;
    }
    
    public void setPortMedia(Integer PortMedia) {
        this.PortMedia = PortMedia;
    }
    public Integer getMediaMode() {
        return this.MediaMode;
    }
    
    public void setMediaMode(Integer MediaMode) {
        this.MediaMode = MediaMode;
    }
    public Integer getSpeedMode() {
        return this.SpeedMode;
    }
    
    public void setSpeedMode(Integer SpeedMode) {
        this.SpeedMode = SpeedMode;
    }
    public Integer getDuplexMode() {
        return this.DuplexMode;
    }
    
    public void setDuplexMode(Integer DuplexMode) {
        this.DuplexMode = DuplexMode;
    }
    public Integer getPortDisable() {
        return this.PortDisable;
    }
    
    public void setPortDisable(Integer PortDisable) {
        this.PortDisable = PortDisable;
    }
    public Integer getFlowCtrl() {
        return this.FlowCtrl;
    }
    
    public void setFlowCtrl(Integer FlowCtrl) {
        this.FlowCtrl = FlowCtrl;
    }
    public Integer getEgressRate() {
        return this.EgressRate;
    }
    
    public void setEgressRate(Integer EgressRate) {
        this.EgressRate = EgressRate;
    }
    public Integer getPackType() {
        return this.PackType;
    }
    
    public void setPackType(Integer PackType) {
        this.PackType = PackType;
    }
    public Integer getLowProbandwidth() {
        return this.LowProbandwidth;
    }
    
    public void setLowProbandwidth(Integer LowProbandwidth) {
        this.LowProbandwidth = LowProbandwidth;
    }
    public Integer getNormalProbandwidth() {
        return this.NormalProbandwidth;
    }
    
    public void setNormalProbandwidth(Integer NormalProbandwidth) {
        this.NormalProbandwidth = NormalProbandwidth;
    }
    public Integer getMediumProbandwidth() {
        return this.MediumProbandwidth;
    }
    
    public void setMediumProbandwidth(Integer MediumProbandwidth) {
        this.MediumProbandwidth = MediumProbandwidth;
    }
    public Integer getHighProbandwidth() {
        return this.HighProbandwidth;
    }
    
    public void setHighProbandwidth(Integer HighProbandwidth) {
        this.HighProbandwidth = HighProbandwidth;
    }
    public Integer getPortType() {
        return this.PortType;
    }
    
    public void setPortType(Integer PortType) {
        this.PortType = PortType;
    }
    public Integer getPortVID() {
        return this.PortVID;
    }
    
    public void setPortVID(Integer PortVID) {
        this.PortVID = PortVID;
    }
    public BigInteger getInGoodOctets() {
        return this.InGoodOctets;
    }
    
    public void setInGoodOctets(BigInteger InGoodOctets) {
        this.InGoodOctets = InGoodOctets;
    }
    public BigInteger getInBadOctets() {
        return this.InBadOctets;
    }
    
    public void setInBadOctets(BigInteger InBadOctets) {
        this.InBadOctets = InBadOctets;
    }
    public BigInteger getInUnicasts() {
        return this.InUnicasts;
    }
    
    public void setInUnicasts(BigInteger InUnicasts) {
        this.InUnicasts = InUnicasts;
    }
    public BigInteger getInBroadCasts() {
        return this.InBroadCasts;
    }
    
    public void setInBroadCasts(BigInteger InBroadCasts) {
        this.InBroadCasts = InBroadCasts;
    }
    public BigInteger getInMultiCasts() {
        return this.InMultiCasts;
    }
    
    public void setInMultiCasts(BigInteger InMultiCasts) {
        this.InMultiCasts = InMultiCasts;
    }
    public BigInteger getInPause() {
        return this.InPause;
    }
    
    public void setInPause(BigInteger InPause) {
        this.InPause = InPause;
    }
    public BigInteger getInUndersize() {
        return this.InUndersize;
    }
    
    public void setInUndersize(BigInteger InUndersize) {
        this.InUndersize = InUndersize;
    }
    public BigInteger getInFragments() {
        return this.InFragments;
    }
    
    public void setInFragments(BigInteger InFragments) {
        this.InFragments = InFragments;
    }
    public BigInteger getInOversize() {
        return this.InOversize;
    }
    
    public void setInOversize(BigInteger InOversize) {
        this.InOversize = InOversize;
    }
    public BigInteger getInJabber() {
        return this.InJabber;
    }
    
    public void setInJabber(BigInteger InJabber) {
        this.InJabber = InJabber;
    }
    public BigInteger getInRxErr() {
        return this.InRxErr;
    }
    
    public void setInRxErr(BigInteger InRxErr) {
        this.InRxErr = InRxErr;
    }
    public BigInteger getInFCSErr() {
        return this.InFCSErr;
    }
    
    public void setInFCSErr(BigInteger InFCSErr) {
        this.InFCSErr = InFCSErr;
    }
    public BigInteger getOutOctets() {
        return this.OutOctets;
    }
    
    public void setOutOctets(BigInteger OutOctets) {
        this.OutOctets = OutOctets;
    }
    public BigInteger getOutUnicast() {
        return this.OutUnicast;
    }
    
    public void setOutUnicast(BigInteger OutUnicast) {
        this.OutUnicast = OutUnicast;
    }
    public BigInteger getOutBroadCasts() {
        return this.OutBroadCasts;
    }
    
    public void setOutBroadCasts(BigInteger OutBroadCasts) {
        this.OutBroadCasts = OutBroadCasts;
    }
    public BigInteger getOutMulticasts() {
        return this.OutMulticasts;
    }
    
    public void setOutMulticasts(BigInteger OutMulticasts) {
        this.OutMulticasts = OutMulticasts;
    }
    public BigInteger getOutPause() {
        return this.OutPause;
    }
    
    public void setOutPause(BigInteger OutPause) {
        this.OutPause = OutPause;
    }
    public BigInteger getExcessive() {
        return this.Excessive;
    }
    
    public void setExcessive(BigInteger Excessive) {
        this.Excessive = Excessive;
    }
    public BigInteger getCollisions() {
        return this.Collisions;
    }
    
    public void setCollisions(BigInteger Collisions) {
        this.Collisions = Collisions;
    }
    public BigInteger getDeferred() {
        return this.Deferred;
    }
    
    public void setDeferred(BigInteger Deferred) {
        this.Deferred = Deferred;
    }
    public BigInteger getSingle() {
        return this.Single;
    }
    
    public void setSingle(BigInteger Single) {
        this.Single = Single;
    }
    public BigInteger getMultple() {
        return this.Multple;
    }
    
    public void setMultple(BigInteger Multple) {
        this.Multple = Multple;
    }
    public BigInteger getLate() {
        return this.Late;
    }
    
    public void setLate(BigInteger Late) {
        this.Late = Late;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("Diagramm").append("='").append(getDiagramm()).append("' ");			
      buffer.append("LinkStatus").append("='").append(getLinkStatus()).append("' ");			
      buffer.append("DuplexStatus").append("='").append(getDuplexStatus()).append("' ");			
      buffer.append("PortSpeed").append("='").append(getPortSpeed()).append("' ");			
      buffer.append("PortMedia").append("='").append(getPortMedia()).append("' ");			
      buffer.append("MediaMode").append("='").append(getMediaMode()).append("' ");			
      buffer.append("SpeedMode").append("='").append(getSpeedMode()).append("' ");			
      buffer.append("DuplexMode").append("='").append(getDuplexMode()).append("' ");			
      buffer.append("PortDisable").append("='").append(getPortDisable()).append("' ");			
      buffer.append("FlowCtrl").append("='").append(getFlowCtrl()).append("' ");			
      buffer.append("EgressRate").append("='").append(getEgressRate()).append("' ");			
      buffer.append("PackType").append("='").append(getPackType()).append("' ");			
      buffer.append("LowProbandwidth").append("='").append(getLowProbandwidth()).append("' ");			
      buffer.append("NormalProbandwidth").append("='").append(getNormalProbandwidth()).append("' ");			
      buffer.append("MediumProbandwidth").append("='").append(getMediumProbandwidth()).append("' ");			
      buffer.append("HighProbandwidth").append("='").append(getHighProbandwidth()).append("' ");			
      buffer.append("PortType").append("='").append(getPortType()).append("' ");			
      buffer.append("PortVID").append("='").append(getPortVID()).append("' ");			
      buffer.append("InGoodOctets").append("='").append(getInGoodOctets()).append("' ");			
      buffer.append("InBadOctets").append("='").append(getInBadOctets()).append("' ");			
      buffer.append("InUnicasts").append("='").append(getInUnicasts()).append("' ");			
      buffer.append("InBroadCasts").append("='").append(getInBroadCasts()).append("' ");			
      buffer.append("InMultiCasts").append("='").append(getInMultiCasts()).append("' ");			
      buffer.append("InPause").append("='").append(getInPause()).append("' ");			
      buffer.append("InUndersize").append("='").append(getInUndersize()).append("' ");			
      buffer.append("InFragments").append("='").append(getInFragments()).append("' ");			
      buffer.append("InOversize").append("='").append(getInOversize()).append("' ");			
      buffer.append("InJabber").append("='").append(getInJabber()).append("' ");			
      buffer.append("InRxErr").append("='").append(getInRxErr()).append("' ");			
      buffer.append("InFCSErr").append("='").append(getInFCSErr()).append("' ");			
      buffer.append("OutOctets").append("='").append(getOutOctets()).append("' ");			
      buffer.append("OutUnicast").append("='").append(getOutUnicast()).append("' ");			
      buffer.append("OutBroadCasts").append("='").append(getOutBroadCasts()).append("' ");			
      buffer.append("OutMulticasts").append("='").append(getOutMulticasts()).append("' ");			
      buffer.append("OutPause").append("='").append(getOutPause()).append("' ");			
      buffer.append("Excessive").append("='").append(getExcessive()).append("' ");			
      buffer.append("Collisions").append("='").append(getCollisions()).append("' ");			
      buffer.append("Deferred").append("='").append(getDeferred()).append("' ");			
      buffer.append("Single").append("='").append(getSingle()).append("' ");			
      buffer.append("Multple").append("='").append(getMultple()).append("' ");			
      buffer.append("Late").append("='").append(getLate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


