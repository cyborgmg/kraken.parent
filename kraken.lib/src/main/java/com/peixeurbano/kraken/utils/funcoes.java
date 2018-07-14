package com.peixeurbano.kraken.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.NullComparator;

import com.peixeurbano.kraken.entity.enums.BooleanEnum;

public class funcoes {
	
	public static Boolean inttobool(final int i) {		
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
	
	public static Object nvl(final Object o1, final Object o2) {		
		if(o1==null){
			return o2;
		}else{
			return o1;
		}
	}
	
	public static String normalize(final String str) { 
		  char[] FIRST_CHAR =  
			       (" !'#$%&'()*+\\-./0123456789:;<->?@ABCDEFGHIJKLMNOPQRSTUVWXYZ"  
			          + "[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ E ,f'.++^%S<O Z  ''''.-"  
			          + "-~Ts>o ZY !C#$Y|$'(a<--(_o+23'u .,1o>113?AAAAAAACEEEEIIIIDNOO"  
			          + "OOOXOUUUUyTsaaaaaaaceeeeiiiidnooooo/ouuuuyty")  
			          .toCharArray();  
		  char[] SECOND_CHAR =  
			       ("  '         ,                                               "  
			          + "\\                                   $  r'. + o  E      ''  "  
			          + "  M  e     #  =  'C.<  R .-..     ..>424     E E            "  
			          + "   E E     hs    e e         h     e e     h ")  
			          .toCharArray();  		  
			  
		       char[] chars = str.toCharArray();  
		       StringBuffer ret = new StringBuffer(chars.length * 2);  
		       for (int i = 0; i < chars.length; ++i) {  
		          char aChar = chars[i];  
		          if (aChar == ' ' || aChar == '\t') {  
		             ret.append(' '); // convertido para espaço  
		          } else if (aChar > ' ' && aChar < '\u0100') {  
		             if (FIRST_CHAR[aChar - ' '] != ' ') {  
		                ret.append(FIRST_CHAR[aChar - ' '] ) ; // 1 caracter  
		             }  
		             if (SECOND_CHAR[aChar - ' '] != ' ') {  
		                ret.append(SECOND_CHAR[aChar - ' '] ) ; // 2 caracteres  
		             }  
		          }  
		       }  
		   
		     return ret.toString();  
	}

private static ArrayList<String> lstalfa(){
		ArrayList<String> letras = new ArrayList<String>();		
		letras.add("A");
		letras.add("Q");
		letras.add("W");
		letras.add("E");
		letras.add("R");
		letras.add("T");
		letras.add("Y");
		letras.add("U");
		letras.add("I");
		letras.add("O");
		letras.add("P");
		letras.add("A");
		letras.add("S");
		letras.add("D");
		letras.add("F");
		letras.add("G");
		letras.add("H");
		letras.add("J");
		letras.add("K");
		letras.add("L");
		letras.add("�");
		letras.add("Z");
		letras.add("X");
		letras.add("C");
		letras.add("V");
		letras.add("B");
		letras.add("N");
		letras.add("M");
		letras.add(" ");
return letras;		
}		

private static ArrayList<String> lstnum(){
	ArrayList<String> letras = new ArrayList<String>();		
	letras.add("0");
	letras.add("9");
	letras.add("8");
	letras.add("7");
	letras.add("6");
	letras.add("5");
	letras.add("4");
	letras.add("3");
	letras.add("2");
	letras.add("1");
return letras;		
}	

private static ArrayList<String> lstall(){
	ArrayList<String> letras = new ArrayList<String>();		
	letras.addAll(lstalfa());
	letras.addAll(lstnum());	
return letras;		
}		
	
private static boolean filter(final String ch, final ArrayList<String> letras){

	try {
		for (int i = 0; i < letras.size(); i++) {
		  if( ch.equals(letras.get(i)) ){
			  return true;
		  }
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
return false;	
}

public static String clearstring(final String pl){
String aux="";	
	
 for (int i = 0; i < pl.length(); i++) {
	
	   if( filter( pl.toUpperCase().substring(i, i+1) ,lstall()) ){
		   aux+=pl.substring(i, i+1);
	   }
 }	
 
return aux;
}

public static String justalfa(final String pl){
	String aux="";	
		
	   for (int i = 0; i < pl.length(); i++) {
		
		   if( filter( pl.toUpperCase().substring(i, i+1) ,lstalfa()) ){
			   aux+=pl.substring(i, i+1);
		   }
	   }	
	   
	return aux;
}

public static String justNumeric(final String pl){
	String aux="";	
		
	   for (int i = 0; i < pl.length(); i++) {
		
		   if( filter( pl.toUpperCase().substring(i, i+1) ,lstnum()) ){
			   aux+=pl.substring(i, i+1);
		   }
	   }	
	   
	return aux;
}

public static List parseEsp(final String fraze){
		String aux = "";	
		List lst = new ArrayList();  

			
			for (int i = 0; i < fraze.trim().length(); i++) {
				
				if( fraze.trim().charAt(i)==' '){
					lst.add( clearstring(normalize(aux)) );
					aux="";
				}else{
					aux+=fraze.trim().charAt(i);			
				}
			}
			lst.add(clearstring(normalize(aux)));
			
		return lst;
	}
	
	public static String fContainsStr(final String str) {
	List<String> frase = parseEsp(stopword(str));
	String aux="";
	
		for (String string : frase) {
			if(string.length()>2){
				aux+=" "+string;
			}
		}
		
	return aux.substring(1);
	}

	
	public static void order(final List l, final String campo) {
		order(l, campo, false);
	}
	public static void order(final List l, final String campo, final Boolean desc) {
	/*ORDENA�AO**********************************************************************/
		ComparatorChain comp = new ComparatorChain();  	
		comp.addComparator(new BeanComparator(campo, new NullComparator(true)), desc);
		Collections.sort(l, comp);		
		comp=null;
	}
	
	
	  /** 
     * Efetua as seguintes normaliza��es: 
     * - Elimina acentos e cedilhas dos nomes; 
     * - Converte aspas duplas em simples; 
     * - Converte algumas letras estrangeiras para seus equivalentes ASCII 
     * (como � = eszet, convertido para ss)  
     * - P�e um "\" antes de v�rgulas, permitindo nomes como 
     * "Verisign, Corp." e de "\", permitindo nomes como " a \ b "; 
     * - Converte os sinais de = para - 
     * - Alguns caracteres s�o removidos: 
     * -> os superiores a 255, 
     * mesmo que possam ser representados por letras latinas normais 
     * (como S, = U+015E = Latin Capital Letter S With Cedilla); 
     * -> os caracteres de controle (exceto tab, que � trocado por um espa�o) 
     * @param str A string a normalizar. 
     * @return A string normalizada. 
     */
	 
	  public static String lpad(String valueToPad, final String filler, final int size) {  
	         while (valueToPad.length() < size) {  
	             valueToPad = filler + valueToPad;  
	         }  
	    return valueToPad;  
	  }  
	       
	  public static String rpad(String valueToPad, final String filler, final int size) {  
	         while (valueToPad.length() < size) {  
	             valueToPad = valueToPad+filler;  
	         }  
	    return valueToPad;  
	  }  
 
	public static List cloneList(final List lst){
	List lstc= new ArrayList();	
		for (int i = 0; i < lst.size(); i++) {
			lstc.add(lst.get(i));
		}
		return lstc;
	}

	
	public static int media_letra(final String pl, String tst){
	String aux = "";
	String teste = tst;
	int cnt_dif=0;	
	char cp1=' ';
	char cp2=' ';
	int result=0;

		try {
			for (int i = 0; i < pl.length() ; i++) {			
				
				for (int x = 0; x < tst.length(); x++) {
					
					if(pl.length()-1>=i){
						cp1=pl.charAt(i);
					}else{
						cp1=' ';
					}
					
					if(tst.length()-1>=x){
						cp2=tst.charAt(x);
					}else{	
						cp2=' ';
					}
					
					if( cp1 == cp2 ){
						
						aux += pl.charAt(i);
						tst= tst.substring(0, x-1<0?0:x-1)+tst.substring(x+1, tst.length());
						
						break;
					}
					
				}	
				
			}
			
			result =  (( (100*(teste.length() - Math.abs( teste.length()- aux.length() )))/teste.length()  )+
					(  (100*(pl.length() - Math.abs( pl.length()- aux.length() )))/pl.length()  ))/2;
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
	return result;	
	}
	
	public static String stopword(final String pl){
	String palavra=pl;
	ArrayList<String> stpw = new ArrayList<String>();
	stpw.add(" a ");
	stpw.add(" ao ");
	stpw.add(" aos ");
	stpw.add(" as ");
	stpw.add(" ate ");
	stpw.add(" com ");
	stpw.add(" da ");
	stpw.add(" das ");
	stpw.add(" de ");
	stpw.add(" do ");
	stpw.add(" dos ");
	stpw.add(" e ");
	stpw.add(" ela ");
	stpw.add(" ele ");
	stpw.add(" em ");
	stpw.add(" ha ");
	stpw.add(" ja ");
	stpw.add(" mas ");
	stpw.add(" na ");
	stpw.add(" nas ");
	stpw.add(" no ");
	stpw.add(" nos ");
	stpw.add(" o ");
	stpw.add(" os ");
	stpw.add(" ou ");
	stpw.add(" por ");
	stpw.add(" que ");
	stpw.add(" se ");
	stpw.add(" sem ");
	stpw.add(" ser ");
	stpw.add(" seu ");
	stpw.add(" seus ");
	stpw.add(" so ");
	stpw.add(" sua ");
	stpw.add(" tem ");
	stpw.add(" ter ");
	stpw.add(" um ");
	stpw.add(" uma ");
	stpw.add(" us ");
	/**********ingles*************************/
	stpw.add(" I ");
	stpw.add(" a ");
	stpw.add(" about ");
	stpw.add(" an ");
	stpw.add(" are ");
	stpw.add(" as ");
	stpw.add(" at ");
	stpw.add(" be ");
	stpw.add(" by ");
	stpw.add(" com ");
	stpw.add(" de ");
	stpw.add(" en ");
	stpw.add(" for ");
	stpw.add(" from ");
	stpw.add(" how ");
	stpw.add(" in ");
	stpw.add(" is ");
	stpw.add(" it ");
	stpw.add(" la ");
	stpw.add(" of ");
	stpw.add(" on ");
	stpw.add(" or ");
	stpw.add(" that ");
	stpw.add(" the ");
	stpw.add(" this ");
	stpw.add(" to ");
	stpw.add(" was ");
	stpw.add(" what ");
	stpw.add(" when ");
	stpw.add(" where ");
	stpw.add(" who ");
	stpw.add(" will ");
	stpw.add(" with ");
	stpw.add(" und ");
	stpw.add(" the ");
	stpw.add(" www ");
	stpw.add(" and ");

	 for (int i = 0; i < stpw.size(); i++) {
		 palavra = palavra.toUpperCase().replaceAll(stpw.get(i).toUpperCase()," ");
	 }
		
	return palavra;
	}

	public static String setwithin(final String pl){
	String frase="";
	List slst = parseEsp(stopword(pl));
		for (int i = 0; i < slst.size(); i++) {
			frase=frase+"!{"+slst.get(i)+"} & ";
		}
	return frase.substring(0,(frase.length()-3));
	}

	public static String setlike(final String pl){
	String frase="%";
		try {
			List slst = parseEsp(stopword(pl));
				for (int i = 0; i < slst.size(); i++) {
					frase=frase+slst.get(i)+"%";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return frase;
	}

	public static void PropertysGet(final Object obj){
	Class[] NO_PARAM = new Class[] { };
				
		        try {
					if(obj!=null)
					for (int i = 0; i < obj.getClass().getMethods().length ; i++) {					
					   if((obj.getClass().getMethods()[i].getReturnType()!=Set.class)&&(obj.getClass().getMethods()[i].getReturnType()!=Class.class)&&
						  (obj.getClass().getMethods()[i].getName().substring(0,3).equals("get"))){						   					   
						   if( !obj.getClass().getMethods()[i].getName().substring(3).toLowerCase().equals("id") ){ 			   
						        try {			   
							        obj.getClass().getMethods()[i].invoke(obj, NO_PARAM );
						        }catch (Exception e) {}			   
						   }		   			   
					   }    
					}
				} catch (SecurityException e) {		
					e.printStackTrace();
				}		
	}
	
	public static void compareObjectsPropertys(final Object obj1){
	Class[] NO_PARAM = new Class[] { };
				
		        try {
					if(obj1!=null)
					for (int i = 0; i < obj1.getClass().getMethods().length ; i++) {					
					   if((obj1.getClass().getMethods()[i].getReturnType()!=Set.class)&&(obj1.getClass().getMethods()[i].getReturnType()!=Class.class)&&
						  (obj1.getClass().getMethods()[i].getName().substring(0,3).equals("get"))){						   					   
						   if( !obj1.getClass().getMethods()[i].getName().substring(3).toLowerCase().equals("id") ){ 			   
						        try {			   
						        	
							        System.out.println(obj1.getClass().getMethods()[i].invoke(obj1, NO_PARAM ));
							        
						        }catch (Exception e) {}			   
						   }		   			   
					   }    
					}
				} catch (SecurityException e) {		
					e.printStackTrace();
				}		
	}
	
	public static Object clone(final Object objToClone)
    {
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(100);
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
            objectoutputstream.writeObject(objToClone);
            byte abyte0[] = bytearrayoutputstream.toByteArray();
            objectoutputstream.close();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
            ObjectInputStream objectinputstream = new ObjectInputStream(bytearrayinputstream);
            Object clone = objectinputstream.readObject();
            objectinputstream.close();
            return clone;
        }
        catch (Exception exception)
        {
            // nothing
        }
        return null;
    }

	
	
	/**********************************************************************************************/	

	
	public static BooleanEnum convertTobooleanEnum(final boolean bl){
		return bl?BooleanEnum.VERDADEIRO:BooleanEnum.FALSO;
	}
/*	
	public static StringBuilder getQry(final CampaignRequest campaignrequest, final Boolean count ){
		
		StringBuilder qry = new StringBuilder();
		
		qry.append(" select "+(count?"count(*)":"distinct new CampaignUseraccount(u)")+" from Useraccount u where \n");
		qry.append(" u.channelid = 1 and u.status between 1 and 2 \n");
		
		if(campaignrequest.getCampaign().getFacebookCheck()){
			qry.append(" and u.usesfacebooklogin = "+campaignrequest.getCampaign().getFacebook().getValor()+" \n");
		}
		if(campaignrequest.getCampaign().getCheckAppCheck()){
			qry.append(" and (select count(1) from Sale s where u.useraccountid=s.useraccountid and s.sourceid = 2)"+(campaignrequest.getCampaign().getCheckApp().getValor()==0?"=":">")+"0 \n");
		}
		if(campaignrequest.getCampaign().getCheckClientCheck()){
			qry.append(" and u.datefirstpurchase is "+ (campaignrequest.getCampaign().getCheckClient().getValor()==0?"not":"") +" null \n");
		}
		if(campaignrequest.getCampaign().getSexoCheck()){
			qry.append(" and u.female = "+(campaignrequest.getCampaign().getSexo().getValor()=="M"?"0":"1")+" \n");
		}
		if(campaignrequest.getCampaign().getCheckvlpresenteCheck()){
			qry.append(" and ( select count(1) from Sale s where s.useraccountid=u.useraccountid and s.promocodes > 0 )"+( campaignrequest.getCampaign().getCheckvlpresente().getValor()==0?"=":">" )+"0 \n");
		}
		*//******ENGAJAMENTO*****************//*
		if(campaignrequest.getEngagement().getCheck()){
			qry.append(" and u.engagement in "+campaignrequest.getEngagement().restore().getSqlPart()+" \n");
		}
		*//******CATEGORIAS******************//*
		if(campaignrequest.getCategoriesSelect().getCheck()){
			qry.append(" and "+campaignrequest.getCategoriesSelect().getSqlPart()+" \n");
		}
		*//******SELECÃO DE TEXTO************//*
		if(campaignrequest.getTextSelect().getCheck()){
			qry.append(" and (select count(*) from Promotionalcode B , Logpromotionalcodeuse A , Sale C  where "
					+ " A.promotionalcodeid = B.promotionalcodeid and A.purchaseid = C.purchaseid and UPPER( B.code ) = UPPER('"+campaignrequest.getTextSelect().getTextDesc()+"') AND C.useraccountid= u.useraccountid)"
					+(campaignrequest.getTextSelect().getOpetation().getValor()==0?"=":">")+"0 \n");
		}
		*//******SELECÃO DE DATAS************//*
		if(campaignrequest.getDataCadastro().getCheck()){
			qry.append(" and u.dateregistered "+campaignrequest.getDataCadastro().getSqlPart()+" \n");
		}
		if(campaignrequest.getDataAniversario().getCheck()){
			qry.append(" and u.anniversary "+campaignrequest.getDataAniversario().getSqlPart()+" \n");
		}
		if(campaignrequest.getDataUltimaCompra().getCheck()){
			qry.append(" and u.datelastpurchase "+campaignrequest.getDataUltimaCompra().getSqlPart()+" \n");
		}
		*//******SELECÃO DE VALORES**********//*
		if(campaignrequest.getReceitaLiquidaCompras().getCheck()){
			qry.append(" and u.netrevenue "+campaignrequest.getReceitaLiquidaCompras().getSqlPart()+" \n");
		}
		if(campaignrequest.getQuantidadeCompras().getCheck()){
			qry.append(" and u.totalpurchases "+campaignrequest.getQuantidadeCompras().getSqlPart()+" \n");
		}
		if(campaignrequest.getMaiorValorCompra().getCheck()){
			qry.append(" and u.maximumpurchasevalue "+campaignrequest.getMaiorValorCompra().getSqlPart()+" \n");
		}
		if(campaignrequest.getValorUtilizadoValePresente().getCheck()){
			qry.append(" and u.countofvalepresente "+campaignrequest.getValorUtilizadoValePresente().getSqlPart()+" \n");
		}
		*//******LISTA DE IDADES*************//*
		if(campaignrequest.getAgerangeSelectRequest().getCheck()){
			qry.append(" and u.agerangeid in "+campaignrequest.getAgerangeSelectRequest().getSqlPart()+" \n");
		}
		*//******LISTA DE OFERTAS************//*
		if(campaignrequest.getOfferSelectRequest().getCheck()){
			qry.append(" and (select count(1) from Sale s where s.useraccountid=u.useraccountid and s.unifieddiscountid in "
					  +campaignrequest.getOfferSelectRequest().getSqlPart()+" and s.type = 1)"+(campaignrequest.getOfferSelectRequest().getOfferSelect().getOperation().getValor()==0?"=":">")+"0 \n");
		}
		*//******LISTA DE OFERTAS************//*
		if(campaignrequest.getMailinglistSelectRequest().getCheck()){
			qry.append(" and (select count(1) from MailinglistUseraccount m where m.useraccountid=u.useraccountid and m.mailinglistid in "+campaignrequest.getMailinglistSelectRequest().getSqlPart()+")>0 \n");
		}
		
	return qry;
		
	}
	*/
	
	public static boolean compareObject(final Object obj1, final Object obj2, final Map<String,Object> parameters){
	Class[] NO_PARAM = new Class[] { };
	boolean diff = false;
				
		        try {
					if(obj1!=null)
					for (int i = 0; i < obj1.getClass().getMethods().length ; i++) {					
					   if((obj1.getClass().getMethods()[i].getReturnType()!=Set.class)&&(obj1.getClass().getMethods()[i].getReturnType()!=Class.class)&&
						  (obj1.getClass().getMethods()[i].getName().substring(0,3).equals("get"))){						   					   
						   if( !obj1.getClass().getMethods()[i].getName().substring(3).toLowerCase().equals("id") ){ 			   
						        try {			   
						        	boolean filter = false;						        	
						        	for(Entry<String, Object> parameter : parameters.entrySet()) {

										if(parameter.getKey().toUpperCase().equals(obj1.getClass().getMethods()[i].getName().substring(3).toUpperCase())){
											filter = true;
											break;
										}
									} 
							        if((!(obj1.getClass().getMethods()[i].invoke(obj1, NO_PARAM )).equals(obj2.getClass().getMethods()[i].invoke(obj2, NO_PARAM )))&&(filter)){
							        	diff = true;
							        	break;
							        }
						        }catch (Exception e) {}			   
						   }		   			   
					   }    
					}
				} catch (SecurityException e) {		
					e.printStackTrace();
				}
	return diff;	        
	}	
	
	public static int getIndexMethod(final Object obj, final String parameter, final String tpMethod){
		
		if(obj!=null)
		for (int i = 0; i < obj.getClass().getMethods().length ; i++) {					
		   if((obj.getClass().getMethods()[i].getReturnType()!=Set.class)&&(obj.getClass().getMethods()[i].getReturnType()!=Class.class)&&
			  (obj.getClass().getMethods()[i].getName().substring(0,3).equals(tpMethod))){						   					   
			   if( !obj.getClass().getMethods()[i].getName().substring(3).toLowerCase().equals("id") ){ 			   
				   return i;					   
			   }		   			   
		   }    
		}
				
	return (-1);	
	}
	
	public static void populateObject(final Object obj1, final Object obj2, final List<String> parameters){
	Class[] NO_PARAM = new Class[] { };	
		
		for(String parameter : parameters) {
			int i = getIndexMethod(obj1, parameter,"set");
			int j = getIndexMethod(obj2, parameter,"get");				
			try {
				obj1.getClass().getMethods()[i].invoke(obj1, obj2.getClass().getMethods()[j].invoke(obj2, NO_PARAM ) );
			} catch (Exception e) {}
    	}			        
			        
	}
	
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}
	
	public static BigInteger asciiToLong(final String code ) {
		
	char[] arr = normalize(code).toCharArray();
	String aux="";
		
		for (char c : arr) {
			aux+=Character.getNumericValue(c);
		}
		
		aux=justNumeric(aux);
		
	return new BigInteger(aux);	
	}
}
