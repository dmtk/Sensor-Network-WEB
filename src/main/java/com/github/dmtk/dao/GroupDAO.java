package com.github.dmtk.dao;


import com.github.dmtk.entity.Group;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Query;

public class GroupDAO extends GenericDAO<Group> {

    public GroupDAO() {
        super();
    }

    public List<Group> findPage(int pageNumber, int paginationStep) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findAllOrderedByName");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.list();
    }

    /**
     *
     * @param name - name
     * @return Group
     * @throws NoResultException - if group with this name doesn't exist
     */
    public Group findByName(String name) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findByName");
        query.setParameter("name", name);
        return (Group) query.uniqueResult();
    }

    /**
     *
     * @param partOfName - part of group name or all name
     * @param pageNumber
     * @param paginationStep
     * @return List of Group which name contains partOfName string
     * @throws NoResultException - if group with this name doesn't exist
     */
    public List<Group> searchByName(String partOfName, int pageNumber, int paginationStep) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("searchByName");
        query.setParameter("name", "%" + partOfName + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.list();
    }

    public int countByNamePart(String partOfName) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(g) FROM Group g WHERE g.name like :name");
        query.setParameter("name", "%" + partOfName + "%");
        return ((Long) query.uniqueResult()).intValue();
    }
}
