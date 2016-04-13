package com.github.dmtk.dao;

import com.github.dmtk.entity.Measurement;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementDAO{

    @Autowired
    private SessionFactory sessionFactory;

    
    public void save(Measurement user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @SuppressWarnings("unchecked")
	public List<Measurement> getList() {
        return sessionFactory.getCurrentSession().createQuery("from Measurement").setMaxResults(1000).list();
    }

    public void remove(Long id) {
    	Measurement country = (Measurement) sessionFactory.getCurrentSession().load(Measurement.class, id);
        if (null != country) {
            sessionFactory.getCurrentSession().delete(country);
        }
    }
    
    public Measurement retrive(Long id){
        Query q = sessionFactory.getCurrentSession().createQuery("from Measurement where id = :id");
        q.setLong("id", id);
        return (Measurement) q.uniqueResult();
    }
}
