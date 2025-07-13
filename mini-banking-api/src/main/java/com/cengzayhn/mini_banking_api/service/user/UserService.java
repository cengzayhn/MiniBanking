package com.cengzayhn.mini_banking_api.service.user;

import com.cengzayhn.mini_banking_api.dto.request.LoginRequestDTO;
import com.cengzayhn.mini_banking_api.dto.request.RegisterRequestDTO;
import com.cengzayhn.mini_banking_api.model.User;

public interface UserService {
    User register(RegisterRequestDTO registerRequestDTO);
    String login(LoginRequestDTO loginRequestDTO);
    User getByUsername(String username);
    User findByUsername(String username);
}
