package vn.edu.hust.project3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, length = 300)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "old_price", columnDefinition = "double default 0.0")
    private double oldPrice;
    @Column(name = "quantity", nullable = false)
    private long quantity;
    @Column(name = "introduction", length = 1500)
    private String introduction;
    @Column(name = "image", length = Integer.MAX_VALUE)
    private String image;
    @Column(name = "screen")
    private String screen;
    @Column(name = "operating_system")
    private String operatingSystem;
    @Column(name = "rear_camera")
    private String rearCamera;
    @Column(name = "front_camera")
    private String frontCamera;
    @Column(name = "chip")
    private String chip;
    @Column(name = "ram")
    private String ram;
    @Column(name = "rom")
    private String rom;
    @Column(name = "sim")
    private String sim;
    @Column(name = "pin_charger")
    private String pinCharger;
    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDate createdDate;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Transient
    public String getImagePath(){
        if (image == null ) return null;
        return "/phone-images/" + id + "/" + image;
    }

}
