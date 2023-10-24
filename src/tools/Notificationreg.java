/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;


import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;

/**
 *
 * @author Kardo
 */
public class Notificationreg {
  public void notificationreg() {
    Platform.runLater(() -> {
        Notifications not = Notifications.create()
            .title("NOTIFICATION")
            .text("Nouvelle Mise à Jour ")
            .position(Pos.TOP_LEFT)
            .hideAfter(Duration.seconds(50));
        not.showInformation();
        not.showConfirm();
    });  
}}
