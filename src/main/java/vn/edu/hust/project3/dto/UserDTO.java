package vn.edu.hust.project3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private String matchingPassword;
    private String firstName;
    private String lastName;
}
