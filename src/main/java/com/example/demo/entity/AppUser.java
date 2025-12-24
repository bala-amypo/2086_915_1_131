package com.example.demo.entity;

public class AppUser {
    private Long id;
    private String email;
    private String role;

    public static AppUserBuilder builder() {
        return new AppUserBuilder();
    }

    public static class AppUserBuilder {
        private AppUser user = new AppUser();

        public AppUserBuilder id(Long id) {
            user.id = id;
            return this;
        }

        public AppUserBuilder email(String email) {
            user.email = email;
            return this;
        }

        public AppUserBuilder role(String role) {
            user.role = role;
            return this;
        }

        public AppUser build() {
            return user;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}