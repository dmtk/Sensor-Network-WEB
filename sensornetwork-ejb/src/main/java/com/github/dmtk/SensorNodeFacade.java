package com.github.dmtk;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SensorNodeFacade extends AbstractFacade<SensorNode> implements SensorNodeFacadeLocal {
    @PersistenceContext(unitName = "sensornetwork-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SensorNodeFacade() {
        super(SensorNode.class);
    }
    
}
