/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

/**
 *
 * @author jaafr
 */
public class StatistiqueType {
       private String type;
    private int offres;
    private int reservations;

    public StatistiqueType(String type, int offres, int reservations) {
        this.type = type;
        this.offres = offres;
        this.reservations = reservations;
    }

    public String getType() {
        return type;
    }

    public int getOffres() {
        return offres;
    }

    public int getReservations() {
        return reservations;
    }
}
