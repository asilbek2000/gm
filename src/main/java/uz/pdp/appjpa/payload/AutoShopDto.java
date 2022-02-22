package uz.pdp.appjpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoShopDto {
    private String name;
    private Integer factoryId;
    private List<Integer> carIds;
    private String city;
    private String district;
    private String street;
    private Integer homeNumber;

}
