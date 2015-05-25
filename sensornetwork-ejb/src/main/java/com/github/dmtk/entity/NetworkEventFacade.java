package com.github.dmtk.entity;

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
    
   public List<NetworkEvent> findById(Integer id) {
        TypedQuery<NetworkEvent> query = em.createNamedQuery("NetworkEvent.findById", NetworkEvent.class);
        query.setParameter("id",id);
        return query.getResultList();
    }
}
