package com.api.service;


import com.api.entity.User;
import com.api.payload.CreateUserRequestDto;
import com.api.payload.CreateUserResponseDto;
import com.api.payload.RegistrationDto;
import com.api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = new User();
        user.setAge(createUserRequestDto.getAge()); // getting the data from createUserDto and setting it in entity user
        user.setName(createUserRequestDto.getName());
        user.setIdentityProof(createUserRequestDto.getIdentityProof());
        user.setGender(createUserRequestDto.getGender());
        userRepository.save(user);
        return prepareCreateUserResponse(user);
    }

    private CreateUserResponseDto prepareCreateUserResponse(User user) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, CreateUserResponseDto.class);
    }


}
