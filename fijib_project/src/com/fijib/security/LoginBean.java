package com.fijib.security;

 import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name="loginMgmtBean")
@Scope("session")
public class LoginBean {
  
    private String userName = null; 
    private String password = null;
     
    @ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;
    
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   
    public String login() {
    	 
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
           
             if(req.isUserInRole("ROLE_ADMIN"))
            	return "/securePages/AdminPages/accueilAdmin";
            else if (req.isUserInRole("ROLE_CLIENT")) {
            	return "/securePages/ClientPages/accueilClient";				
			}else if(req.isUserInRole("ROLE_AGENT"))
            	return "/securePages/AgentPages/accueilAgent";

        } catch (Exception e) {
            e.printStackTrace();
            return "incorrect";
        }
        return "/";
    }
 

    public String cancel() {
        return null;
    }

    public String logout(){
        SecurityContextHolder.clearContext();
        return "/insecurePages/accueil";
    }
 
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
}