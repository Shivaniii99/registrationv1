package com.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDto {
    String id;
    String name;
    String identityProof;
    int age;
    String gender;
}
