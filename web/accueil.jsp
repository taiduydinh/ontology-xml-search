<%@page import="com.hp.hpl.jena.ontology.OntModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.hp.hpl.jena.ontology.OntClass"%>
<%@page import="java.util.Vector"%>
<%@page import="com.hp.hpl.jena.rdf.model.ModelFactory"%>
<%@page import="com.hp.hpl.jena.ontology.Individual"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="http://fonts.googleapis.com/css?family=Quattrocento" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACCUEIL</title>
        <style>.alert a{color:#003399}.ta-left{text-align:left}.ta-center{text-align:center}.ta-justify{text-align:justify}.ta-right{text-align:right}.float-l{float:left}.float-r{float:right}.float-lpad{float:left;padding-right:1.5em;}.float-rpad{float:right;padding-left:1.5em;}
</style>
    </head>
    <body>
    <header>
          <div id="toolbar3" class="navbar">
              <div class="navbar-inner">
             <div class="container-fluid">
                 <ul id="toolbar3_l" class="nav">
                     <div id="sharebox">
   <a target="_blank" href="https://www.facebook.com/"><img style="width:32px;height:32px" src="images/tw-share-facebook@2x.png" alt=""></a>
   <a target="_blank" href="https://twitter.com/"><img style="width:32px;height:32px" src="images/tw-share-twitter@2x.png" alt=""></a>
   <a target="_blank" href="https://google.fr/"><img style="width:32px;height:32px" src="images/tw-share-google@2x.png" alt=""></a>
   <a target="_blank" href="mailto:"><img style="width:32px;height:32px" src="images/tw-share-mail@2x.png" alt=""></a>
               </div></ul>
                </div></div></div>
          <div>
                   
                     
                           
                     <ul>
                      <a id="logo" href="index.jsp">
                     <span id="logo-lt">Apprendre l'informatique  </span>
                     <span id="logo-rt">Go</span><br> </a>
                           </ul>
                         
                           
          </div>
          
          <div id="main-carousel" class="carousel slide">
          
              <div class="active item">
              </div>
              <div class="item">
         
          </div>
                   
          </div>
      </header>
   
<div id="maDivGauche" style="float:left"></div>
<div id="maDivDroite" style="float:right;clear:right;"></div>
      
<Center> 
   <%-- <img src="accountant.jpg" width="1140" height="180" alt="accountant" />--%></center>
    <BR><BR><BR>
<div style="float:left;">
<TABLE BORDER=0 BORDERCOLOR= blue >
 

          <TR ALIGN=LEFT BGCOLOR=PINK >
             <TH>Apprenant</TH>
           
          </TR>
          <TR BGcolor="F3F3F3">
              <TD> <a href= "PageReq.jsp">Recherche des Cours</a></TD>
          </TR>
          
          <TR BGcolor="F3F3F3">
             <TD>Tchat</TD>
           </TR>
           <TR BGcolor="F3F3F3">
             <TD>Messagerie Electronique</TD>
           </TR>
          <TR BGcolor="F3F3F3">
             <TD >Aide</TD>
           </TR>
        
 <!--<TR ALIGN=LEFT BGCOLOR=PINK>
             <TH>Expert</TH>
          </TR>
          <TR BGcolor="F3F3F3">
              <TD> <a href="Ontologie.jsp" title="Voir la page d'accueil">Gestion de l'Ontologie</a></TD>
          </TR>
          <TR BGcolor="F3F3F3">
             <TD>Aide</TD>
           </TR>-->
</TABLE>

</div>
    <div style="float:left;"> 
        <form>
            
           
        </form>
  
    
    </div>
    <br><br><br><br><br><br><br><br><br><br><br>
 <TABLE style="PADDING-TOP: 20px" cellSpacing=0 cellPadding=0 width="100%">
<TBODY>
<TR>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/CiscoLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Cisco, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/IbmLogo.jpg" alt="Logo de la soci&eacute;t&eacute; IBM, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/OracleSunLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Sun-Oracle, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/AppleLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Apple, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/SybaseLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Sybase, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/NovellLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Novell, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/IntelLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Intel, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/accentureLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Accenture, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/SAPLogo.jpg" alt="Logo de la soci&eacute;t&eacute; SAP, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/PrometricLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Prometric, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/ToeicLogo.jpg" alt="Logo de la soci&eacute;t&eacute; Toeic, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD>
<TD width=190 align=center><FONT color=black><IMG border=0 src="http://www.supinfo.com/images/partners/MicrosoftITALogo.jpg" alt="Logo du IT Academy Program par Microsoft, partenaire p&eacute;dagogique de SUPINFO, la Grande &Eacute;cole de l'informatique, du num&eacute;rique et du management"></FONT></TD></TR></TBODY></TABLE></CENTER>
<br/>
<P align=center><SPAN><FONT color=black size=1>
<strong>SUPINFO International University</strong><br>
Ecole d'Informatique - IT School<br>
&Eacute;cole Sup&eacute;rieure d'Informatique de Paris, leader en France<br>
La Grande Ecole de l'informatique, du num&eacute;rique et du management<br>
Fond&eacute;e en 1965, reconnue par l'&Eacute;tat. Titre Bac+5 certifi&eacute; au niveau I.<br>
SUPINFO International University is globally operated by EDUCINVEST Belgium - Rue Ducale, 29 - 1000 Brussels<BR>and is accredited in France by Association Ecole Sup&eacute;rieure d'Informatique de Paris (ESI SUPINFO)</FONT></P></span>
			</td>
</tr>

	</table>    

    </body>
</html>
