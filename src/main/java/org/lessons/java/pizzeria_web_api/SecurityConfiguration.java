// public class SecurityConfiguration {
//     @Bean
//     @SuppressWarnings("removal")
//     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests()
//             .requestMatchers("/books/create", "/books/edit/**").hasAuthority("ADMIN")     
//             .requestMatchers(HttpMethod.POST, "/books/**").hasAuthority("ADMIN") 
//             .requestMatchers("/categories", "/categories**").hasAuthority("ADMIN")
//             .requestMatchers("/books", "/books/**").hasAuthority("USER", "ADMIN")
//             .requestMatchers("/**").permitAll()
//             .and().formLogin()
//             .and().logout()
//             .and()exceptionHandling();

//         return http.build();
//     }

//     @SuppressWarnings("deprecation")
//     DaoAuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//         authProvider.setUserDetailsService(null);
//         authProvider.setPasswordEncoder(passwordEncoder());
//         return authProvider;
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
//     }

// }