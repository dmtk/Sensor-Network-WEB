package com.github.dmtk;

import java.util.List;
import javax.ejb.Local;

@Local
public interface NetworkEventFacadeLocal {

    void create(NetworkEvent networkEvent);

    void edit(NetworkEvent networkEvent);

    void remove(NetworkEvent networkEvent);

    NetworkEvent find(Object id);

    List<NetworkEvent> findAll();

    List<NetworkEvent> findRange(int[] range);

    int count();
    
}
