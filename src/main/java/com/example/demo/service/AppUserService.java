public interface AppUserService {

    AppUser register(String email, String password, String role);

    AuthResponse login(String email, String password);
}
