package com.github.dmtk.entity;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class NetworkEventFacade extends AbstractFacade<NetworkEvent> implements NetworkEventFacadeLocal {

    @PersistenceContext(unitName = "sensornetwork-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NetworkEventFacade() {
        super(NetworkEvent.class);
    }

    @Override
    public List<NetworkEvent> findById(Integer id, Integer count) {
        TypedQuery<NetworkEvent> query = em.createNamedQuery("NetworkEvent.findById", NetworkEvent.class);
        query.setParameter("id", id);
        return query.setMaxResults(count).getResultList();
    }

    @Override
    public List<NetworkEvent> findByDate(Integer count) {
        TypedQuery<NetworkEvent> query = em.createNamedQuery("NetworkEvent.findByDate", NetworkEvent.class);
        return query.setMaxResults(count).getResultList();
    }

    @Override
    public Double getAvg(Integer id) {
        TypedQuery query = em.createNamedQuery("NetworkEvent.getAvg", NetworkEvent.class);
        query.setParameter("id", id);
        query.setParameter("start", new Date("2016-01-19 01:20:00"));
        query.setParameter("end", new Date("2016-01-19 01:21:00"));
        return (Double) query.getSingleResult();

    }
}
