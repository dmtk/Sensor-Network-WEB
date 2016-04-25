package com.github.dmtk.dao;

import com.github.dmtk.entity.Role;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends GenericDAO<Role>{

   
    
    public RoleDAO() {
        super();
    }

    /**
     *
     * @param name
     * @return Role
     * @throws NoResultException - if role with this name doesn't exist
     */
    public Role findByRolename(String name) {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("findByRolename");
            query.setParameter("rolename", name);
            return (Role) query.uniqueResult();
    }
}
