/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Asus
 */
public class Notification_ac {
    public void notification_ac() {
    Platform.runLater(() -> {
        Notifications not = Notifications.create()
            .title("NOTIFICATION")
            .text("Nouvelle Mise à Jour ")
            .position(Pos.TOP_RIGHT)
            .hideAfter(Duration.seconds(50));
        not.showInformation();
        not.showConfirm();
    });
}
    
}
