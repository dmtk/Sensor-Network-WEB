/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dmtk.web.client.sampleservice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author nbuser
 */
@RemoteServiceRelativePath("sampleservice/gwtservice")
public interface GWTService extends RemoteService {
    public String myMethod();
}
