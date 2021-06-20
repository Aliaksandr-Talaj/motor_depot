package by.talai.web;

import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.personnel.User;
import by.talai.service.UserService;
import by.talai.service.impl.UserServiceImpl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomTag extends TagSupport {

    private User user;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            UserService userService = new UserServiceImpl();
            out.print(LocalDateTime.now());
        } catch (IOException | ConnectionPoolException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
