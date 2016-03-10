package com.github.dmtk.dao;

import com.github.dmtk.entity.SiteUser;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserDAOLocal {

    void create(SiteUser user);

    void edit(SiteUser user);

    void remove(SiteUser user);

    SiteUser find(Object id);

    List<SiteUser> findAll();

    List<SiteUser> findRange(int[] range);

    int count();
    
}
