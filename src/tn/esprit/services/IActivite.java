/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;

/**
 *
 * @author Asus
 * @param <Activite>
 */
public interface IActivite <Activite>{
      void ajouter(Activite A);
      void supprimer(String nomActivite);
      void modifier(Activite A);
    List<Activite> affihcer();
     Activite getById(int id);
}
