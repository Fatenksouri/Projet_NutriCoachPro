/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.FicheMesures;
import java.util.List;

/**
 *
 * @author Kardo
 * @param <F>
 */
public interface Ifichemesures <F> {
    
    void ajouterfichmes(F f);
    void supprimerfichmes(F f);
    void modifierfichmes(F f);
    List<F> affihcerfichemes();
  public FicheMesures getFicheMesuresParpseudo(String pseudofich);
   double calculer_imc(FicheMesures ficheMesures);
  
   
    
}
