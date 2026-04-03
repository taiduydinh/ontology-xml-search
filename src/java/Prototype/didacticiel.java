package Prototype;

import java.util.*;
import java.io.Serializable;

public class didacticiel extends ConceptG implements Serializable{ 
List constitué_de=new LinkedList();// liste des scénarios qui le constitue
List enseigne= new LinkedList();// liste des concepts  que le didacticiel enseigne

public didacticiel(){super();}


public void add_constué_scénario(Scénario s)
{   List List = null;
 constitué_de=(List) ;}

public void add_enseigner(Concept c) 
{  enseigne.add(c); }

public void supp_enseigner(int c) 
{  enseigne.remove(c); }
}
