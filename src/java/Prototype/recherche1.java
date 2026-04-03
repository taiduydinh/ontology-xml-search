package Prototype;

import Prototype.docpond;
import java.util.*;
import com.hp.hpl.jena.util.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.reasoner.*;
import com.hp.hpl.jena.reasoner.rulesys.*;


public class recherche1 {
  public recherche1() {
  }
// fonction qui retourne la liste des documents attach�s a un concept donn� tri�s
public  Vector return_liste_doc(String s)
{
String NS = "http://localhost:8080/unnamed.owl#";
OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
m.read("file:C:/Users/PCPC/Documents/NetBeansProjects/Webprototype/onto.owl");
Individual ind = m.getIndividual(NS+s);
// liste qui va contenir les documents attach�s
 Vector vect=new Vector();
StmtIterator  li= ind.listProperties(m.getProperty(NS+"collection-doc"));
while (li.hasNext())
{
 Resource rs=((Statement)li.next()).getResource();
 StmtIterator it=rs.listProperties(m.getProperty(NS+"doc"));
 StmtIterator it1=rs.listProperties(m.getProperty(NS+"poid-concept"));
 while((it.hasNext())&&(it1.hasNext()))
 {
  String s1=((Statement)it.next()).getResource().getLocalName();
  float f=((Statement)it1.next()).getFloat();
  docpond dp=new docpond();
  dp.set_nom_doc(s1);
  dp.set_poids(f);
  vect.add(dp);
 }
}
int n=vect.size();
int i=0;
int j=0;
while (i<n-1)
   {
     docpond d1=new docpond();
     d1=(docpond)vect.elementAt(i);
     double p=d1.get_poids();
     for (j=i+1;j<n;j++)
     {
       docpond d2=new docpond();
       d2=(docpond)vect.elementAt(j);
       double p1=d2.get_poids();
       if (p<p1)
       {
        docpond dt=new docpond();
        dt=d1;
        d1=d2;
        d2=dt;
        vect.setElementAt(d1,i);
        vect.setElementAt(d2,j);
      }

     }
    i++;
}
return vect;

}
public  Vector return_liste_doc_sans_tri(String s)
{
String NS = "http://localhost:8080/unnamed.owl#";
OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
m.read("file:/E:/prototype_revu/Tomcat/testpathway.owl");
Individual ind = m.getIndividual(NS+s);
// liste qui va contenir les documents attach�s
 Vector vect=new Vector();
if (vect.size()!=0)
{
StmtIterator  li= ind.listProperties(m.getProperty(NS+"collection-doc"));
while (li.hasNext())
{
 Resource rs=((Statement)li.next()).getResource();
 StmtIterator it=rs.listProperties(m.getProperty(NS+"doc"));
 StmtIterator it1=rs.listProperties(m.getProperty(NS+"poid-concept"));
 while((it.hasNext())&&(it1.hasNext()))
 {
  String s1=((Statement)it.next()).getResource().getLocalName();
  float f=((Statement)it1.next()).getFloat();
  docpond dp=new docpond();
  dp.set_nom_doc(s1);
  dp.set_poids(f);
  vect.add(dp);
 }
}
}
return vect;
}
public  String return_type(String s)
{
String s1="";
String NS = "http://localhost:8080/unnamed.owl#";
OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
m.read("file:/E:/prototype_revu/Tomcat/testpathway.owl");
OntClass oc=m.getOntClass(NS+"Article");
Iterator it=oc.listInstances();
while (it.hasNext())
{
Individual ind=(Individual)it.next();
if (ind.getLocalName().equals(s))
{
  s1="Article";
}
}

//
OntClass oc1=m.getOntClass(NS+"Article-scientifique");
Iterator it1=oc1.listInstances();
while (it1.hasNext())
{
Individual ind=(Individual)it1.next();
if (ind.getLocalName().equals(s))
{
  s1="Article-scientifique";
}
}
//
OntClass oc2=m.getOntClass(NS+"Page-web");
Iterator it2=oc2.listInstances();
while (it2.hasNext())
{
Individual ind=(Individual)it2.next();
if (ind.getLocalName().equals(s))
{
  s1="Page-web";
}
}
//
OntClass oc3=m.getOntClass(NS+"Journal");
Iterator it3=oc3.listInstances();
while (it3.hasNext())
{
Individual ind=(Individual)it3.next();
if (ind.getLocalName().equals(s))
{
  s1="Journal";
}
}
//
OntClass oc4=m.getOntClass(NS+"Proceeding");
Iterator it4=oc4.listInstances();
while (it4.hasNext())
{
Individual ind=(Individual)it4.next();
if (ind.getLocalName().equals(s))
{
  s1="Proceeding";
}
}
//
OntClass oc5=m.getOntClass(NS+"Livre");
Iterator it5=oc5.listInstances();
while (it5.hasNext())
{
Individual ind=(Individual)it5.next();
if (ind.getLocalName().equals(s))
{
  s1="Livre";
}
}
//
OntClass oc6=m.getOntClass(NS+"Note");
Iterator it6=oc6.listInstances();
while (it6.hasNext())
{
Individual ind=(Individual)it6.next();
if (ind.getLocalName().equals(s))
{
  s1="Note";
}
}
//
OntClass oc7=m.getOntClass(NS+"Manuel");
Iterator it7=oc7.listInstances();
while (it7.hasNext())
{
Individual ind=(Individual)it7.next();
if (ind.getLocalName().equals(s))
{
  s1="Manuel";
}
}
OntClass oc8=m.getOntClass(NS+"Guide");
Iterator it8=oc8.listInstances();
while (it8.hasNext())
{
Individual ind=(Individual)it8.next();
if (ind.getLocalName().equals(s))
{
  s1="Guide";
}
}
return s1;
// une fonction qui retourne les documents li�s

}
public  String return_type_concept(String s)
{
String NS = "http://localhost:8080/unnamed.owl#";
OntModel m1 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
m1.read("file:/E:/prototype_revu/Tomcat/testpathway.owl");
OntClass oc1=m1.getOntClass(NS+"didacticiel");
Iterator  it1=oc1.listInstances();
String s1="";
while (it1.hasNext())
{
  Individual in=(Individual)it1.next();
  if (in.getLocalName().equals(s))
  {
   s1="didacticiel";
  }
}
OntClass oc2=m1.getOntClass(NS+"scenario");
Iterator  it2=oc2.listInstances();
while (it2.hasNext())
{
  Individual in=(Individual)it2.next();
  if (in.getLocalName().equals(s))
  {
   s1="scenario";
  }
}
OntClass oc3=m1.getOntClass(NS+"concept");
Iterator  it3=oc3.listInstances();
while (it3.hasNext())
{
  Individual in=(Individual)it3.next();
  if (in.getLocalName().equals(s))
  {
   s1="concept";
  }
}
OntClass oc4=m1.getOntClass(NS+"element_de_connaissance");
Iterator  it4=oc4.listInstances();
while (it4.hasNext())
{
  Individual in=(Individual)it4.next();
  if (in.getLocalName().equals(s))
  {
   s1="element_de_connaissance";
  }
}
return s1;
}
public Vector fusion_vect(Vector v1,Vector v2)
{
Vector vect=new Vector();
int i=0;
int j=0;
int n1=v1.size();
int n2=v2.size();
if (n1>0)
{
  while (i<n1)
  {
    vect.addElement(v1.elementAt(i));
    i++;
  }
}
if (n2>0)
{
  while (j<n2)
  {
    vect.addElement(v2.elementAt(j));
    j++;
  }
}

 return vect;
}
public Vector return_doc_inferes(String s)
{
Vector vect=new Vector();
String NS = "http://localhost:8080/unnamed.owl#";
Model data = ModelLoader.loadModel("file:/E:/prototype_revu/Tomcat/testpathway.owl");
String demo= "http://localhost:8080/unnamed.owl#";
String rules =

// la transitivit�
"[(?d1 "+demo+"implication ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"implication ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d1  "+demo+"shortCut ?d4)  makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"implication ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1  "+demo+"shortCut ?d4)]"+

"[(?d1 "+demo+"sous-type ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"sous-type ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d1  "+demo+"shortCut ?d4)  makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"sous-type ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1  "+demo+"shortCut ?d4)]"+

