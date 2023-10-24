/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaafr
 */
public interface Iserviceoffre<offre> {
     public void ajouter(offre o);
    public void modifier(offre o);
    public void supprimer(int id);
    public List<offre> chercherParType(typeoffre type);

    public List<offre> getAll(offre o);
}
