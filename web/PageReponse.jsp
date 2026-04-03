
<%@page import="java.sql.DriverManager"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Prototype.CalculeScore"%>
<%@page import="java.sql.Statement"%>

<%-- 
    Document   : PageReponse
    Created on : 24 oct. 2014, 20:49:56
    Author     : PCPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
        <%! //CalculeScore s=new CalculeScore();    
        String val; 
        float sco;
        int doc;
        String NomDoc; %>
        <table border="1">  
            <tr> <th> <h2>Document ou Fragment de Document</h2></th>
                <th> <h2>Score</h2></th>
            </tr>
        <%   
           
    Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
 
 
Statement stmt=cnx.createStatement(); 
Statement stmt1=cnx.createStatement(); 
Statement stmt2=cnx.createStatement(); 






     ResultSet res4 =stmt2.executeQuery("select s.idf_doc, s.début, s.fin, s.score,d.nom_doc from score s, document d where d.idf_doc=s.idf_doc and score>0 order by score DEsc"); 
 while(res4.next())
{int doc=res4.getInt(1);
  int déb=res4.getInt(2);
 int fin=res4.getInt(3);
 sco=res4.getFloat(4);
NomDoc=res4.getString(5);
 ResultSet res3 =stmt.executeQuery("select valeur  from texte t where t.idf_doc= "+doc+" and t.début>"+déb +" and "+fin+">t.fin "); 
   
 while(res3.next())
{
    val=res3.getString(1);%>
        <tr>  
            

  <%}%> 
  <th ><a href =""> <%=NomDoc+" "+déb%> </a> </th>  
            <th > <%=sco%>  </th>    
        
          
         <% }%>
        </tr>
    </table>
    </body>
</html>
