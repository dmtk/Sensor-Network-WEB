package com.github.dmtk.dao;

import com.github.dmtk.entity.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends GenericDAO<Role>{

    public RoleDAO() {
        super();
    }

    public Role findByRolename(String name) {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("findByRolename");
            query.setParameter("rolename", name);
            return (Role) query.uniqueResult();
    }
}
