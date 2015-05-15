package com.github.dmtk;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class ComPortFacade {

    /*ComPort comport = new ComPort();
     public String getData() {
     return comport.getData();
     }*/
    
    @EJB NetworkController controller;
    private String data = "";
    public void emulate() {

        Thread myThready;
        myThready = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int value = (int) Math.round(Math.random() * 100 - 20);
                    int node = (int) Math.round(Math.random() * 8);
                    data = "Sensor " + node + " Value " + value;
                    
                    controller.handle(node,value);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComPort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        myThready.start();
    }

    public String getData() {
        return data;
    }

}
