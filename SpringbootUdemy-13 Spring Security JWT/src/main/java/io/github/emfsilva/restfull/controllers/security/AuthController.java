package io.github.emfsilva.restfull.controllers.security;

import io.github.emfsilva.restfull.repository.security.UserRepository;
import io.github.emfsilva.restfull.security.AccountCredentialsDTO;
import io.github.emfsilva.restfull.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

   private AuthenticationManager authenticationManager;
   private JwtTokenProvider tokenProvider;
   private UserRepository repository;

   @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository repository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.repository = repository;
    }

    @ApiOperation(value = "Authenticate a user by credentials")
    @PostMapping(value = "/signin", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
       try {
           var username = data.getUsername();
           var password = data.getUsername();

           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

           var user = repository.findByUsername(username);

           var token = "";

           if(user != null) {
               token = tokenProvider.createToken(username, user.getRoles());
           }else {
               throw  new UsernameNotFoundException("Username " + username + " not found");
           }

           Map<Object, Object> model = new HashMap<>();
           model.put("username", username);
           model.put("token", token);
            return  ok(model);
       }catch (Exception e) {
           throw new BadCredentialsException("Invalid username/password suplied");
       }
    }
}