"[(?d1 "+demo+"sequentiel ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"sequentiel ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d1  "+demo+"shortCut ?d4)  makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"sequentiel ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1  "+demo+"shortCut ?d4)]"+

"[(?d1 "+demo+"reference ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d1  "+demo+"shortCut ?d4)  makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"reference ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1  "+demo+"shortCut ?d4)]"+
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"cause-resultat ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d1  "+demo+"shortCut ?d4)  makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1  "+demo+"shortCut ?d4)]"+

// cause-resultat.implication ==> cause-resultat
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"implication ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"cause-resultat ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"implication ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"cause-resultat) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4) (?d1 "+demo+"shortCut ?d4)]"+

// cause-resultat.sous-type ==> cause-resultat
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"sous-type ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"cause-resultat ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"sous-type ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"cause-resultat)  noValue(?d2,"+demo+"implication)  noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1 "+demo+"shortCut ?d4)]"+

// cause-resultat.instance ==> cause-resultat
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"instance ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"cause-resultat ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"instance ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"cause-resultat) noValue(?d2,"+demo+"implication) noValue(?d2,"+demo+"sous-type) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4) (?d1 "+demo+"shortCut ?d4)]"+

// cause-resultat.reference ==> cause-resultat
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"cause-resultat ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"cause-resultat ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"cause-resultat) noValue(?d2,"+demo+"implication) noValue(?d2,"+demo+"sous-type) noValue(?d2,"+demo+"instance) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"cause-resultat ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4) (?d1 "+demo+"shortCut ?d4)]"+

// implication .sous-type ==> implication
"[(?d1 "+demo+"implication ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"sous-type ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"implication ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"implication ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"implication ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"sous-type ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"implication) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+" implication?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1 "+demo+"shortCut ?d4)]"+

