/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prototype;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PCPC
 */
//@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet2"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String NS = "http://localhost:8080/unnamed.owl#";
OntModel m = ModelFactory.createOntologyModel( );
 Vector EspaceConceptuel=new Vector();
int [] Vecrequete=new int[188];
Vector Concepttrouve =new Vector();
CalculeScore CalScore =new CalculeScore();
m.read("file:/C:/Users/PCPC/Documents/NetBeansProjects/Webprototype/onto.owl");
OntClass ConcOnto=m.getOntClass(NS+"concept_generique");
Iterator  it=ConcOnto.listInstances();
        while (it.hasNext())
        {  Individual NosConcept=(Individual)it.next();
           String s= (NosConcept.toString()).substring(34);
           EspaceConceptuel.addElement(s);
              }
         
    String v = request.getParameter("requete");
    if (v == null) {
      v = "";}
//request.
String v1 = request.getParameter("profil");
    if (v1 == null) {
      v1 = "";
    }    
        boolean b1,b2,b3,b4,b5;
b1=b2=b3=b4=b5=false;

//---------------------si c'est un didacticiel--------------------------------//
OntClass oc1=m.getOntClass(NS+"didacticiel");
ExtendedIterator  it1=oc1.listInstances();
while (it1.hasNext())
{
  Individual in=(Individual)it1.next();
  if (in.getLocalName().equals(v))
  {
    b1=true; 
  }}
//------------------si c'est un scénario--------------------------------------//        
OntClass oc2=m.getOntClass(NS+"scenario");
ExtendedIterator  it2=oc2.listInstances();
while (it2.hasNext())
{
  Individual in=(Individual)it2.next();
  if (in.getLocalName().equals(v))
  {
    b2=true;}}
//------------------si c'est un concept---------------------------------------//
OntClass oc3=m.getOntClass(NS+"concept");
ExtendedIterator  it3=oc3.listInstances();
while (it3.hasNext())
{
  Individual in=(Individual)it3.next();
  if (in.getLocalName().equals(v))
  {
    b3=true;}
}
//------------------si c'est un élémént de Connaissance------------------------
OntClass oc4=m.getOntClass(NS+"element_de_connaissance");
ExtendedIterator  it4=oc4.listInstances();
while (it4.hasNext())
{
  Individual in=(Individual)it4.next();
  if (in.getLocalName().equals(v))
  {
    b4=true;}}
//--------------------------------sinon --------------------------------------
if(!b1&&!b2&&!b3&&!b4)
{
  b5=true;}

//--------------Si Didacticiel------------------------------------------------------------

