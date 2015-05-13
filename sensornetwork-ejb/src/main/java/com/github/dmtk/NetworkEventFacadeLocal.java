/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dmtk;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
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
