package vn.edu.hust.project3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    @Column(name = "PhoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "Address")
    private String address;
    @Column(name = "Status", nullable = false)
    private int status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Bill(String name, String phoneNumber, String address, int status, User user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.user = user;
    }
}
