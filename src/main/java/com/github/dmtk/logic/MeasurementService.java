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
    public Measurement getLast() {
        return measurementDAO.getLast();
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
    public Measurement getLastBySensorId(Integer id) {
        return measurementDAO.getLastBySensorId(id);
    }

    @Transactional
    public Measurement getLastBySensorName(String name) {
        return measurementDAO.getLastBySensorName(name);
    }

    @Transactional
    public List<Measurement> findBySensorIdOrderByDate(Integer id, int resultsCount) {
        return measurementDAO.getListBySensorIdOrderByDate(id, resultsCount);
    }

    @Transactional
    public List<Measurement> getListBySensorNameOrderByDate(String name, int resultsCount) {
        return measurementDAO.getListBySensorNameOrderByDate(name, resultsCount);
    }

    @Transactional
    public List<Measurement> getListOrderByDate(int resultsCount) {
        return measurementDAO.getListOrderByDate(resultsCount);
    }

    @Transactional
    public List<Measurement> getPage(int pageNumber, int pageSize) {
        return measurementDAO.getPage(pageNumber, pageSize);
    }

    @Transactional
    public Long getCount() {
        return measurementDAO.getCount();
    }

}
