package com.api.controller;

import com.api.payload.CreateUserRequestDto;
import com.api.payload.CreateUserResponseDto;
import com.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    @Autowired //we injected the dependency of userService class using @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/" )
    public ResponseEntity<?> createUser(
            @Valid @RequestBody CreateUserRequestDto createUserRequestDto,
            BindingResult result){   // class and its object name
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED); //returnstringvalue
        }
        CreateUserResponseDto userDto = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


}
