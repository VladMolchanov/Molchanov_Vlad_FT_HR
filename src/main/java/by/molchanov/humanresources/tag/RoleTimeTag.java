package by.molchanov.humanresources.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Class {@link RoleTimeTag} used for display user role in system and current time.
 *
 * @author MolcanovVladislav
 * @see TagSupport
 */
public class RoleTimeTag extends TagSupport {
    private static final String DEFAULT_ROLE = "guest";
    private String role;

    public void setRole(String role) {
        this.role = DEFAULT_ROLE;
        if (!role.isEmpty()) {
            this.role = role;
        }
    }

    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<hr/>Time : <b> " + gc.getTime() + " </b><hr/>";
        String locale = "Role : <b> " + role + " </b><hr/> ";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time + locale);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
