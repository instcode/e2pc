package com.rstn.e2pc.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailException;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationTrustResolver;
import org.springframework.security.AuthenticationTrustResolverImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Preparable;
import com.rstn.e2pc.Constants;
import com.rstn.e2pc.model.Address;
import com.rstn.e2pc.model.Role;
import com.rstn.e2pc.model.User;
import com.rstn.e2pc.service.ApplicationContextProvider;
import com.rstn.e2pc.service.RoleManager;
import com.rstn.e2pc.service.UserExistsException;
import com.rstn.e2pc.service.UserManager;
import com.rstn.e2pc.webapp.util.RequestUtil;

/**
 * Action for facilitating User Management feature.
 */
public class UserAction extends BaseAction implements Preparable {
    private static final long serialVersionUID = 6776558938712115191L;
    private List users;
    private User user;
    private String id;


    /**
     * Grab the entity from the database before populating with request parameters
     */
    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.Preparable#prepare()
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (!"".equals(getRequest().getParameter("user.id"))) {
                user = userManager.getUser(getRequest().getParameter("user.id"));
            }
        }
    }

    /**
     * Holder for users to display on list screen
     * @return list of users
     */
    public List getUsers() {
        return users;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Delete the user passed in.
     * @return success
     */
    public String delete() {
    	loginService.deleteUser(user.getUsername());

    	List<String> args = new ArrayList<String>();
        args.add(user.getFullName());
        saveMessage(getText("user.deleted", args));

        return SUCCESS;
    }

    /**
     * Grab the user from the database based on the "id" passed in.
     * @return success if user found
     * @throws IOException can happen when sending a "forbidden" from response.sendError()
     */
    public String edit() throws IOException {
        HttpServletRequest request = getRequest();
        boolean editProfile = (request.getRequestURI().indexOf("editProfile") > -1);

        // if URL is "editProfile" - make sure it's the current user
        if (editProfile) {
            // reject if id passed in or "list" parameter passed in
            // someone that is trying this probably knows the AppFuse code
            // but it's a legitimate bug, so I'll fix it. ;-)
            if ((request.getParameter("id") != null) || (request.getParameter("from") != null)) {
                ServletActionContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
                log.warn("User '" + request.getRemoteUser() + "' is trying to edit user '" 
                         + request.getParameter("id") + "'");

                return null;
            }
        }

        // if a user's id is passed in
        if (id != null) {
            // lookup the user using that id
            user = userManager.getUser(id);
        } else if (editProfile) {
            user = userManager.getUserByUsername(request.getRemoteUser());
        } else {
            user = new User();
            user.addRole(new Role(Constants.USER_ROLE));
        }

        if (user.getUsername() != null) {
            user.setConfirmPassword(user.getPassword());

            // if user logged in with remember me, display a warning that they can't change passwords
            log.debug("checking for remember me login...");

            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
            SecurityContext ctx = SecurityContextHolder.getContext();

            if (ctx != null) {
                Authentication auth = ctx.getAuthentication();

                if (resolver.isRememberMe(auth)) {
                    getSession().setAttribute("cookieLogin", "true");
                    saveMessage(getText("userProfile.cookieLogin"));
                }
            }
        }

        return SUCCESS;
    }

    /**
     * Default: just returns "success"
     * @return "success"
     */
    public String execute() {
        return SUCCESS;
    }

    /**
     * Sends users to "mainMenu" when !from.equals("list"). Sends everyone else to "cancel"
     * @return "mainMenu" or "cancel"
     */
    public String cancel() {
        if (!"list".equals(from)) {
            return "mainMenu";
        }
        return "cancel";
    }

    /**
     * Save user
     * @return success if everything worked, otherwise input
     * @throws IOException when setting "access denied" fails on response
     */
    public String save() throws Exception {
        // only attempt to change roles if user is admin
        // for other users, prepare() method will handle populating
        loginService.createUser(user.getUsername());
		return SUCCESS;
    }

    /**
     * Fetch all users from database and put into local "users" variable for retrieval in the UI.
     * @return "success" if no exceptions thrown
     */
    public String list() {
        users = userManager.getUsers(new User());
        return SUCCESS;
    }
}
