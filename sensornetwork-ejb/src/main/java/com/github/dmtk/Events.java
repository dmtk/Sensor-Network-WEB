package com.github.dmtk;

import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class Events{

    public List getList(){
       
        
              
        return NetworkController.getEvents();
    }
}
