<%@page import="java.util.Vector"%>
<%@page import="com.hp.hpl.jena.ontology.Individual"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.hp.hpl.jena.ontology.OntModel"%>
<%@page import="com.hp.hpl.jena.rdf.model.ModelFactory"%>

<%@page import="com.hp.hpl.jena.ontology.OntClass"%>
<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="fr">

<head><meta charset="utf-8">
<title>Bienvenue sur notre site</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="generator" content="Lauyan TOWeb 5.1.2.642">
<meta name="viewport" content="width=device-width, initial-scale=1.0"><!--[if IE]><![endif]-->

<link href="images/_scripts/bootstrap/css/font-awesome.min.css" rel="stylesheet">
<link href="images/_scripts/css/reset.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Quattrocento" rel="stylesheet">
<link href="images/_frame/style.css" rel="stylesheet">
<link rel="stylesheet" href="images/style.css">
<link rel="stylesheet" media="screen" href="../Documents/TOWeb Sites/defaultV5/Preview/_scripts/colorbox/colorbox.css">
<style>.alert a{color:#003399}.ta-left{text-align:left}.ta-center{text-align:center}.ta-justify{text-align:justify}.ta-right{text-align:right}.float-l{float:left}.float-r{float:right}.float-lpad{float:left;padding-right:1.5em;}.float-rpad{float:right;padding-left:1.5em;}
</style>
 </head>
 <body>
     
     <div id="site">
         <div id="page">
             
    <header>
        <div id="toolbar3" class="navbar">
             <div class="navbar-inner">
             <div class="container-fluid">
              <ul id="toolbar3_l" class="nav">
               </ul></div></div></div>
               <div id="toolbar1" class="navbar"><div class="navbar-inner"><div class="container-fluid">
                             
<div class="nav-collapse collapse">
<ul id="toolbar1_r" class="nav pull-right">
</ul></div></div></div></div>
                    
 <div id="main-carousel" class="carousel slide">
     <div class="carousel-inner fade-trans">
         <div class="active item"><img src="images/Sanstitre.png" alt=""></div>
        <!--<div class="item"><img src="images/accounting-business-management.jpg" alt=""></div>
         <div class="item"><img src="images/accounting-paper.jpg" alt=""></div><-->
     </div></div>
   <br><br><br><br><br><br>
    <div id="main-carousel" class="carousel slide">
          
              <div class="active item"></div>
              <div class="item"> </div>
             </div>
    
    </header>
       <br><br>
             <!-- Déclare la variable c -->

      
       <%-- -------------------------------------------------------- --%>
<form action="ServletPrincipale" method="POST"  >
    <div style="width:800px; margin:0 auto;">
    <div style="float:left;"> <br><img src="images/Sans titre1.jpg" width="91" height="24" alt="Sans titre1"/></div>
    <div style="float:left;">Tapez votre requête : <br>
        <select name="requete">
            <%! String s; 
String s1;%>

<%
     String NS = "http://localhost:8080/unnamed.owl#";
OntModel m = ModelFactory.createOntologyModel( );
Vector EspaceConceptuel=new Vector();
 m.read("file:/C:/Users/PCPC/Documents/NetBeansProjects/Webprototype/onto.owl");
OntClass ConcOnto=m.getOntClass(NS+"concept_generique");
Iterator  it=ConcOnto.listInstances();

 while (it.hasNext())
        {
           Individual NosConcept=(Individual)it.next();
           
            s= (NosConcept.toString()).substring(34);
           s1=s.replaceAll("_"," ") ;
           //String ss=s1.replaceAll("\\^","'") ;
          // EspaceConceptuel.add(s1);
         // c= EspaceConceptuel.size(); 
         %>
         
            
            <option><%=s1%></option><% }%>
           
        </select>
       </div>
 
          
             <div style="float:left;"> Choisisez votre Profil :
            <br>
            <select name="profil" >
                <option>Debutant</option>
                <option>Intermediaire</option>
                <option>Expert</option>
                
            </select>
        </div>
        </div>
      
         <center><div style="clear:both">
  
      <input type="submit" value="Recherche"  action="PageReponse.jsp"/></form>
     <input type="submit" value="Annuler" action="pageAccueil.jsp"/>
        </div></center>


    </body>



</html>
