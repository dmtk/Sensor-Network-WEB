package com.github.dmtk.dao;

import com.github.dmtk.entity.Measurement;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Measurement measurement) {
        sessionFactory.getCurrentSession().saveOrUpdate(measurement);
    }

    @SuppressWarnings("unchecked")
    public List<Measurement> getList() {
        return sessionFactory.getCurrentSession().createQuery("from Measurement").list();
    }
    
    public List<Measurement> getList(int resultsCount) {
        return sessionFactory.getCurrentSession().createQuery("from Measurement").setMaxResults(resultsCount).list();
    }
    
    public List<Measurement> getListOrderByDate(int resultsCount) {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("getOrderByDate");
        return query.setMaxResults(resultsCount).list();
    }

    public void remove(Long id) {
        Measurement country = (Measurement) sessionFactory.getCurrentSession().load(Measurement.class, id);
        if (null != country) {
            sessionFactory.getCurrentSession().delete(country);
        }
    }

    public Measurement retrive(Long id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Measurement where id = :id");
        q.setLong("id", id);
        return (Measurement) q.uniqueResult();
    }

    public List<Measurement> findBySensorId(Integer id) {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("findBySensorId").setInteger("sensorId",id);
        return query.list();
    }
    
    public Measurement getLastBySensorId(Integer id) {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("findBySensorIdOrderByDate").setInteger("sensorId",id);
        return (Measurement) query.setMaxResults(1).uniqueResult();
    }
    
    public Measurement getLastBySensorName(String name) {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("findBySensorNameOrderByDate").setString("sensorName",name);
        return (Measurement) query.setMaxResults(1).uniqueResult();
    }
    public List<Measurement> getListBySensorNameOrderByDate(String name,  int resultsCount) {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("findBySensorNameOrderByDate").setString("sensorName",name);
        return query.setMaxResults(resultsCount).list();
    }
    
    public List<Measurement> getListBySensorIdOrderByDate(Integer id,  int resultsCount) {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("findBySensorIdOrderByDate").setInteger("sensorId",id);
        return query.setMaxResults(resultsCount).list();
    }
}
