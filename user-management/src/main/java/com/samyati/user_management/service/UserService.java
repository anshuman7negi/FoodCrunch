package com.samyati.user_management.service;

import java.security.SecureRandom;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;
import com.samyati.user_management.config.JwtUtil;
import com.samyati.user_management.dto.LoginDto;
import com.samyati.user_management.dto.ResponseDto;
import com.samyati.user_management.entity.User;
import com.samyati.user_management.exceptions.ApplicationException;
import com.samyati.user_management.exceptions.ErrorCode;
import com.samyati.user_management.repository.UserRepo;
import com.samyati.user_management.util.ValidationUtil;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SmtpMailSender smtpMailSender;

    // ------------------------------user register code ------------------

    public ResponseDto getRegisterUser(LoginDto entity) {

        String name = entity.getUsername();
        String email = entity.getEmail();
        String password = entity.getPassword();
        Long number = entity.getPhoneNumber();
        ResponseDto response = new ResponseDto();

        Optional<User> existingUser = checkByUserNameOREmail(name, email);

        if (existingUser.isPresent()) {
            throw new ApplicationException(ErrorCode.USER_EXISTS);
        }

        try {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setPhoneNumber(number);

            userRepo.save(newUser);
            response.setStatus("Succesfull");

        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.GENERIC_ERROR);
        }

        return response;

    }

    // ------------------------------login code ------------------

    public ResponseDto getLogin(LoginDto loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        ResponseDto response = new ResponseDto();
        try {
            authenticateUser(username, password);
            response.setToken(JwtUtil.generateToken(loginRequest.getUsername()));
            response.setStatus("Succesfull");
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.GENERIC_ERROR);
        }

        return response;
    }

    // ------------------------change password-------------

    public ResponseDto getChangePassword(LoginDto loginRequest) {
        String username = loginRequest.getUsername();
        String email = loginRequest.getEmail();
        String oldPassword = loginRequest.getOldPassword();
        String password = loginRequest.getPassword();

        ResponseDto response = new ResponseDto();

        ValidationUtil.validatePasswordChange(oldPassword, password);
        Optional<User> opUser = checkByUserNameOREmail(username, email);

        if (!opUser.isPresent()) {
            throw new ApplicationException(ErrorCode.USER_NOT_EXISTS);
        }

        User user = opUser.get();

        try {
            authenticateUser(username, oldPassword);
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
            response.setStatus("Password Changed Succesfully");

        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.GENERIC_ERROR);
        }
        return response;

    }

    //---------------------------Forget Password------------------------

    public ResponseDto getForgetPassword(LoginDto loginDto) {

        Optional<User> existingUser = checkByUserNameOREmail(loginDto.getUsername(), loginDto.getEmail());
        if(!existingUser.isPresent()) {
            throw new ApplicationException(ErrorCode.USER_NOT_EXISTS);
        }

        User user = existingUser.get();
        ResponseDto response = new ResponseDto();
        String otp = generateOtp();
        smtpMailSender.sendOtp(user.getEmail(), otp);
        user.setOtp(otp);
        userRepo.save(user);
        response.setStatus("Otp send succesfully");
        return response;
        
    }

    // --------------------------method to check By USERNAME OR EMAIL
    private Optional<User> checkByUserNameOREmail(String username, String email) {
        return userRepo.findByNameOREmail(username, email);
    }

    // -----------------------------authenticate user--------------------
    private Authentication authenticateUser(String username, String password) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
        }

    }

    //--------------------------------Generate OTP---------------------------
    private String generateOtp(){
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
