package com.github.dmtk.dao;

import com.github.dmtk.entity.NetworkEvent;
import java.util.List;
import javax.ejb.Local;

@Local
public interface NetworkEventDAOLocal {

    void create(NetworkEvent networkEvent);

    void edit(NetworkEvent networkEvent);

    void remove(NetworkEvent networkEvent);

    NetworkEvent find(Object id);

    List<NetworkEvent> findAll();

    List<NetworkEvent> findRange(int[] range);

    int count();

    List<NetworkEvent> findById(Integer id, Integer count);

    List<NetworkEvent> findByDate(Integer count);

    Double getAvg(Integer id);
}
