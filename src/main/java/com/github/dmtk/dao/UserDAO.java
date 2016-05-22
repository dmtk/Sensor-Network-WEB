package com.github.dmtk.dao;

import com.github.dmtk.entity.User;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<User>{

    
    public User getByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findByEmail");
        query.setParameter("email", email.toLowerCase());
        return (User) query.uniqueResult();
    }

    public List<User> getByRolename(String rolename, int pageNumber, int paginationStep) {
        
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.rolename = :rolename ORDER BY u.email");
        query.setParameter("rolename", rolename);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.list();
    }

    public List<User> getByGroup(String groupName, int pageNumber, int paginationStep) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u JOIN u.groups g WHERE g.name = :name");
        query.setParameter("name", groupName);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.list();
    }

    public List<User> searchByEmail(String email, int pageNumber, int paginationStep) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.email like :email ORDER BY u.email");
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.list();
    }

    public List<User> searchByEmailAndRolename(String email, String rolename, int pageNumber, int paginationStep) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT u FROM User u JOIN u.roles r WHERE (r.rolename = :rolename) and (u.email like :email) ORDER BY u.email");
        query.setParameter("rolename", rolename);
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.list();
    }

    public int countByRolename(String rolename) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(u) FROM User u JOIN u.roles ur JOIN u.groups g JOIN g.roles gr WHERE (ur.rolename = :userRolename) OR (gr.rolename = :groupRolename)");

        query.setParameter("userRolename", rolename);
        query.setParameter("groupRolename", rolename);
        return ((Long) query.uniqueResult()).intValue();

    }

    public int countByUserRoleName(String rolename) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE (r.rolename = :rolename)");
        query.setParameter("rolename", rolename);
        return ((Long) query.uniqueResult()).intValue();

    }

    public int countByEmailAndRolename(String email, String rolename) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(u) FROM User u JOIN u.roles r WHERE (r.rolename = :rolename) and (u.email like :email)");
        query.setParameter("rolename", rolename);
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        return ((Long) query.uniqueResult()).intValue();
    }

    public int countByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(u) FROM User u WHERE (u.email like :email)");
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        return ((Long) query.uniqueResult()).intValue();
    }
}
