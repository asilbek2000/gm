package uz.pdp.appjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
    private String city;
   @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer homeNumber;
}
