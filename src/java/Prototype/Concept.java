package Prototype;
import java.util.*;
import java.io.Serializable;

public class Concept extends ConceptG implements Serializable{ 
List formé_de=new LinkedList();// liste des éléments de connaissances qui forme le concept
List précédé_par =new LinkedList(); // liste des Concepts précédents
List précéde=new LinkedList();// liste des  Concepts suivants
List dépendre_de=new LinkedList();
List  est_dépendu_de=new LinkedList(); 
List  contribue_enseiger=new LinkedList();
     
didacticiel est_enseigné=null;
public Concept(){super();}



  public  String getty()
  {
    return "Concept";
  }
  public void settype()
  {
   // type="Concept";
  }


  public void add_est_enseigné_par_did(didacticiel d)
  {
   est_enseigné=d;
  }
  public void supp_est_enseigné_par_did(didacticiel d)
  {
   est_enseigné=null;
  }
  public void add_contribue_a_enseigné(Scénario s)
  {
  contribue_enseiger.add(s);
  }
  public void supp_contribue_a_enseigné(int s)
  {
  contribue_enseiger.remove(s);
  }
  public void addpré_par(Concept c)
  {
    précédé_par.add(c);
  }
  public void supppré_par(int c)
  {
    précédé_par.remove(c);
  }
  public void addprécéde(Concept c)
  {
    précéde.add(c);
  }
  public void suppprécéde(int c)
  {
    précéde.remove(c);
  }
  public void add_dépend(Concept c)
  {
    dépendre_de.add(c);
  }
  public void supp_dépend(int c)
  {
    dépendre_de.remove(c);
  }
  public void adddépendu_de(Concept c)
  {
   est_dépendu_de.add(c);
  }
  public void suppdépendu_de(int c)
  {
   est_dépendu_de.remove(c);
  }

  public void addformé(Elément_Connaissance ec)
  {
   formé_de.add(ec);
  }
  public void suppformé(int ec)
  {
     formé_de.remove(ec);
  }
public List get_préc()
{
  return précédé_par;
}
public List get_précéde()
{
  return précéde;
}
public List getdép()
{
  return dépendre_de;
}
public List getest_dép()
{
  return est_dépendu_de;
}

public List getformé()
{
  return formé_de;
}
public didacticiel getestenseigé()
{
  return est_enseigné;
}
public List getscéenseige()
{
  return contribue_enseiger;
}

public didacticiel get_est_enseigné()
{
  return est_enseigné;
}

}

