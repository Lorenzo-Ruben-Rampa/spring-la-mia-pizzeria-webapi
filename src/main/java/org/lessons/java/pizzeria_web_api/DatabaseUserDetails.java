// public interface DatabaseUserDetails implements UserDetails {
//     private final Integer id;
//     private final String user;
//     private final String password;
//     private final Set<GrantedAuthority> authorities;

//     public DatabaseUserDetails(User user) {
//         this.id = user.getId();
//         this.username = user.getUsername();
//         this.password = user.getPassword();
//         this.authorities = new HashSet<GrantedAuthority>();

//         for (Role role : user.getRoles()) {
//             this.authorities.add(new SimpleGrantedAuthority(UserRole.getName()));
//         }
//     }
//     // @Override
//     // public Collection<? extends GrantedAuthority> getAuthorities() {
//     //         // TODO Auto-generated method stub
//     //         throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
//     // }

//     // @Override
//     // public String getPassword() {
//     //     // TODO Auto-generated method stub
//     //     throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
//     // }

//     // @Override
//     // public String getUsername() {
//     //     // TODO Auto-generated method stub
//     //     throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
//     // }
    
// //Getters e Setters

//     public Integer getId() {
//         return this.id;
//     }

//     public String getUsername() {
//         return this.username;
//     }

//     public String getPassword() {
//         return this.password;
//     }

//     public Set<GrantedAuthority> getAuthorities() {
//         return this.authorities;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;

// }

