package com.github.dmtk.dao;

import com.github.dmtk.entity.SiteUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserDAO extends GenericDAO<SiteUser> implements UserDAOLocal {
    @PersistenceContext(unitName = "sensornetwork-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDAO() {
        super(SiteUser.class);
    }
    
    public SiteUser findByEmail(String email) {
        TypedQuery<SiteUser> query = em.createNamedQuery("SiteUser.findByEmail", SiteUser.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
    public SiteUser findByPassword(String password) {
        TypedQuery<SiteUser> query = em.createNamedQuery("SiteUser.findByPassword", SiteUser.class);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    public SiteUser findByUsername(String username) {
        TypedQuery<SiteUser> query = em.createNamedQuery("SiteUser.findByUsername", SiteUser.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
