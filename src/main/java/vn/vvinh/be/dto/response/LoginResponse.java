package vn.vvinh.be.dto.response;


import lombok.Getter;
import lombok.Setter;
import vn.vvinh.be.enums.Role;

@Getter
@Setter

public class LoginResponse {
     long id;
     String username;
     String fullname;
     String token;
     String avatar;
     Role role;
     boolean isDeleted;
     boolean isVerify;


}
