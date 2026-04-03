/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prototype;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import java.util.Vector;

/**
 *
 * @author PCPC
 */
public class CalculeScore {
    //int Score=0;
       int sommeR=0;
       float[] sommeNe =new float [4000];

    Vector VecteurScore = new Vector();
    float Score;
    public void Score() throws ClassNotFoundException, SQLException {
    
       IndexationNe n=new  IndexationNe(); 
       IndexationRequete m= new IndexationRequete();
        n.Nbr_Nt_Conc_Desendant ();  
 n.EspaceCenceptuel();
n.NbrNoeudtext ();
n.Pond_globale();
n.Pond_locale();
n.NoeudElement(); 
 m.requete();
      for (int i=0; i<=m.Vecrequete.length-1;i++){
         sommeR =(int)m.Vecrequete[i]+ sommeR;
        
      }
      System.out.println("");
      System.out.println("la sommeR= "+sommeR);
     
 

for (int i=0; i<= n.Ne-1;i++){
       for (int j=0; j<=n.EspaceConceptuel.size()-1;j++)
        sommeNe[i] =(float)(n.poidNoeudElement[i][j])+ (float)sommeNe[i];
       System.out.println("la sommeNe"+i+" "+sommeNe[i]);
         }
     
      
      for (int i=0; i<= n.Ne-1;i++)
      {Score=0;
       for (int j=0; j<= n.EspaceConceptuel.size()-1;j++){
           if (sommeNe[i]==0) {Score=0;
          } else {
               Score=Score+(( (int)m.Vecrequete[j]*(float) n.poidNoeudElement[i][j])* (float)sqrt(pow(sommeR,-2))* (float)sqrt(pow(sommeNe[i],-2)));
          }
       } VecteurScore.add(Score);
       }
    for (int i=0; i<= n.Ne-1;i++)
        System.out.println("le score de Ne"+i+" = "+VecteurScore.elementAt(i));
               
      Class.forName("com.mysql.jdbc.Driver");
Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
Statement stmt=cnx.createStatement(); 
Statement stmt1=cnx.createStatement(); 
Statement stmt2=cnx.createStatement(); 
//int res=stmt.executeUpdate()
int k=0;
int res=stmt1.executeUpdate("Delete from score");
 ResultSet res1 =stmt.executeQuery(" Select idf_doc, début, fin  from element"); 
 while(res1.next())
{ int doc=res1.getInt(1);
 int deb=res1.getInt(2);
 int fin=res1.getInt(3);

int res2 =stmt1.executeUpdate(" insert into score values ("+doc+","+deb+","+fin+","+VecteurScore.elementAt(k)+")");
k++;

}
 
    }
    }
    
    
    
    

