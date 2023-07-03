package org.example.service;
import org.example.container.ComponentContainer;
import org.example.dto.ProfileDto;
import org.example.dto.TakenBookDto;
import org.example.enums.ProfileStatus;
import org.example.enums.Role;
import org.example.exception.*;
import org.example.repository.ProfileRepository;
import org.example.repository.TakenBookRepository;
import org.example.util.CheckValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class ProfileService {
@Autowired
private CheckValidationUtil checkValidationUtil;
@Autowired
private ProfileRepository profileRepository;
@Autowired
private TakenBookRepository takenBookRepository;

    public ProfileDto registration(ProfileDto profileDto) {
        checkValidationUtil.checkProfile(profileDto);
        if(!checkValidationUtil.checkPhoneNumber(profileDto.getPhone())){
            System.out.println("Enter valid phone Number");
            throw new EnterValidPhoneNumberException("Enter valid phone Number");
        }

        profileRepository.checkProfileIsExists(profileDto.getLogin(),profileDto.getPhone());

        System.out.println("You have registered successfully!");
        profileDto.setId(UUID.randomUUID().toString());
        profileDto.setRole(Role.STUDENT);
        profileDto.setStatus(ProfileStatus.ACTIVE);
        profileDto.setCreatedDate(LocalDateTime.now());
        profileRepository.registerProfile(profileDto);
        return profileDto;
    }

    public String login(String login, String password) {
        ProfileDto profile=profileRepository.getByLogin(login);
        if (profile==null){
            throw new ProfileNotFoundException("Profile not exist first Register your account!");
        }
        else if (!profile.getPassword().equals(password)){
          throw new PasswordIncorrectException("Enter valid password");
        }
        ComponentContainer.profileDto=profile;

        return "You have logged in";
    }

    public List<ProfileDto> getAllStudent() {
        List<ProfileDto> list=profileRepository.getAllStudentList();
        if(list.isEmpty()){
            throw new ItemNotFoundException("No student was found!");
        }else {
          return list;
        }
    }

    public ProfileDto searchStudent(String data) {
            ProfileDto student=profileRepository.searchProfile(data);
            if(student==null) throw new ItemNotFoundException("Profile not found");
            return student;
    }

    public String blockStudent(String id, ProfileDto profileDto) {
        ProfileDto student=profileRepository.searchProfile(id);
        if(student==null){
           throw new ItemNotFoundException("Student not found");
        }else if(student.getStatus().equals(ProfileStatus.BLOCK)){
            throw new IsAlreadyExistsException("This profile is already blocked!");
        }

        profileRepository.updateStatusOfProfile(id,profileDto);
        return "Profile status is changed!";
    }

    public List<TakenBookDto> getStudentBookInfo(String id) {
        ProfileDto profile=profileRepository.searchProfile(id);
        if(profile==null){
           throw new ItemNotFoundException("Profile is not found!");
        }
    List<TakenBookDto>list=takenBookRepository.getStudentBookInfo(id);
        if(list.isEmpty()){
           throw new ItemNotFoundException("No info was found!");
        }
       return list;

    }

    public List<ProfileDto> getAllProfile() {
        List<ProfileDto> list=profileRepository.getAllProfile();
        if(list.isEmpty()){
            throw new ItemNotFoundException("No Profile is found!");
        }
        else {
            return list;
        }
    }

    public ProfileDto searchProfile(String data) {
        ProfileDto profileDto=profileRepository.searchProfile(data);
        if(profileDto==null){
          throw new ItemNotFoundException("Profile is not found!");
        }
        return profileDto;
    }
    public ProfileDto changeStatus(String id, ProfileDto profileDto) {
        ProfileDto profile=profileRepository.searchProfile(id);
        if(profile==null){
          throw new ItemNotFoundException("Profile is not found!");
        }
        profileRepository.changeProfileStatus(id,profileDto);
       return profileDto;
    }
    public ProfileDto staffRegistration(ProfileDto profileDto) {
        if(!checkValidationUtil.checkPhoneNumber(profileDto.getPhone())){
         throw new EnterValidPhoneNumberException("Enter valid phone Number");
        }
        profileRepository.checkProfileIsExists(profileDto.getLogin(),profileDto.getPhone());
        profileDto.setCreatedDate(LocalDateTime.now());
        profileDto.setRole(Role.STAFF);
        profileDto.setStatus(ProfileStatus.ACTIVE);
        profileRepository.registerProfile(profileDto);
        return profileDto;
    }
}
