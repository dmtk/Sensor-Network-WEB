package com.github.dmtk.logic;

import com.github.dmtk.dao.SensorNodeDAO;
import com.github.dmtk.entity.SensorNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorNodeService {

    @Autowired
    private SensorNodeDAO sensorNodeDAO;

    @Transactional
    public void save(SensorNode node) {
        sensorNodeDAO.save(node);
    }

    @Transactional
    public List<SensorNode> getList() {
        return sensorNodeDAO.getList();
    }

    @Transactional
    public void remove(Long id) {
        sensorNodeDAO.remove(id);
    }

    @Transactional
    public SensorNode retrive(Long id) {
        return sensorNodeDAO.retrive(id);
    }
}
