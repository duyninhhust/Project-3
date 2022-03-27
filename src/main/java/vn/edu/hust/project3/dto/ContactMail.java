package vn.edu.hust.project3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMail {
    private String name;
    private String website;
    private String email;
    private String subject;
    private String message;
}
