/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */ 

package org.apache.taglibs.standard.examples.taglib;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.jstl.core.*;

import org.apache.taglibs.standard.examples.beans.Customer;

/**
 * <p>Tag handler for &lt;customerFmt&gt;
 *
 * @author Pierre Delisle
 * @version $Revision: 1.1.62.1 $ $Date: 2006/01/18 20:00:36 $
 */
public class CustomerFmtTag extends TagSupport {
    
    //*********************************************************************
    // Instance variables
    
    /** Holds value of property customer. */
    private Customer customer;
    
    /** Holds value of property fmt. */
    private String fmt;
    
    //*********************************************************************
    // Constructors
    
    public CustomerFmtTag() {
        super();
        init();
    }
    
    private void init() {
        customer = null;
        fmt = null;
    }    
    
    //*********************************************************************
    // TagSupport methods
    
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (fmt.equalsIgnoreCase("short")) {
                out.println(customer.getFirstName() + " " +
                customer.getLastName());
            } else if (fmt.equalsIgnoreCase("long")) {
                out.println(customer.getFirstName() + " " +
                customer.getLastName() + " " + customer.getAddress());
            } else {
                out.println("invalid format");
            }
        } catch (IOException ex) {}
        
        return SKIP_BODY;
    }
    
    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }
    
    //*********************************************************************
    // Accessors
    
    /**
     * Getter for property customer.
     * @return Value of property customer.
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Setter for property customer.
     * @param customer New value of property customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Getter for property fmt.
     * @return Value of property fmt.
     */
    public String getFmt() {
        return fmt;
    }
    
    /**
     * Setter for property fmt.
     * @param fmt New value of property fmt.
     */
    public void setFmt(String fmt) {
        this.fmt = fmt;
    }    
}
