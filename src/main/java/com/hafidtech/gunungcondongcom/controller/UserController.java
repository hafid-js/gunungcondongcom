package com.hafidtech.gunungcondongcom.controller;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Transactional
public class UserController {

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserServiceImpl userServiceImpl;
//    @PostMapping("/user")
//    public ResponseEntity<User> register(  @RequestParam("firstName") String firstName,
//                                           @RequestParam("lastName") String lastName,
//                                           @RequestParam("email") String email,
//                                           @RequestParam("password") String password,
//                                           @RequestParam("image") MultipartFile image, final HttpServletRequest request) throws Exception {
//
//        Optional<User> existingUser = userRepository.findByEmail(email);
//        if (existingUser.isPresent()) {
//            return new ResponseEntity<User>("User with the email address '%s' already exists.", HttpStatus.CONFLICT);
//        }
//
//        User user = userServiceImpl.register(
//                firstName,
//                lastName,
//                email,
//                password,
//                image);
//
//        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
//        return "Success! Please, Check your email for complete your registration";
//    }
}
