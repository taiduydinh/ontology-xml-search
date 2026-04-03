/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prototype;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author IDDIR
 */

public class ConceptG implements java.io.Serializable{
  int id;
  String nom;
  String type;
  List doc_attaché=new LinkedList();
  public ConceptG() {

    }
   public String getty()
   {
     return type;
   }
   public void setid(int i)
   {
   id=i;
   }
   public void setnom(String s)
  {
  nom=s;
  }
 
  public void settype (String s)
  {
  type=s;
  }
  public  String getnom()
  {
    return nom;
  }
  public  int getid()
 {
   return id;
  }
  public List get_doc_attaché()
  {
    return    doc_attaché;
  }


}

    

