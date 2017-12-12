package com.harris.netboss.common.schema.generation.classes;
// Generated 27.11.2017 10:39:04 by Hibernate Tools 3.2.4.GA



/**
 * Generated Javadocs for UIM_GDEpriT861VlansGrouping
 * 	@author jkonicki
 */
public class UIM_GDEpriT861VlansGrouping extends com.harris.netboss.common.schema.generation.classes.UIM_GenericGrouping implements java.io.Serializable {


    private Boolean Diagramm
  = new java.lang.Boolean(false);

    public UIM_GDEpriT861VlansGrouping() {


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
      buffer.append("Diagramm").append("='").append(getDiagramm()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


