package com.cengzayhn.mini_banking_api.service.user;

import com.cengzayhn.mini_banking_api.common.exception.NotFoundException;
import com.cengzayhn.mini_banking_api.dto.request.LoginRequestDTO;
import com.cengzayhn.mini_banking_api.dto.request.RegisterRequestDTO;
import com.cengzayhn.mini_banking_api.model.User;
import com.cengzayhn.mini_banking_api.repository.UserRepository;
import com.cengzayhn.mini_banking_api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public User register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        User user = getByUsername(loginRequestDTO.getUsername());

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtService.generateToken(user.getUsername());
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
