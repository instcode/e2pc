package com.ak.e2pc.service;

import org.springframework.security.annotation.Secured;

public class LoginService {

   @Secured({"ROLE_USER"})
   public String getUserData() {
      return "";        
   }
}
