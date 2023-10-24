/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Objecttype;
import entities.Regime;
import java.util.List;

/**
 *
 * @author Kardo
 * @param <R>
 */
public interface Iregime<R> {
    
   
    void ajouterregime(R r);
    void supprimerregime(R r);
    void modifierregime(R r);
    List<R> affihcerregime();
    Regime getRegimeParId(int id);
    List<Regime> getRegimeParobjectifreg(Objecttype objectifreg);
    
  
     
    
}
