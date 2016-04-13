package com.github.dmtk.dao;

import com.github.dmtk.entity.Sensor;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SensorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Sensor node) {
        sessionFactory.getCurrentSession().saveOrUpdate(node);
    }

    @SuppressWarnings("unchecked")
    public List<Sensor> getList() {
        return sessionFactory.getCurrentSession().createQuery("from Sensor").list();
    }

    public void remove(Long id) {
        Sensor sensor = (Sensor) sessionFactory.getCurrentSession().load(Sensor.class, id);
        if (null != sensor) {
            sessionFactory.getCurrentSession().delete(sensor);
        }
    }

    public Sensor retrive(Long id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Sensor where id = :id");
        q.setLong("id", id);
        return (Sensor) q.uniqueResult();
    }
}
