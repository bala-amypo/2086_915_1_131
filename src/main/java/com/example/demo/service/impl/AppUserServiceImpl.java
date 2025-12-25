@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AppUserServiceImpl(AppUserRepository userRepo,
                              PasswordEncoder passwordEncoder,
                              JwtTokenProvider jwtTokenProvider) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AppUser register(String email, String password, String role) {

        if (userRepo.findByEmail(email).isPresent()) {
            throw new BadRequestException("unique");
        }

        AppUser user = AppUser.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .active(true)
                .build();

        return userRepo.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {

        AppUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtTokenProvider.createToken(user);

        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
