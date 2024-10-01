package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
//import org.modelMapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    private ModelMapper modelMapper;
    // private KYCRepository kycRepository;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {

        //  private ModelMapper modelMapper;
        this.registrationRepository = registrationRepository;
        //this.kycRepository = kycRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy dto to entity
        Registration registration = mapToEntity(registrationDto);

        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    // Registration registration = new Registration();
    //  registration.setName(registrationDto.getName());
    // registration.setEmail(registrationDto.getEmail());
    //  registration.setMobile(registrationDto.getMobile());
    //  Registration savedEntity = registrationRepository.save(registration);

    // copy entity to dto

    // KYC kyc  = new KYC();
    // kyc.setPanNumber(registrationDto.getPanNumber());
    //  KYC savedKyc = kycRepository.save(kyc);

    // RegistrationDto dto = new RegistrationDto();
    //  dto.setName(savedEntity.getName());
    // dto.setEmail(savedEntity.getEmail());
    //  dto.setMobile(savedEntity.getMobile());
    //  dto.setPanNumber(savedKyc.getPanNumber());
    // return dto;

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);

    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        //  r.setId(id);
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(r);
        // Registration savedEntity1 = savedEntity;
        return savedEntity;

    }

    Registration mapToEntity(RegistrationDto registrationDto) {
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        // Registration registration = new Registration();
        // registration.setName(registrationDto.getName());
        // registration.setEmail(registrationDto.getEmail());
        // registration.setMobile(registrationDto.getMobile());
        return registration;
    }

    //conver entity object to dto object
    RegistrationDto mapToDto(Registration registration) {
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
        // RegistrationDto dto = new RegistrationDto();
        // dto.setName(registration.getName());
        // dto.setEmail(registration.getEmail());

        // dto.setMobile(registration.getMobile());
        return dto;

    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Record not found")
        );
       return  mapToDto(registration);
    }
   // }
}


