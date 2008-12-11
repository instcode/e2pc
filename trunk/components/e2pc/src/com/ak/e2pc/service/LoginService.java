package com.ak.e2pc.service;

import org.springframework.security.annotation.Secured;

public class LoginService {

   @Secured({"ROLE_ADMIN"})
   public String getAdminData() {
      return "Login.getAdminData()";
   }
    
   @Secured({"ROLE_USER"})
   public String getUserData() {
      return "Login.getUserData()";        
   }
}
