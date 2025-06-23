// package org.lessons.java.pizzeria_web_api;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class DatabaseUserDetailService {

//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//         Optional<User> user = userRepository.findByUsername(username);
//         if (user.isEmpty()) {
//             throw new UsernameNotFoundException("There are no users available with username: " + username);
//         }

//         return new DatabaseUserDetails(userAttempt.get());
    
//        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
//     }
// }
