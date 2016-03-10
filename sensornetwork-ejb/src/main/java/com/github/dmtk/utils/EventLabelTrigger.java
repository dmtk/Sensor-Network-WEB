package com.github.dmtk.utils;

import com.github.dmtk.entity.NetworkEvent;

public class EventLabelTrigger{
    
    private static double dangerValuehigh = 300;
    private static double dangerValuelow = 55;
    
    private static double warningValuehigh = 55;
    private static double warningValuelow = 35;
    
    private static double infoValuehigh = 35;
    private static double infoValuelow = 25;
    
    private static double successValuehigh = 25;
    private static double successValuelow = 10;
    
    private static double defaultValuehigh = 10;
    private static double defaultValuelow = 0;
    
    private static double primaryValuehigh = 0;
    private static double primaryValuelow = -40;
    
    public static void chooseLabel(NetworkEvent e) {
        
        double value = e.getValue();
        if ((dangerValuelow < value) && (value <= dangerValuehigh)) {
            e.setLabel("danger");
        } else if ((warningValuelow < value) && (value <= warningValuehigh)) {
            e.setLabel("warning");
        } else if ((infoValuelow < value) && (value <= infoValuehigh)) {
            e.setLabel("info");
        } else if ((successValuelow < value) && (value <= successValuehigh)) {
            e.setLabel("success");
        } else if ((defaultValuelow < value) && (value <= defaultValuehigh)) {
            e.setLabel("default");
        } else if ((primaryValuelow < value) && (value <= primaryValuehigh)) {
            e.setLabel("primary");
        }
        
    }
}
