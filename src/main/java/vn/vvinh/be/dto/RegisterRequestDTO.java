package vn.vvinh.be.dto;

import lombok.Getter;
import lombok.Setter;
import vn.vvinh.be.enums.Gender;
import vn.vvinh.be.enums.Role;

@Getter
@Setter
public class RegisterRequestDTO {
    String avatar;
    String userName;
    String password;
    String fullName;
    String phoneNumber;
    String email;
    Gender gender;
    Role role;

}
