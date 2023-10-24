/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

import java.util.List;
import offregestion.reservation ;
/**
 *
 * @author jaafr
 */
public interface Iservicereservation<reservation> {
    public void ajouter(reservation r);
    public void modifier(reservation r);
    public void supprimer(int id);
    public reservation getOne(reservation r);
    public List<reservation> getAll(reservation r);
}
