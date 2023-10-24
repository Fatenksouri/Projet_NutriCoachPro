/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;
import javafx.util.Duration;

import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author jaafr
 */
public class NotificationUtil {
     public static void showNotification(String title, String message, NotificationType type) {
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(5));
    }
}
