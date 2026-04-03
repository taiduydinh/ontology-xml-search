package Prototype;

import java.sql.SQLException;




public class calculs{


 
//----------------------------------------------------------------------

    public static void main(String[] args) throws ClassNotFoundException, SQLException { 


        IndexationNe n= new IndexationNe();
 n.EspaceCenceptuel();
n.NbrNoeudtext ();
n.Pond_globale();
n.Pond_locale();
//n.ponderationNoeudTexte();
//n.Nbr_Nt_Desendant () ;
//n.Nbr_Nt_Conc_Desendant ();
//System.out.println("pondération locale"+l);
//n.ponderation();
n.NoeudElement();
}
  
   // } 
 
    
    
}
