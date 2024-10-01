package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    // public ResponseEntity<List<Registration> > getRegistration() {
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        // return registrations;
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @PostMapping("/")
//    @RequestMapping(method = RequestMethod.POST, value = "/" )
    public ResponseEntity<?> createRegistration(
          @Valid  @RequestBody RegistrationDto registrationDto,
        BindingResult result
    ){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED); //returnstringvalue
        }
        RegistrationDto  regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id1}")
    public ResponseEntity<String> deleteRegistration(
            @PathVariable long id1){
        registrationService.deleteRegistration(id1);
        //HttpStatusCode body;
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable  long id,
            @RequestBody Registration registration
    ) {
        Registration updateReg = registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(updateReg, HttpStatus.OK);
    }
    @GetMapping("/{id}") //fetch record based on id number
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @PathVariable long id
    ){
       RegistrationDto dto = registrationService.getRegistrationById(id);
      // public RegistrationDto
              // getRegistrationById(long id){
           return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
