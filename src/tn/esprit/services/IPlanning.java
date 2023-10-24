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
 * @param <Planning>
 */
public interface IPlanning <Planning>{
    void ajouter(Planning P);
    void supprimer(int idPlanning);
    void modifier(Planning P);
    List<Planning> affihcer();
     Planning getById(int id);
    
}
