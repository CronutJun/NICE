package com.nicetcm.nibsplus.broker.ams.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

public class AMSBrokerJMXClientListener implements NotificationListener {

    @Override
    public void handleNotification(Notification notification, Object handback) {

        //echo("\nReceived notification:");
        //echo("\tClassName: " + notification.getClass().getName());
        //echo("\tSource: " + notification.getSource());
        //echo("\tType: " + notification.getType());
        System.out.println("Message: " + notification.getMessage());
    }

}
