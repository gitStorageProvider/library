package com.kuriata.tags;

import com.kuriata.entities.Author;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AuthorsTag extends SimpleTagSupport {
    private List<Author> authorsList;
    private boolean isAdmin;

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAuthorsList(List<Author> authorsList) {
        this.authorsList = authorsList;
    }

    @Override
    public void doTag() throws JspException, IOException {
        Iterator<Author> iterator = authorsList.iterator();
        while (iterator.hasNext()) {
            List<String> colList = new LinkedList<>();
            Author one = (Author) iterator.next();
            colList.add(one.getFullName());
            colList.add(one.getDetails());
            //if current user isAdmin (isAdmin == true), then put in result additional options
            if (isAdmin) {
                colList.add("<a href=\"?command=deleteAuthor&authorId=" + one.getId() + "\">Delete</a>");
                colList.add("<a href=\"?command=editAuthor&authorId=" + one.getId() + "\">Edit</a>");
            }
            getJspContext().setAttribute("colList", colList);
            getJspBody().invoke(null);
        }
    }
}
