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

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.taglibs.standard.examples.util.*;

/**
 * <p>Tag handler for &lt;escapeHtml&gt;
 *
 * @author Pierre Delisle
 * @version $Revision: 1.1.62.1 $ $Date: 2006/01/18 20:00:36 $
 */
public class EscapeHtmlTag extends BodyTagSupport {
    
    //*********************************************************************
    // Instance variables
    
    private Object reader;
    private Object writer;
    
    //*********************************************************************
    // Constructors
    
    public EscapeHtmlTag() {
        super();
        init();
    }
    
    private void init() {
        reader = null;
        writer = null;
    }
    
    
    //*********************************************************************
    // Tag's properties
    
    /**
     * Tag's 'reader' attribute
     */
    public void setReader(Object reader) {
        this.reader = reader;
    }
    
    /**
     * Tag's 'writer' attribute
     */
    public void setWriter(Object writer) {
        this.writer = writer;
    }
    
    //*********************************************************************
    // TagSupport methods
    
    public int doEndTag() throws JspException {
        Reader in;
        Writer out;
        
        if (reader == null) {
            String bcs = getBodyContent().getString().trim();
            if (bcs == null || bcs.equals("")) {
                throw new JspTagException("In &lt;escapeHtml&gt;, 'reader' " +
                "not specified and no non-whitespace content inside the tag.");
            }
            in = Util.castToReader(bcs);
        } else {
            in = Util.castToReader(reader);
        }
        
        if (writer == null) {
            out = pageContext.getOut();
        } else {
            out = Util.castToWriter(writer);
        }
        
        transform(in, out);
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
    
    /**
     * Transform
     */
    public void transform(Reader reader, Writer writer)
    throws JspException {
        int c;
        try {
            writer.write("<pre>");
            while ((c = reader.read()) != -1) {
                if (c == '<') {
                    writer.write("&lt;");
                } else if (c == '>') {
                    writer.write("&gt;");
                } else {
                    writer.write(c);
                }
            }
            writer.write("</pre>");
        } catch (IOException ex) {
            throw new JspException("EscapeHtml: " +
            "error copying chars", ex);
        }
    }
}
