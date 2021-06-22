package com.socgen.stockmarketcharting.controller;

import com.socgen.stockmarketcharting.model.UserEntity;
import com.socgen.stockmarketcharting.payload.request.LoginRequest;
import com.socgen.stockmarketcharting.payload.request.SignupRequest;
import com.socgen.stockmarketcharting.payload.response.JwtResponse;
import com.socgen.stockmarketcharting.payload.response.MessageResponse;
import com.socgen.stockmarketcharting.repository.UserRepository;
import com.socgen.stockmarketcharting.security.jwt.JwtUtils;
import com.socgen.stockmarketcharting.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Properties;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().toString();
        if(!userRepository.findByUsername(loginRequest.getUsername()).get().getIsVerified())
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getMobileNumber(),
                roles));
    }

    @PostMapping("/signup/")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) throws MessagingException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        System.out.println("we are in");
        UserEntity newUserEntity = new UserEntity(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getRole(),
                signUpRequest.getEmail(),
                signUpRequest.getMobileNumber(),
                true
                );
        System.out.println(signUpRequest);
        System.out.println("User");
        System.out.println(newUserEntity);

        UserEntity savedUserEntity = userRepository.save(newUserEntity);
//        sendEmail(savedUser.getId());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    public void sendEmail(long id) throws AddressException, MessagingException{
        final String username = "youremail@gmail.com";
        final String password ="yourpassword";
        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {
            Optional<UserEntity> user = userRepository.findById(id);
            if(!user.isPresent())
                throw new UsernameNotFoundException("not found");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(user.get().getEmail())
            );
            message.setSubject("User confirmation email");

            message.setContent(
                    "<h1><a href =\"https://secret-tundra-65063.herokuapp.com/api/auth/confirmuser/"+id+"/\"> Click to confirm </a></h1>",
                    "text/html");
            Transport.send(message);

            System.out.println("sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value="/confirmuser/{userid}", method=RequestMethod.GET)
    public String welcomepage(@PathVariable Long userid) {
        Optional<UserEntity> userList =   userRepository.findById(userid);
        if(!userList.isPresent())
            return "User doesn't exist";
        UserEntity userEntity = userList.get();
        userEntity.setIsVerified(true);
        userRepository.save(userEntity);
        return "User confirmed" + userEntity.getUsername();
    }

    @DeleteMapping("/deletebyusername")
    public ResponseEntity<UserEntity> delete(@RequestParam String username){
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isPresent()){
            userRepository.delete(userEntity.get());
            return ResponseEntity.ok(userEntity.get());
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not found");
    }




}
