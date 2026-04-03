package Prototype;
import java.util.*;
import java.io.Serializable;

public class Scénario extends ConceptG implements Serializable{ 
List contribue_a_enseigner=new LinkedList();// liste des concepts  que le scénario contribue à enseigner 
List enseigne= new LinkedList();// liste des éléments de connaissances que le scénario enseigne 
List précédé_par =new LinkedList(); // liste des scénarios précédents
List précède=new LinkedList();// liste des  scénarios suivants
String adresse;// adresse physique du scénario
didacticiel appartient_a_didac=null;//le nom de didacticiel qui contient ce scénario
   
   

public Scénario()
{ super();}
public String get_type()
{ return "Scénario";}      
        
//public void set_type()
//{type = "Scénario";
//}


 
public  didacticiel get_appartient_did()
{   return appartient_a_didac;}

public void set_adresse (String ad) 
{  adresse=ad; }

public String get_adresse () 
{ return adresse; }


public  List get_précédé_par()
{   return précédé_par;}


public  List get_précède()
{   return précède;}

public  List get_scénarioprécède()
{   return précède;}

public  List get_contribuàenseigner()
{   return contribue_a_enseigner;}

public  List get_enseigne()
{   return enseigne;}


}
