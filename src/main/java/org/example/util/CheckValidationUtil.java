package org.example.util;

import org.example.dto.ProfileDto;
import org.example.exception.ItemNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CheckValidationUtil {

    public boolean checkPhoneNumber(String phone) {
        if(phone.length()!=13 || !phone.startsWith("+998")){
            return false;
        }
        return true;
    }

    public Integer isId(String data) {
        for (int i=0; i<data.length(); i++){
            char c=data.charAt(i);
            if(!Character.isDigit(c)){
                return null;
            }
        }
       return Integer.valueOf(data);
    }

    public void checkProfile(ProfileDto profileDto) {
    if(profileDto.getName()==null || profileDto.getName().isBlank()){
        throw new ItemNotFoundException("Enter name!");
    } else if (profileDto.getSurname()==null||profileDto.getSurname().isBlank()) {
        throw new ItemNotFoundException("Enter surname!");
    } else if (profileDto.getPhone()==null || profileDto.getPhone().isBlank()) {
        throw  new ItemNotFoundException("Enter phone number!");
    } else if (profileDto.getLogin()==null || profileDto.getLogin().isBlank()) {
        throw new ItemNotFoundException("Enter login!");
    } else if (profileDto.getPassword()==null || profileDto.getPassword().isBlank()) {
        throw new ItemNotFoundException("Enter password");
    }
    }
}
