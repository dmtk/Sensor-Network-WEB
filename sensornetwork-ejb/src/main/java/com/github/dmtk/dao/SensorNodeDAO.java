package com.github.dmtk.dao;

import com.github.dmtk.entity.SensorNode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SensorNodeDAO extends GenericDAO<SensorNode> implements SensorNodeDAOLocal {
    @PersistenceContext(unitName = "sensornetwork-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SensorNodeDAO() {
        super(SensorNode.class);
    }
    
}
