/*
 * NameHandler.java
 *
 * Created on October 28, 2006, 9:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.me.hello;

import java.io.Serializable;

/**
 *
 * @author sang
 */
public class NameHandler implements Serializable{
    
    private String name;
    
    /** Creates a new instance of NameHandler */
    public NameHandler() {
        setName("");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {        
        if(name.equals("")){
            this.name = name;
        }
        else
            this.name= this.name + " " +name;
    }    
}
