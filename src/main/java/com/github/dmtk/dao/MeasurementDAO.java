package com.github.dmtk.dao;

import com.github.dmtk.entity.Measurement;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementDAO extends GenericDAO<Measurement> {

    public List<Measurement> getList(int resultsCount) {
        return sessionFactory.getCurrentSession().createQuery("from Measurement").setMaxResults(resultsCount).list();
    }

    public List<Measurement> getListOrderByDate(int resultsCount) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getOrderByDate");
        return query.setMaxResults(resultsCount).list();
    }

    public Measurement getLast() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getOrderByDate");
        return (Measurement) query.setMaxResults(1).uniqueResult();
    }

    public List<Measurement> findBySensorId(Integer id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findBySensorId").setInteger("sensorId", id);
        return query.list();
    }

    public Measurement getLastBySensorId(Integer id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findBySensorIdOrderByDate").setInteger("sensorId", id);
        return (Measurement) query.setMaxResults(1).uniqueResult();
    }

    public Measurement getLastBySensorName(String name) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findBySensorNameOrderByDate").setString("sensorName", name);
        return (Measurement) query.setMaxResults(1).uniqueResult();
    }

    public List<Measurement> getListBySensorNameOrderByDate(String name, int resultsCount) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findBySensorNameOrderByDate").setString("sensorName", name);
        return query.setMaxResults(resultsCount).list();
    }

    public List<Measurement> getListBySensorIdOrderByDate(Integer id, int resultsCount) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findBySensorIdOrderByDate").setInteger("sensorId", id);
        return query.setMaxResults(resultsCount).list();
    }

   public Long getCount() {
        return (Long) sessionFactory.getCurrentSession().getNamedQuery("getCount").uniqueResult();
    }
}
