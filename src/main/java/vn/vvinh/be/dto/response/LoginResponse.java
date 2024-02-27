package vn.vvinh.be.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponse {
     long id;
     String username;
     String fullname;
     String token;
     String avatar;



}
