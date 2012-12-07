/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myhwpackage;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Web application lifecycle listener.
 * @author Administrado
 */

public class MyOwnEventListener implements HttpSessionAttributeListener {

    public void attributeAdded(HttpSessionBindingEvent arg0) {
        arg0.getSession().getServletContext().log("attributeAdded() method is invoke");
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        arg0.getSession().getServletContext().log("attributeRemoved() method is invoke");
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        arg0.getSession().getServletContext().log("attributeReplaced() method is invoke");
    }
}