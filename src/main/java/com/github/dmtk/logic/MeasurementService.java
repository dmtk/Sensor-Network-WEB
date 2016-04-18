package com.github.dmtk.logic;

import com.github.dmtk.dao.MeasurementDAO;
import com.github.dmtk.entity.Measurement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementDAO measurementDAO;

    @Transactional
    public void save(Measurement measurement) {
        measurementDAO.save(measurement);
    }

    @Transactional
    public List<Measurement> getList() {
        return measurementDAO.getList();
    }
    
    @Transactional
    public List<Measurement> getList(int resultsCount) {
        return measurementDAO.getList(resultsCount);
    }
            

    @Transactional
    public void remove(Long id) {
        measurementDAO.remove(id);
    }

    @Transactional
    public Measurement retrive(Long id) {
        return measurementDAO.retrive(id);
    }
    
    @Transactional
    public List<Measurement> findBySensorId(int id) {
        return measurementDAO.findBySensorId(id);
    }
    
    @Transactional
    public Measurement findBySensorIdOrderByDate(Integer id) {
        return measurementDAO.getLastBySensorId(id);
    }
}
