/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dmtk.web.client.sampleservice;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author nbuser
 */
public interface GWTServiceAsync {
    public void myMethod(AsyncCallback<String> callback);
}
