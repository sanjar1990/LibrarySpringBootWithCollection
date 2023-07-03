package org.example.repository;
import org.example.dto.ProfileDto;
import org.example.enums.Role;
import org.example.exception.IsAlreadyExistsException;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProfileRepository {
    private List<ProfileDto> profileDtoList= new LinkedList<>();
    public  List<ProfileDto> getAllStudentList() {
     List<ProfileDto> list=new LinkedList<>();
       profileDtoList.forEach(s->{
           if(s.getRole().equals(Role.STUDENT)){
               list.add(s);
           }
       });
       return list;
    }
    public void checkProfileIsExists(String login, String phone) {
        for (ProfileDto p:profileDtoList){
            if(p.getLogin().equals(login) ){
                throw new IsAlreadyExistsException("This phone number is exists!");
            } else if (p.getPhone().equals(phone)) {
                throw new IsAlreadyExistsException("This login already taken!");
            }
        }
    }

    public void registerProfile(ProfileDto profileDto) {
       profileDtoList.add(profileDto);
    }

    public ProfileDto getByLogin(String login) {
        return profileDtoList.stream().filter(s->s.getLogin().equals(login)).findAny().orElse(null);
    }
    public void updateStatusOfProfile(String id, ProfileDto profileDto) {
      profileDtoList.forEach(s->{
          if(s.getId().equals(id)){
              s.setStatus(profileDto.getStatus());
          }
      });
    }
    public List<ProfileDto> getAllProfile() {
        List<ProfileDto> list=new LinkedList<>();
        for (ProfileDto p: profileDtoList){
            if(p.getRole().equals(Role.STAFF)){
                list.add(p);
            }
        }
        return list;
    }

    public ProfileDto searchProfile(String data) {
        return profileDtoList.stream().filter(s->{
            if(s.getLogin().equals(data) || s.getName().equals(data) || s.getId().equals(data) || s.getSurname().equals(data)){
             return true;
            }
            return false;
        }).findAny().orElse(null);
    }
    public void changeProfileStatus(String  id, ProfileDto profileDto) {
       profileDtoList.forEach(s->{
           if(s.getId().equals(id)){
               s.setStatus(profileDto.getStatus());
           }
       });
    }
}
