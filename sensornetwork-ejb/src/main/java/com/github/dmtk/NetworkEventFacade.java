package com.github.dmtk;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@EJB(name="networkEventFacade", beanInterface=NetworkEventFacade.class)
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
    
}
