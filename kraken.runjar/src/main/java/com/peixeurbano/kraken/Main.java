package com.peixeurbano.kraken;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
		   
	public static void main(final String[] args) {

		   Connection conn = null;
		   Statement stmt = null;
		   
		   final String USER = args[0];
		   final String PASS = args[1];
		   final String DB_URL = "jdbc:sqlserver://"+args[2];
		   final boolean PC =  "pc".equals( funcoes.nvl(args[3],"") );
				   
		   try{

		      Class.forName(Main.JDBC_DRIVER);
		      
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   

		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT C.CAMPAIGN_ID, C.NAME, C.STATUS, C.SCHEDULE_DATEINI, C.SCHEDULE_DATEFIM, C.SCHEDULE_COUNT,"
		      				+ "( SELECT COUNT(*) FROM dbo.CAMPAIGN_USERACCOUNT CU WITH (NOLOCK) WHERE CU.CAMPAIGN_ID=C.CAMPAIGN_ID )CNT"
		      				+ ", C.USER_ID_INSERT, C.DATE_INSERT, C.USER_ID_UPDATE, C.DATE_UPDATE "
		      				+ "FROM dbo.CAMPAIGN C WITH (NOLOCK) WHERE C.USABLE=1 ORDER BY C.STATUS, C.SCHEDULE_DATEINI, C.CAMPAIGN_ID";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      StringBuilder header = new StringBuilder("POSICAO |CAMPAIGN_ID |NAME                                       |STATUS |SCHEDULE_DATEINI        |SCHEDULE_DATEFIM        |"
			      		+ "SCHEDULE_COUNT |COUNT  |");		      
		      if(PC){
		    	  header.append("USER_ID_INSERT |DATE_INSERT             |USER_ID_UPDATE |DATE_UPDATE             |");
		      }
		      StringBuilder divisor = new StringBuilder("--------|------------|-------------------------------------------|-------|------------------------|------------------------|"
			      		+ "---------------|-------|");
		      if(PC){
		    	  divisor.append("---------------|------------------------|---------------|------------------------|");
		      }
		      System.out.println(header);
		      System.out.println(divisor);
		      
		      int cnt=0;
		      while(rs.next()){
		    	  
		    	 cnt++;
		    	 
		         try {
		        	 
		        	 StringBuilder dados = new StringBuilder(							 
							 funcoes.rpad(String.valueOf(cnt), " ", 7)+" | "+ 
							 funcoes.rpad(rs.getString("CAMPAIGN_ID"), " ", 11)+"|"+
							 funcoes.rpad(rs.getString("NAME"), " ", 43)+"|"+
							 funcoes.rpad(rs.getString("STATUS"), " ", 7)+"|"+
							 funcoes.rpad(rs.getString("SCHEDULE_DATEINI"), " ", 24)+"|"+
							 funcoes.rpad(rs.getString("SCHEDULE_DATEFIM"), " ", 24)+"|"+
							 funcoes.rpad(rs.getString("SCHEDULE_COUNT"), " ", 15)+"|"+
							 funcoes.rpad(rs.getString("CNT"), " ", 7)+"|");
		        	 if(PC){
		        	 		 dados.append(
							 funcoes.rpad(rs.getString("USER_ID_INSERT"), " ", 15)+"|"+
							 funcoes.rpad(rs.getString("DATE_INSERT"), " ", 24)+"|"+
							 funcoes.rpad(rs.getString("USER_ID_UPDATE"), " ", 15)+"|"+
							 funcoes.rpad(rs.getString("DATE_INSERT"), " ", 24)+"|");
		        	 }
		        	 
		        	 System.out.println(dados);
		        	 
				} catch (Exception e) {
					System.out.println("PROBLEMAS NA LINHA "+cnt);
				}
		         
		      }
		      
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}

}
