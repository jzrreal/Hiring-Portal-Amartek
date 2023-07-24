package com.hiringportal.service.auth;

import com.hiringportal.dto.LoginRequest;
import com.hiringportal.dto.RegisterRequest;
import com.hiringportal.dto.ResendVerificationRequest;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.Role;
import com.hiringportal.entities.Token;
import com.hiringportal.entities.User;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.RoleRepository;
import com.hiringportal.repository.TokenRepository;
import com.hiringportal.repository.UserRepository;
import com.hiringportal.service.ValidationService;
import com.hiringportal.service.email.EmailService;
import com.hiringportal.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ValidationService validationService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CandidateProfileRepository candidateProfileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Override
    public String register(RegisterRequest request) {
        validationService.validate(request);

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already used");
        }

        if (candidateProfileRepository.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "phone number already used");

        }

        //Find role
        Role role = roleRepository.findById(4).orElse(null);

        //Save user
        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .gender(request.getGender())
                .build();
        userRepository.save(user);

        CandidateProfile candidateProfile = CandidateProfile.builder()
                .user(user)
                .verify(false)
                .token(UUID.randomUUID().toString())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .build();
        candidateProfileRepository.save(candidateProfile);

        //Todo : Send email with token in user
        emailService.sendEmailVerification(user.getFullName(), user.getEmail(), candidateProfile.getToken());

        return "Success register, check email for verify your account";
    }

    @Override
    public String login(LoginRequest request) {
        validationService.validate(request);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "email or password wrong");
        }

        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();

        if (!user.getCandidateProfile().getVerify()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "please verify your account");
        }

        String token = jwtService.generateToken(user);
        revokeAllUsersTokens(user);
        saveUserToken(user, token);
        return token;
    }

    @Override
    public String verifyEmail(String token) {
        CandidateProfile candidateProfile = candidateProfileRepository.findCandidateProfileByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));

        if (candidateProfile.getVerify()) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email already verify");

        candidateProfile.setVerify(true);
        candidateProfileRepository.save(candidateProfile);

        return "Success verify email";
    }

    @Override
    public String resendVerification(ResendVerificationRequest request) {
        validationService.validate(request);

        CandidateProfile candidateProfile = candidateProfileRepository.findCandidateProfileByUser_Email(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));

        if (candidateProfile.getVerify()) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email already verify");

        candidateProfile.setToken(UUID.randomUUID().toString());
        candidateProfileRepository.save(candidateProfile);

        emailService.sendEmailVerification(candidateProfile.getUser().getFullName(), request.getEmail(), candidateProfile.getToken());

        return "Success resend verify email, check email for verify your account";
    }

    private void revokeAllUsersTokens(User user){
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()){
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevokeToken(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .token(jwtToken)
                .revokeToken(false)
                .expired(false)
                .user(user)
                .build();
        tokenRepository.save(token);
    }
}
