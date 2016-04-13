package com.github.dmtk.logic;

import com.github.dmtk.dao.SensorDAO;
import com.github.dmtk.entity.Sensor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorService {

    @Autowired
    private SensorDAO sensorDAO;

    @Transactional
    public void save(Sensor node) {
        sensorDAO.save(node);
    }

    @Transactional
    public List<Sensor> getList() {
        return sensorDAO.getList();
    }

    @Transactional
    public void remove(Long id) {
        sensorDAO.remove(id);
    }

    @Transactional
    public Sensor retrive(Long id) {
        return sensorDAO.retrive(id);
    }
}