// implication .reference ==> implication
"[(?d1 "+demo+"implication ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"implication ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"implication ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"implication ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"implication) noValue(?d2,"+demo+"sous-type) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"implication ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d4)(?d1 "+demo+"shortCut ?d4)]"+

// sous-type.reference ==> sous-type
"[(?d1 "+demo+"sous-type ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"sous-type ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"sous-type ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"sous-type ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"reference ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"cause-resultat) noValue(?d2,"+demo+"implication) noValue(?d2,"+demo+"sous-type) noValue(?d2,"+demo+"instance) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"sous-type ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final-final ?d4) (?d1 "+demo+"shortCut ?d4)]"+

// sous-type.instance ==> sous-type
"[(?d1 "+demo+"sous-type ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"instance ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) (?d2,"+demo+"sous-type ?l2)(?l2 "+demo+"degree-certitude ?cd2) (?l2 "+demo+"document-final ?d3)   noValue(?d1  "+demo+"shortCut ?d3) makeTemp(?l3) min(?cd1, ?cd2, ?cd3) -> (?d1 "+demo+"sous-type ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final ?d3)(?d1 "+demo+"shortCut ?d3)]"+
"[(?d1 "+demo+"sous-type ?l1) (?l1 "+demo+"degree-certitude ?cd1)(?l1 "+demo+"document-final ?d2) (?d2 "+demo+"instance ?l4) (?l4 "+demo+"degree-certitude ?cd4) (?l4 "+demo+"document-final ?d4) noValue(?d2,"+demo+"cause-resultat) noValue(?d2,"+demo+"implication) noValue(?d2,"+demo+"sous-type) noValue(?d1  "+demo+"shortCut ?d4) makeTemp(?l3) min(?cd1, ?cd4, ?cd3) -> (?d1 "+demo+"sous-type ?l3) (?l3 "+demo+"degree-certitude ?cd3) (?l3 "+demo+"document-final-final ?d4) (?d1 "+demo+"shortCut ?d4)]";

Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
InfModel inf = ModelFactory.createInfModel(reasoner, data);
Resource r=data.getResource(NS+s);
Property p = data.getProperty(NS,"cause-resultat");
Property p1 = data.getProperty(NS,"implication");
Property p2= data.getProperty(NS,"sous-type");
Property p3= data.getProperty(NS,"instance");
Property p4= data.getProperty(NS,"reference");
Property p5= data.getProperty(NS,"sequentiel");
Property p6= data.getProperty(NS,"similaire");
StmtIterator i=inf.listStatements(r,p,(RDFNode)null);
StmtIterator i1=inf.listStatements(r,p1,(RDFNode)null);
StmtIterator i2=inf.listStatements(r,p2,(RDFNode)null);
StmtIterator i3=inf.listStatements(r,p3,(RDFNode)null);
StmtIterator i4=inf.listStatements(r,p4,(RDFNode)null);
StmtIterator i5=inf.listStatements(r,p5,(RDFNode)null);
StmtIterator i6=inf.listStatements(r,p6,(RDFNode)null);
boolean b,b1,b2,b3,b4,b5,b6;
b=b1=b2=b3=b4=b5=true;
while (i.hasNext()) {
  Resource rs=((Statement)i.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext()) {
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
  b=false;
}
}
if (b)
{
while (i1.hasNext()) {
  Resource rs=((Statement)i1.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext()) {
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
  b1=false;
}
}
}
if (b1)
{
while (i2.hasNext()) {
  Resource rs=((Statement)i2.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext()) {
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
  b2=false;
}
}
}
if (b1&&b2)
{
while (i3.hasNext()) {
  Resource rs=((Statement)i3.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext()) {
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
  b3=false;
}
}
}
if (b1&&b2&&b3)
{
while (i4.hasNext()) {
  Resource rs=((Statement)i4.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext()) {
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
  b4=false;
}
}
}
while (i5.hasNext()) {
  Resource rs=((Statement)i5.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext()) {
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
  b5=false;
}
}


while (i6.hasNext()) {
  Resource rs=((Statement)i6.next()).getResource();
  StmtIterator it=rs.listProperties(inf.getProperty(NS+"document-final"));
while (it.hasNext())
{
  String s1=((Statement)it.next()).getResource().getLocalName();
  vect.add(s1);
}
}



return vect;
}
public String remplace(String n)
{
int j=1;
 String s="";
while(j<(n.length()))
   {
  s=n.replace(' ','_');
  j++;
   }
   return s;
}
public String remplace1(String n)
{
  String s="";
int j=1;
while(j<(n.length()))
   {
  s=n.replace('_',' ');
  j++;
   }
   return s;
}
public void ajout_chaine(String s,Vector vecteur)
{
vecteur.addElement(s);
}
public boolean existe(String s, Vector vecteur)
{
  boolean b=false;
  Iterator it=vecteur.iterator();
  while (it.hasNext())
  {
   if(it.next().toString().equals(s))
   {
     b=true;
   }
  }
  return b;

}
}
