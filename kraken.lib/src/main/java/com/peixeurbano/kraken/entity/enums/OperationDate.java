package com.peixeurbano.kraken.entity.enums;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public enum OperationDate {

	NESTA_FAIXA_DATAS(0),
	NAO_NESTA_FAIXA_DATAS(1),	
	/*************************/
	NESTA_FAIXA_PERIODO(2),
	NAO_NESTA_FAIXA_PERIODO(3);	

    private int valor;
    private String label;

    private OperationDate(final int valor) {
        
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }
    
    public String getLabel() {
    	
    	switch (this.getValor()) {
    	
         case 0:  this.label = "Está faixa de Datas";
         		  break;	
         case 1:  this.label = "Não nesta faixa de Datas";
		  		  break;         
         case 2:  this.label = "Está faixa de Período";
         		  break;
         default: this.label = "Não nesta faixa de Período";
                 
    	}
		return this.label;
	}
  
/*    
	public String getOperation(final Date dateIni, final Date dateFim){
    	
    	switch (this.getValor()) {
    	
         case 0:  return "between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateFim)).toString()+"'";
              
         default:  return "not between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateFim)).toString()+"'";
                 
    	}
    	
    }
    
    public String getOperation(final int perIni, final int perFim){
    	
    	Date dateIni=(new DateTime()).minusDays(perIni).toDate(); 
    	Date dateFim=(new DateTime()).minusDays(perFim).toDate();
    	
    	switch (this.getValor()) {
    	
         case 2:  return "between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateFim)).toString()+"'";
              
         default:  return "not between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateFim)).toString()+"'";
                 
    	}
    	
    }
  */
    
    public String getOperation(final Long perIni, final Long perFim){
    	
    	Date dateIni=new Date(perIni);
    	Date dateFim=new Date(perFim);
    	
    	Date dateTimeIni=(new DateTime()).minusDays(perFim.intValue()).toDate(); 
    	Date dateTimeFim=(new DateTime()).minusDays(perIni.intValue()).toDate();    	
    	
    	switch (this.getValor()) {
    	
        	case 0:  return "between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateFim)).toString()+"'";             
        	case 1:  return "not between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateFim)).toString()+"'";
        	case 2:  return "between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateTimeIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateTimeFim)).toString()+"'";
        	case 3:  return "not between '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateTimeIni)).toString()+"' and '"+(new SimpleDateFormat("yyyy-MM-dd").format(dateTimeFim)).toString()+"'";
        	default: return "";

    	}        
        
    }
    
    public String getDescription(final Long perIni, final Long perFim){
    	
    	if((perIni<perFim)){
    	
	    	switch (this.getValor()) {
	    	
	    		case 0:  return "Entre "+(new SimpleDateFormat("yyyy/MM/dd").format(new Date(perIni))).toString()+"-"+(new SimpleDateFormat("yyyy/MM/dd").format(new Date(perFim))).toString();
	        
	    		case 1:  return "Fora de "+(new SimpleDateFormat("yyyy/MM/dd").format(new Date(perIni))).toString()+"-"+(new SimpleDateFormat("yyyy/MM/dd").format(new Date(perFim))).toString();
	    	
	    		case 2:  return "Entre "+perIni+" e "+perFim;
	              
	    		default:  return "Fora de "+perIni+" e "+perFim;
	                 
	    	}
    	}else{
    		return "";
    	}
    	
    	
    }    
    
}
