package com.github.dmtk.entity;

import java.util.List;
import javax.ejb.Local;

@Local
public interface UserFacadeLocal {

    void create(SiteUser user);

    void edit(SiteUser user);

    void remove(SiteUser user);

    SiteUser find(Object id);

    List<SiteUser> findAll();

    List<SiteUser> findRange(int[] range);

    int count();
    
}
