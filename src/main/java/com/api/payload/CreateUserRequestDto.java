package com.api.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequestDto {

    @NotEmpty
    @Size(min= 2 ,message = "Min should be 2 letters")
    String name;

    @NotEmpty
    @Size(min= 2 ,message = "Min letters of identityProof should be 5 letters")
    String identityProof;

    @Min(value = 18 ,message = "Min age should be 18")
    int age;

    @NotNull
    String gender;
}
