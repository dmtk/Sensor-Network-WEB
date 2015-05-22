package com.github.dmtk.logic;

import com.github.dmtk.entity.NetworkEvent;
import javax.ejb.Local;

@Local
public interface EventLabelTriggerLocal {

    public void change(NetworkEvent n);
    
}
