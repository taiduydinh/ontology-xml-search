package Prototype;
import  java.io.*;
public class docpond implements Serializable {
  String nom_doc;
  float poids;

  public docpond() {
  }

  public void set_nom_doc(String s )
   {
     nom_doc=s;
   }
   public void set_poids(float c )
   {
    poids=c;
   }
   public String get_nom_doc()
   {
     return nom_doc;
   }
   public float get_poids()
   {
     return poids;
   }
}
