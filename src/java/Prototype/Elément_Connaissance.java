package Prototype;
import java.util.*;
import java.io.Serializable;

public class Elément_Connaissance extends ConceptG implements Serializable{ 

List précédé_par =new LinkedList(); // liste des élément de connaisances précédents
List précéde=new LinkedList();// liste des élément de connaisances suivants

     List dépendre_de=new LinkedList();
     List est_dépendu_de=new LinkedList();
     Scénario est_eneigné_par=null;
     Concept  appartient_a_concept=null;
public Elément_Connaissance(){super();}
  

public void settype()
{type="Element de Connaissance";
}
public String gettype()
{
  return "Element de Connaissance";
}
public void add_précédé_par(Elément_Connaissance ec)
{
précédé_par.add(ec);
}
public void supp_précédé_par(int ec)
{
précédé_par.remove(ec);
}
public void add_précédé(Elément_Connaissance ec)
{
précéde.add(ec);
}
public void supp_précédé(int  ec)
{
précéde.remove(ec);
}
public void add_dépendre_de(Elément_Connaissance ec)
{
dépendre_de.add(ec);
}
public void supp_dépendre_de(int  ec)
{
dépendre_de.remove(ec);
}
public void add_est_dépendu_de(Elément_Connaissance ec)
{
est_dépendu_de.add(ec);
}
public void supp_est_dépendu_de(int  ec)
{
est_dépendu_de.remove(ec);
}
public void add_est_enseigné_par_scé(Scénario s)
{
 est_eneigné_par=s;
}
public void supp_est_enseigné_par_scé(Scénario s)
{
 est_eneigné_par=null;
}
public void add_appartient_a_con(Concept c)
{
 appartient_a_concept=c;
}
public void supp_appartient_a_con()
{
 appartient_a_concept=null;
}
public List get_précédé_par()
{
  return précédé_par;
}
public List get_précéde()
{
  return précéde;
}
public List get_dépendre()
{
  return dépendre_de;
}
public List get_est_dépendu_de()
{
  return est_dépendu_de;
}
public Scénario get_est_enseigné_par()
{
  return est_eneigné_par;
}
public Concept get_appartient_a_concept()
{
  return appartient_a_concept ;
}
}