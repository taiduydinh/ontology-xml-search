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

import static java.lang.Math.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import java.util.Iterator;


import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IndexationNe {
   
int Nt;
 int k;
 int NtTotal;
 int NbrNtDesCon;
float iecf;
int Nte;//nombre de noeud texte descendant de chaque Ne
int Ne;// nombre de noeud élément
int NteCon;//nombre de noeud texte descendant de chaque Ne pour un concept Cj
Vector EspaceConceptuel=new Vector();
// List PondGlobal = new ArrayList();
 float[] PondGlobal=new float [188];
 int[][] matricePonLocal=new int[4000][188]; 
   //int[][] matricePonLocal=new int[108][188];
 float [][] poidNoeudtexte= new float[4000][188];
 float[][] poidNoeudElement= new float[4000][188];
int [][] NeNtCon= new int[4000][188];
 int [] tabNte=new int[4000];//pour le nombre de Nt descendant pour chaque Ne
 int [] Distance=new int[4000];
 public void EspaceCenceptuel() {

String NS = "http://localhost:8080/unnamed.owl#";
OntModel m = ModelFactory.createOntologyModel( );
 //Vector EspaceConceptuel=new Vector();

 


 m.read("file:/C:/Users/PCPC/Documents/NetBeansProjects/Webprototype/onto.owl");
OntClass ConcOnto=m.getOntClass(NS+"concept_generique");
Iterator  it=ConcOnto.listInstances();
        while (it.hasNext())
        {
           Individual NosConcept=(Individual)it.next();
           
           String s= (NosConcept.toString()).substring(34);
           String s1=s.replaceAll("_"," ") ;
           //String ss=s1.replaceAll("\\^","'") ;
           EspaceConceptuel.add(s1);
           
        }
      // for(int i=0; i<=EspaceConceptuel.size()-1;i++)
           System.out.println(EspaceConceptuel);
          System.out.println("nombre de concept de notre ontologie est  " +EspaceConceptuel.size());

}
 //******************Nombre de noeuds texte |Nt|********************************
public int NbrNoeudtext () throws SQLException, ClassNotFoundException   
 {
  Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt=cnx.createStatement();     
   
//recupérer  |Nt|: le nombre  des noeud texte de notre corpus 
ResultSet res =stmt.executeQuery("select count(*) from texte"); 
  
while(res.next())
{NtTotal=res.getInt(1);}
return NtTotal;
//System.out.println("|Nt|= "+Nt);
 }
 //*****************************************************************************

//-----Podération Globale= (log(nbr total Nt/nbr total Nt contenant le concept recherché))
 public void Pond_globale() throws ClassNotFoundException, SQLException{
 
int Nc = 0;

Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt=cnx.createStatement(); 

//String v =  request.getParameter("requete");
 //recupérer  |Nc|: est le nombre total de noeuds textes contenant le concept recherché.
for(int i=0; i<=EspaceConceptuel.size()-1;i++) {
ResultSet res =stmt.executeQuery("select count(*) from texte  where valeur like '%"+EspaceConceptuel.elementAt(i)+"%'"); 
  while(res.next())
{ 
  Nc=res.getInt(1);}
  if(Nc!=0)
   iecf=(float) log10(NtTotal/Nc);
  else{
      iecf=0;}
//System.out.println("|Nc|= "+Nc);
 // PondGlobal.addElement(iecf); 
 PondGlobal[i]=iecf;
  //PondGlobal.add(iecf);
 }
for(int i=0; i<=EspaceConceptuel.size()-1;i++) {
//System.out.println("|iecf"+i+"|= "+ PondGlobal[i]); 
}
}

//----Pondération Locale =le nombre d'occurence d'un concept  dans un noeud texte 
public void Pond_locale() throws ClassNotFoundException, SQLException{
String Texte;
   int Nbr;
Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt=cnx.createStatement();     
ResultSet res =stmt.executeQuery("select valeur from texte"); 
 Nbr=0;
 k=0;
while(res.next())
{    Texte=res.getString(1);
//System.out.println("le noeud " +l++ +"="+ Texte);
  for(int j=0; j<=EspaceConceptuel.size()-1;j++)
  {
  Pattern Recherche=Pattern.compile((String) EspaceConceptuel.elementAt(j));
  Matcher concept= Recherche.matcher(Texte);
  while (concept.find())  
  Nbr++;
  matricePonLocal[k][j]=Nbr;
  //System.out.println("NBR ="+ Nbr);
Nbr=0;
  }k++;
}

/*for(int i=0; i<=Nt-1;i++)
{  for(int j=0; j<=matricePonLocal[i].length-1;j++)
    //System.out.println("Pour le Noeud Nt"+i +" le nombre de Concept '"+EspaceConceptuel.elementAt(k)+"' trouvé est = "+ matricePonLocal[i][k]);
System.out.println("Pour le Nt"+i+" C"+j+"= "+ matricePonLocal[i][j]);

}*/
}   

//----pondération des concepts de notre ontologie
public void ponderationNoeudTexte() {


for(int i=0; i<=Nt-1;i++)
{  System.out.print(" Nt"+i+":");
    for(int j=0; j<=matricePonLocal[i].length-1;j++){
   poidNoeudtexte[i][j]=(matricePonLocal[i][j]*PondGlobal[j]);
//System.out.print(" "+poidNoeudtexte[i][j]);
    }
    System.out.println(" ");
}}
//-------Calculer le nombre de noeud texte descandant pour chaque noeud Elément//////////////////////////
public void Nbr_Nt_Desendant () throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt=cnx.createStatement();     
   
//recupérer  |Nte|: le nombre  des noeud texte descendant de chaque Ne
ResultSet res =stmt.executeQuery("select  count(*) from texte, element where  texte.idf_doc=element.idf_doc and texte.début>element.début and texte.fin<element.fin group by element.idf_doc,  nom_ele, element.début"); 
int i=0;
while(res.next())
{ Nte=res.getInt(1);
  tabNte[i]=Nte; 
// System.out.println(" " +tabNte[i]); 
 i++;
}
}


