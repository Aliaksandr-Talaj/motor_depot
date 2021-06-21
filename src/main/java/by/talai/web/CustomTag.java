package by.talai.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            String role = (String) pageContext.getSession().getAttribute("role");
            if (role != null) {
                out.print("_<u>"+role+"</u>_");}
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}
