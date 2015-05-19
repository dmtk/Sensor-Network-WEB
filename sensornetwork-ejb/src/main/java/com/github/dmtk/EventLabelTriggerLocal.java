/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dmtk;

import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface EventLabelTriggerLocal {

    public void change(NetworkEvent n);
    
}
