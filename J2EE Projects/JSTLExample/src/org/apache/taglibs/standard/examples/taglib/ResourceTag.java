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

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.taglibs.standard.examples.util.*;

/**
 * <p>Tag handler for &lt;resource&gt;
 *
 * @author Pierre Delisle
 * @version $Revision: 1.1.62.1 $ $Date: 2006/01/18 20:00:39 $
 */
public class ResourceTag extends TagSupport {
    
    //*********************************************************************
    // Instance variables
    
    private String id;
    private String resource;
    
    private Reader reader;
    
    //*********************************************************************
    // Constructors
    
    public ResourceTag() {
        super();
        init();
    }
    
    private void init() {
        id = null;
        resource = null;
    }
    
    //*********************************************************************
    // Tag's properties
    
    /**
     * Tag's 'id' attribute
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Tag's 'resource' attribute
     */
    public void setResource(String resource) {
        this.resource = resource;
    }
    
    //*********************************************************************
    // TagSupport methods
    
    public int doStartTag() throws JspException {
        reader = getReaderFromResource(resource);
        exposeVariable(reader);
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() throws JspException {
        try {
            reader.close();
        } catch (IOException ex) {}
        reader = null;
        return EVAL_PAGE;
    }
    
    /**
     * Releases any resources we may have (or inherit)
     */
    public void release() {
        super.release();
        init();
    }
    
    //*********************************************************************
    // Tag's scific behavior methods
    
    private Reader getReaderFromResource(String name) throws JspException {
        HttpServletRequest request =
        (HttpServletRequest)pageContext.getRequest();
        HttpServletResponse response =
        (HttpServletResponse)pageContext.getResponse();
        RequestDispatcher rd = null;
        
        // The response of the local URL becomes the reader that
        // we export. Need temporary storage.
        IOBean ioBean = new IOBean();
        Writer writer = ioBean.getWriter();
        ServletResponseWrapper responseWrapper =
        new ServletResponseWrapperForWriter(
        response, new PrintWriter(writer));
        rd = pageContext.getServletContext().getRequestDispatcher(name);
        try {
            rd.include(request, responseWrapper);
            return ioBean.getReader();
        } catch (Exception ex) {
            throw new JspException(ex);
        }
    }
    
    //*********************************************************************
    // Utility methods
        
    private void exposeVariable(Reader reader) {
        if (id != null) {
            pageContext.setAttribute(id, reader);
        }
    }
}
