<!--
  Copyright 2004 The Apache Software Foundation

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
  <head>
    <title>JSP 2.0 Examples - Panels using Tag Files</title>
  </head>
  <body>
    <h1>JSP 2.0 Examples - Panels using Tag Files</h1>
    <hr>
    <p>This JSP page invokes a custom tag that draws a 
    panel around the contents of the tag body.  Normally, such a tag 
    implementation would require a Java class with many println() statements,
    outputting HTML.  Instead, we can use a .tag file as a template,
    and we don't need to write a single line of Java or even a TLD!</p>
    <hr>
    <table border="0">
      <tr valign="top">
        <td>
          <tags:panel color="#ff8080" bgcolor="#ffc0c0"  title="Panel 1" subtitle="Life">
	    First panel.<br/>
	  </tags:panel>
        </td>
        <td>
          <tags:panel color="#80ff80" bgcolor="#c0ffc0" title="Panel 2" subtitle="is">
	    Second panel.<br/>
	    Second panel.<br/>
	    Second panel.<br/>
	    Second panel.<br/>
	  </tags:panel>
        </td>
        <td>
          <tags:panel color="#8080ff" bgcolor="#c0c0ff" title="Panel 3" subtitle="Worth">
	    Third panel.<br/>
            <tags:panel color="#ff80ff" bgcolor="#ffc0ff" title="Inner" subtitle="living">
	      A panel in a panel.
	    </tags:panel>
	    Third panel.<br/>
	  </tags:panel>
        </td>
        <td>
            <tags:panel color="#8000ff" bgcolor="#c0c0ff" title="Panel 4" subtitle="with">
                Fourth Panel.<br/>
                <tags:panel color="#ff8000" bgcolor="#ffc0ff" title="Inner" subtitle="Passion">
                    A panel in a panel.<br/>
                    <tags:panel color="#ff8000" bgcolor="#ffc0ff" title="InnerInner" subtitle="!">
                        A panel in a panel of another panel.
                    </tags:panel>
                </tags:panel>
                Fourth Panel.
            </tags:panel>
        </td>
      </tr>
    </table>
  </body>
</html>
