package uz.pdp.appjpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FactoryDto {
    private String name;
    private String owner;
    private String city;
    private String district;
    private String street;
    private Integer homeNumber;
}
