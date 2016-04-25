package com.github.dmtk.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class GenericDAO<T> {

    protected Class<T> entityClass;

    
    @Autowired
    protected SessionFactory sessionFactory;

    public void save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> getList() {
        return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName()).list();
    }

    public void remove(Long id) {
        T sensor = (T) sessionFactory.getCurrentSession().load(entityClass, id);
        if (null != sensor) {
            sessionFactory.getCurrentSession().delete(sensor);
        }
    }

    public T retrive(Long id) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("from "+ entityClass.getSimpleName()+" where id = :id");
        q.setLong("id", id);
        return (T) q.uniqueResult();
    }

      

    public List<T> getPage(int pageNumber, int pageSize) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }
        Query query = sessionFactory.getCurrentSession().createQuery("From " + entityClass.getSimpleName());
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<T>) query.list();
    }
}
