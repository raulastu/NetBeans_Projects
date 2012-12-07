/*
 * NameHandler.java
 *
 * Created on October 28, 2006, 9:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.me.hello;

/**
 *
 * @author sang
 */
public class NameHandler {

    private String username;
    String x; //This is my test code abreviation

    /** Creates a new instance of NameHandler */
    public NameHandler() {
        setName(null);
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public int addNumbers(int x, int y) {
        return (x + y);
    }
    public int comparteTwoNamesIgnoringCases(String name1, String name2){
        return name1.compareToIgnoreCase(name2);
    }
}
