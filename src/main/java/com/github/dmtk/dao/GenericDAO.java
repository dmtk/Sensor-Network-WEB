package com.github.dmtk.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDAO<T> {

    protected Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public GenericDAO() {

        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

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
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName() + " where id = :id");
        q.setLong("id", id);
        return (T) q.uniqueResult();
    }

    public List<T> getPage(int pageNumber, int pageSize, String sortingParam) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        criteria.addOrder(Order.desc(sortingParam));
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        return (List<T>) criteria.list();

    }
}
