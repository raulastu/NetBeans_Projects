
package jsp2.examples.simpletag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyOwnSimpleTag extends SimpleTagSupport{
 
    public MyOwnSimpleTag() {
    }
    
    public void doTag() throws JspException, IOException{
        getJspContext().getOut().write("This is my own simple Tag, and i'm proud of it");
    }
    
}
