package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_Port
 * 	@author jkonicki
 */
public class UIM_Port extends com.harris.netboss.common.schema.generation.classes.UIM_ManagedSystemElement implements java.io.Serializable {


    private String TrafficType
  = "other";
    private String MediaType
  = "Unknown";
    private Long MaxOutboundLinks
  = new java.lang.Long(1);
    private Long MaxInboundLinks
  = new java.lang.Long(1);
    private Long OutboundConnections
  = new java.lang.Long(0);
    private Long InboundConnections
  = new java.lang.Long(0);

    public UIM_Port() {


    }

   
    public String getTrafficType() {
        return this.TrafficType;
    }
    
    public void setTrafficType(String TrafficType) {
        this.TrafficType = TrafficType;
    }
    public String getMediaType() {
        return this.MediaType;
    }
    
    public void setMediaType(String MediaType) {
        this.MediaType = MediaType;
    }
    public Long getMaxOutboundLinks() {
        return this.MaxOutboundLinks;
    }
    
    public void setMaxOutboundLinks(Long MaxOutboundLinks) {
        this.MaxOutboundLinks = MaxOutboundLinks;
    }
    public Long getMaxInboundLinks() {
        return this.MaxInboundLinks;
    }
    
    public void setMaxInboundLinks(Long MaxInboundLinks) {
        this.MaxInboundLinks = MaxInboundLinks;
    }
    public Long getOutboundConnections() {
        return this.OutboundConnections;
    }
    
    public void setOutboundConnections(Long OutboundConnections) {
        this.OutboundConnections = OutboundConnections;
    }
    public Long getInboundConnections() {
        return this.InboundConnections;
    }
    
    public void setInboundConnections(Long InboundConnections) {
        this.InboundConnections = InboundConnections;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("TrafficType").append("='").append(getTrafficType()).append("' ");			
      buffer.append("MediaType").append("='").append(getMediaType()).append("' ");			
      buffer.append("MaxOutboundLinks").append("='").append(getMaxOutboundLinks()).append("' ");			
      buffer.append("MaxInboundLinks").append("='").append(getMaxInboundLinks()).append("' ");			
      buffer.append("OutboundConnections").append("='").append(getOutboundConnections()).append("' ");			
      buffer.append("InboundConnections").append("='").append(getInboundConnections()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