//---calculer le nombre de noeuds texte contenenant le concept Cj pour un noeud Elément----------------------------------------
public void Nbr_Nt_Conc_Desendant () throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt=cnx.createStatement();     
 ResultSet res1 =stmt.executeQuery(" Select count(*) from element"); 
 while(res1.next())
{ Ne=res1.getInt(1);}
//recupérer  |NtCe|: le nombre  des noeud texte descendant de chaque Ne contenant le concpet Cj

 for (int i=0; i<=Ne-1;i++){
 for(int j=0; j<=EspaceConceptuel.size()-1;j++) {
ResultSet res =stmt.executeQuery("select  count(*) from texte, element where valeur like'%"+EspaceConceptuel.elementAt(j)+"%' and texte.idf_doc=element.idf_doc and texte.début>element.début and texte.fin<element.fin group by element.idf_doc,  nom_ele, element.début"); 
  while(res.next())
{ 
  NteCon=res.getInt(1);
  NeNtCon[i][j]= NteCon;}
  }
 }
for(int i=0; i<=Ne-1;i++)
  for(int j=0; j<=  NeNtCon[i].length-1;j++){
    //System.out.println("Pour le Noeud Nt"+i +" le nombre de Concept '"+EspaceConceptuel.elementAt(k)+"' trouvé est = "+ matricePonLocal[i][k]);
//System.out.println("Pour le Ne"+i+" C"+j+"= "+ NeNtCon[i][j]);
  }



}
//------------------------------------------------------------------------------
//-------------------indexation des Noeus Eléments------------------------------
//------------------------------------------------------------------------------
public void NoeudElement() throws ClassNotFoundException, SQLException{

int Nbr;  
int num_ele=0;
Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt0=cnx.createStatement();  
Statement stmt=cnx.createStatement();  
Statement stmt1=cnx.createStatement(); 

ResultSet NoeudElement =stmt0.executeQuery(" Select * from element");

 while(NoeudElement.next()) // pour chaque noeud élément
 {  int doc_ele=NoeudElement.getInt(1);
    int deb_ele=NoeudElement.getInt(2);
    int fin_ele=NoeudElement.getInt(4);
    //int par_ele=res1.getInt(5);

 // -------calculer le nombre de noeuds textes descendant pour chaque noeud élément
    ResultSet count=stmt.executeQuery(" select count(*) from texte where  idf_doc= "+doc_ele+" and début >"+deb_ele+" and fin< "+fin_ele);
    while(count.next()) 
    Nt=count.getInt(1);//NBR de noeuds texte descendant pour un noeud élément
//System.out.println(Nt);

//--------------Calculer le nbr de noeuds Textes descendant contenant un concept cj
 for(int j=0; j<=EspaceConceptuel.size()-1;j++) {
ResultSet res =stmt.executeQuery("select count(*) from texte where  idf_doc= "+doc_ele+" and début >"+deb_ele+" and fin< "+fin_ele+ " and valeur like'%"+EspaceConceptuel.elementAt(j)+"%'" ); 
  while(res.next())
{ 
  NbrNtDesCon=res.getInt(1);
  NeNtCon[num_ele][j]= NbrNtDesCon;}
  //System.out.println("nber"+NbrNtDesCon);
  }


//***** recuperer les noeuds textes descendants pour chaque noeud élélement*****
   Nbr=0;
    k=0;//pour indiquer le numéro du noeud texte
    ResultSet NoeudTexte=stmt1.executeQuery(" select * from texte where  idf_doc= "+doc_ele +" and début>"+deb_ele+" and fin< "+fin_ele);
 while (NoeudTexte.next())//pour chaque noeud texte descendant
{
//-------------------pondération locale Nt---------------------------------------
    int  doc_Nt=NoeudTexte.getInt(1);
    int  deb_Nt=NoeudTexte.getInt(2);
    String Texte=NoeudTexte.getString(3);
    int  fin_Nt=NoeudTexte.getInt(4);
     for(int j=0; j<=EspaceConceptuel.size()-1;j++)
       {
          Pattern Recherche=Pattern.compile((String) EspaceConceptuel.elementAt(j));
          Matcher concept= Recherche.matcher(Texte);
          while (concept.find())  
          Nbr++;
          matricePonLocal[k][j]=Nbr;
 // System.out.println("pondlocal ="+ Nbr);
         Nbr=0; }
     


//-----------------  Calcul de Distance ------------------------------------------
ResultSet dist=stmt.executeQuery("select count(*) from element where  idf_doc="+doc_Nt +" and début between "+deb_ele+" and "+deb_Nt+" and fin between "+fin_Nt+" and "+fin_ele);

while (dist.next())//recuperer la distance 
  { 
    int distance=dist.getInt(1);
  Distance[k]=distance;

  //System.out.println("distance Ne"+num_ele+" du Nt"+k+" "+Distance[k]);  
} 
//--------------------------pondération des noeuds textes-----------------------
 
    for(int j=0; j<=matricePonLocal[k].length-1;j++){
   poidNoeudtexte[k][j]=(matricePonLocal[k][j]*PondGlobal[j]);//pondération noeuds Texte
//System.out.print("Nt "+poidNoeudtexte[k][j]);
  
  //pondération noeud élément
 poidNoeudElement[num_ele][j]= (((NeNtCon[num_ele][j]*(float) (pow(Nt,-1)))*(float) (pow(Distance[k],-1)) )*poidNoeudtexte[k][j])+poidNoeudElement[num_ele][j];

    }
    //System.out.println(""); 
k++;//m'indique le numéro du Noeud texte
}//fin noeud texte/********************************************************
  for(int j=0; j<=matricePonLocal[k].length-1;j++)
//System.out.print("Ne"+num_ele+" "+"C"+j+" "+ poidNoeudElement[num_ele][j]+"|");
System.out.print( poidNoeudElement[num_ele][j]+"|");
      
      System.out.println("");
 
num_ele++;//Noeud Elément suivant

}//fin noeud élément**************************************************************
 
}




}