if (b1){
Individual ind=m.getIndividual(NS+v);
Concepttrouve.addElement(ind.getLocalName());
  StmtIterator  li= ind.listProperties(m.getProperty(NS+"constitue-de"));
  while (li.hasNext())
      {
      Statement  p=(Statement)li.next();
      Individual sc=m.getIndividual(NS+p.getResource().getLocalName());
     Concepttrouve.addElement(sc.getLocalName());
    
   StmtIterator  li1= sc.listProperties(m.getProperty(NS+"contribue-a-enseigner"));
   while (li1.hasNext()) 
      {
      Statement  p1=(Statement)li1.next();
       Individual concept=m.getIndividual(NS+p1.getResource().getLocalName());
        Concepttrouve.addElement(concept.getLocalName());
        
   StmtIterator  li2= concept.listProperties(m.getProperty(NS+"forme-de"));
         while (li2.hasNext())
       {
       Statement  p2=(Statement)li2.next();
        Individual Ele_con=m.getIndividual(NS+p2.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
       }}
     }


       // processRequest(request, response);
    }//fin b1
   
//----------------------si c'est un scénario---------------------------------

if (b2)
{
 //----------------------scénario principal---------------------------
Individual sc=m.getIndividual(NS+v);
Concepttrouve.addElement(sc.getLocalName());
StmtIterator  li1= sc.listProperties(m.getProperty(NS+"contribue-a-enseigner"));

   while (li1.hasNext()) 
      {
      Statement  p1=(Statement)li1.next();
       Individual concept=m.getIndividual(NS+p1.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
     
      StmtIterator  li2= concept.listProperties(m.getProperty(NS+"forme-de"));
      while (li2.hasNext())
       {
       Statement  p2=(Statement)li2.next();
        Individual Ele_con=m.getIndividual(NS+p2.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
       }}
    
    if (v1.equals("Expert"))
{
 //------------------------------scénario suivant-------------------------
StmtIterator  li3= sc.listProperties(m.getProperty(NS+"precede2"));
while (li3.hasNext()) 
      {
      Statement  p3=(Statement)li3.next();
       Individual sc1=m.getIndividual(NS+p3.getResource().getLocalName());
     Concepttrouve.addElement(sc1.getLocalName());
       
 StmtIterator  li4= sc1.listProperties(m.getProperty(NS+"contribue-a-enseigner"));
      while (li4.hasNext()) 
      {
      Statement  p4=(Statement)li4.next();
       Individual concept=m.getIndividual(NS+p4.getResource().getLocalName());
        Concepttrouve.addElement(concept.getLocalName());
     StmtIterator  li5= concept.listProperties(m.getProperty(NS+"forme-de"));
  
     while (li5.hasNext())
       {
       Statement  p5=(Statement)li5.next();
        Individual Ele_con=m.getIndividual(NS+p5.getResource().getLocalName());
        Concepttrouve.addElement(Ele_con.getLocalName());
       }}
}    
}      
if (v1.equals("Intermediaire"))
{
 //------------------------------scénario suivant-------------------------

StmtIterator  li3= sc.listProperties(m.getProperty(NS+"precede2"));
while (li3.hasNext()) 
      {
      Statement  p3=(Statement)li3.next();
       Individual sc1=m.getIndividual(NS+p3.getResource().getLocalName());
     Concepttrouve.addElement(sc1.getLocalName());
       
    StmtIterator  li4= sc1.listProperties(m.getProperty(NS+"contribue-a-enseigner"));
      while (li4.hasNext()) 
      {
      Statement  p4=(Statement)li4.next();
       Individual concept=m.getIndividual(NS+p4.getResource().getLocalName());
        Concepttrouve.addElement(concept.getLocalName());
  
   StmtIterator  li5= concept.listProperties(m.getProperty(NS+"forme-de"));
     while (li5.hasNext())
       {
       Statement  p5=(Statement)li5.next();
        Individual Ele_con=m.getIndividual(NS+p5.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
       }}   
}
//-------------------------senario précédent--------------------------
StmtIterator  li6= sc.listProperties(m.getProperty(NS+"precede-par2"));

while (li6.hasNext()) 
      {
      Statement  p6=(Statement)li6.next();
       Individual sc2=m.getIndividual(NS+p6.getResource().getLocalName());
     Concepttrouve.addElement(sc2.getLocalName());
       
       StmtIterator  li7= sc2.listProperties(m.getProperty(NS+"contribue-a-enseigner"));
      while (li7.hasNext()) 
      {
      Statement  p7=(Statement)li7.next();
       Individual concept=m.getIndividual(NS+p7.getResource().getLocalName());
        Concepttrouve.addElement(concept.getLocalName());
        
     StmtIterator  li8= concept.listProperties(m.getProperty(NS+"forme-de"));
      while (li8.hasNext())
       {
       Statement  p8=(Statement)li8.next();
        Individual Ele_con=m.getIndividual(NS+p8.getResource().getLocalName());
           Concepttrouve.addElement(Ele_con.getLocalName());
       }}}}

if (v1.equals("Debutant"))
{
 
//-------------------------senario précédent--------------------------
StmtIterator  li6= sc.listProperties(m.getProperty(NS+"precede-par2"));

while (li6.hasNext()) 
      {
      Statement  p6=(Statement)li6.next();
       Individual sc2=m.getIndividual(NS+p6.getResource().getLocalName());
     Concepttrouve.addElement(sc2.getLocalName());
       
     StmtIterator  li7= sc2.listProperties(m.getProperty(NS+"contribue-a-enseigner"));
      while (li7.hasNext()) 
      {
      Statement  p7=(Statement)li7.next();
       Individual concept=m.getIndividual(NS+p7.getResource().getLocalName());
        Concepttrouve.addElement(concept.getLocalName());
     
    StmtIterator  li8= concept.listProperties(m.getProperty(NS+"forme-de"));
     while (li8.hasNext())
       {
       Statement  p8=(Statement)li8.next();
        Individual Ele_con=m.getIndividual(NS+p8.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
       }}   
}
}
} //fin b2

////////---------------si c'est un concept--------------------------------
if (b3)
{
    //----------------------scénario principal---------------------------
Individual conc=m.getIndividual(NS+v);
   StmtIterator  li=  conc.listProperties(m.getProperty(NS+"est-enseigne-par"));
while (li.hasNext())
       {
       Statement  p=(Statement)li.next();
     Individual sc=m.getIndividual(NS+p.getResource().getLocalName());
       Concepttrouve.addElement(sc.getLocalName());
       
     StmtIterator  li1= sc.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li1.hasNext())
       {
       Statement  p1=(Statement)li1.next();
     Individual concept=m.getIndividual(NS+p1.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li2= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li2.hasNext())
          {Statement  p2=(Statement)li2.next();
     Individual Ele_con=m.getIndividual(NS+p2.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
        }}}

if (v1.equals("Expert"))
{
 
//------------------------------scénario suivant-------------------------

StmtIterator  li3= conc.listProperties(m.getProperty(NS+"est-enseigne-par"));
     
while (li3.hasNext())
       {
       Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
    
       
       StmtIterator  li4= sc.listProperties(m.getProperty(NS+"precede2"));
       while (li4.hasNext())
       {
           Statement p4=(Statement)li4.next();
        Individual sc1=m.getIndividual(NS+p4.getResource().getLocalName());
         Concepttrouve.addElement(sc.getLocalName());
    
         StmtIterator  li5= sc1.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li5.hasNext())
       {
       Statement  p5=(Statement)li5.next();
     Individual concept=m.getIndividual(NS+p5.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li6= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li6.hasNext())
          {Statement  p6=(Statement)li6.next();
     Individual Ele_con=m.getIndividual(NS+p6.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          }
       }}}    
}      
if (v1.equals("Intermediaire"))
{//------------------------------scénario suivant-------------------------
  StmtIterator  li3= conc.listProperties(m.getProperty(NS+"est-enseigne-par"));
     
while (li3.hasNext())
       {
       Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
     
       
       StmtIterator  li4= sc.listProperties(m.getProperty(NS+"precede2"));
       while (li4.hasNext())
       {
           Statement p4=(Statement)li4.next();
        Individual sc1=m.getIndividual(NS+p4.getResource().getLocalName());
         Concepttrouve.addElement(sc.getLocalName());
    
         StmtIterator  li5= sc1.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li5.hasNext())
       {
       Statement  p5=(Statement)li5.next();
     Individual concept=m.getIndividual(NS+p5.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li6= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li6.hasNext())
          {Statement  p6=(Statement)li6.next();
     Individual Ele_con=m.getIndividual(NS+p6.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          
          }
         }  }}
   //------------------------------scénario précédent-------------------------
    
 while (li3.hasNext())
       {Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
      
     StmtIterator  li7= sc.listProperties(m.getProperty(NS+"precede-par2"));
       while (li7.hasNext())
       {
           Statement p7=(Statement)li7.next();
        Individual sc2=m.getIndividual(NS+p7.getResource().getLocalName());
         Concepttrouve.addElement(sc2.getLocalName());
    
         StmtIterator  li8= sc2.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li8.hasNext())
       {
       Statement  p8=(Statement)li8.next();
     Individual concept=m.getIndividual(NS+p8.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li9= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li9.hasNext())
          {Statement  p9=(Statement)li9.next();
     Individual Ele_con=m.getIndividual(NS+p9.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          
          }
         }  }    
       
       
              }}

if (v1.equals("Debutant"))
{//------------------------------scénario précédent-------------------------
    StmtIterator  li3= conc.listProperties(m.getProperty(NS+"est-enseigne-par"));
 while (li3.hasNext())
       {Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
      
     StmtIterator  li7= sc.listProperties(m.getProperty(NS+"precede-par2"));
       while (li7.hasNext())
       {
           Statement p7=(Statement)li7.next();
        Individual sc2=m.getIndividual(NS+p7.getResource().getLocalName());
         Concepttrouve.addElement(sc2.getLocalName());
    
         StmtIterator  li8= sc2.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li8.hasNext())
       {
       Statement  p8=(Statement)li8.next();
     Individual concept=m.getIndividual(NS+p8.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li9= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li9.hasNext())
          {Statement  p9=(Statement)li9.next();
     Individual Ele_con=m.getIndividual(NS+p9.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          
          }
         }  }    
                     }
}
    }//fin b3
    
////////---------------si c'est un élément de connaissance--------------------------------
if (b4)
{
    //----------------------scénario principal---------------------------
Individual Ele=m.getIndividual(NS+v);
   StmtIterator  li=  Ele.listProperties(m.getProperty(NS+"appartient-a-scenario"));
while (li.hasNext())
       {
       Statement  p=(Statement)li.next();
     Individual sc=m.getIndividual(NS+p.getResource().getLocalName());
       Concepttrouve.addElement(sc.getLocalName());
       
     StmtIterator  li1= sc.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li1.hasNext())
       {
       Statement  p1=(Statement)li1.next();
     Individual concept=m.getIndividual(NS+p1.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li2= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li2.hasNext())
          {Statement  p2=(Statement)li2.next();
     Individual Ele_con=m.getIndividual(NS+p2.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
        }}}

if (v1.equals("Expert"))
{
 
//------------------------------scénario suivant-------------------------

StmtIterator  li3= Ele.listProperties(m.getProperty(NS+"appartient-a-scenario"));
     
while (li3.hasNext())
       {
       Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
    
       
       StmtIterator  li4= sc.listProperties(m.getProperty(NS+"precede2"));
       while (li4.hasNext())
       {
           Statement p4=(Statement)li4.next();
        Individual sc1=m.getIndividual(NS+p4.getResource().getLocalName());
         Concepttrouve.addElement(sc.getLocalName());
    
         StmtIterator  li5= sc1.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li5.hasNext())
       {
       Statement  p5=(Statement)li5.next();
     Individual concept=m.getIndividual(NS+p5.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li6= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li6.hasNext())
          {Statement  p6=(Statement)li6.next();
     Individual Ele_con=m.getIndividual(NS+p6.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          }
       }}}    
}      
if (v1.equals("Intermediaire"))
{//------------------------------scénario suivant-------------------------
  StmtIterator  li3= Ele.listProperties(m.getProperty(NS+"appartient-a-scenario"));
     
while (li3.hasNext())
       {
       Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
     
       
       StmtIterator  li4= sc.listProperties(m.getProperty(NS+"precede2"));
       while (li4.hasNext())
       {
           Statement p4=(Statement)li4.next();
        Individual sc1=m.getIndividual(NS+p4.getResource().getLocalName());
         Concepttrouve.addElement(sc.getLocalName());
    
         StmtIterator  li5= sc1.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li5.hasNext())
       {
       Statement  p5=(Statement)li5.next();
     Individual concept=m.getIndividual(NS+p5.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li6= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li6.hasNext())
          {Statement  p6=(Statement)li6.next();
     Individual Ele_con=m.getIndividual(NS+p6.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          
          }
         }  }}
   //------------------------------scénario précédent-------------------------
    
 while (li3.hasNext())
       {Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
      
     StmtIterator  li7= sc.listProperties(m.getProperty(NS+"precede-par2"));
       while (li7.hasNext())
       {
           Statement p7=(Statement)li7.next();
        Individual sc2=m.getIndividual(NS+p7.getResource().getLocalName());
         Concepttrouve.addElement(sc2.getLocalName());
    
         StmtIterator  li8= sc2.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li8.hasNext())
       {
       Statement  p8=(Statement)li8.next();
     Individual concept=m.getIndividual(NS+p8.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li9= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li9.hasNext())
          {Statement  p9=(Statement)li9.next();
     Individual Ele_con=m.getIndividual(NS+p9.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          
          }
         }  }    
       
       
              }}

if (v1.equals("Debutant"))
{//------------------------------scénario précédent-------------------------
    StmtIterator  li3= Ele.listProperties(m.getProperty(NS+"appartient-a-scenario"));
 while (li3.hasNext())
       {Statement  p3=(Statement)li3.next();
     Individual sc=m.getIndividual(NS+p3.getResource().getLocalName());
      
     StmtIterator  li7= sc.listProperties(m.getProperty(NS+"precede-par2"));
       while (li7.hasNext())
       {
           Statement p7=(Statement)li7.next();
        Individual sc2=m.getIndividual(NS+p7.getResource().getLocalName());
         Concepttrouve.addElement(sc2.getLocalName());
    
         StmtIterator  li8= sc2.listProperties(m.getProperty(NS+"contribue-a-enseigner"));  
       while (li8.hasNext())
       {
       Statement  p8=(Statement)li8.next();
     Individual concept=m.getIndividual(NS+p8.getResource().getLocalName());
       Concepttrouve.addElement(concept.getLocalName());
       
          StmtIterator  li9= concept.listProperties(m.getProperty(NS+"forme-de"));  
       
          while (li9.hasNext())
          {Statement  p9=(Statement)li9.next();
     Individual Ele_con=m.getIndividual(NS+p9.getResource().getLocalName());
       Concepttrouve.addElement(Ele_con.getLocalName());
          
          }
         }  }    
       
       
              }

}


    }//fin b4
    





   if (b5 ) {  System.out.print("Veuillez Reformuler votre requête"); }
   boolean t=false;

 for(int i=0; i<=EspaceConceptuel.size()-1;i++){
     t=false;int j=0;
 while (j<=Concepttrouve.size()-1 && t==false)
     { 
     if (EspaceConceptuel.elementAt(i).equals(Concepttrouve.elementAt(j)))
     {t=true;}
     
     j++;
    }   
  if (t==true)  
     Vecrequete[i]=1;  
 else  Vecrequete[i]= 0;  
 }
        try {    
            CalScore.Score();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
 
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
