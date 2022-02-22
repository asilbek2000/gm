package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.AutoShopDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.AutoShopService;

@RestController
@RequestMapping("/autoshop")
public class AutoShopController {
    @Autowired
    AutoShopService autoShopService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse apiResponse = autoShopService.getall();
        return apiResponse;
    }
    @PostMapping
    public ApiResponse add(@RequestBody AutoShopDto dto){
        ApiResponse apiResponse = autoShopService.addAutoShop(dto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody AutoShopDto dto){
        ApiResponse apiResponse = autoShopService.editAutoShop(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = autoShopService.deleteAutoshop(id);
        return apiResponse;
    }
    @GetMapping("/{id}")//Ushbu metod orqali Autoshop Id kiritiladi va ushbu autoshopda bor mashinalar modeli haqida ma'lumot olinadi
    public ApiResponse getCarNamesByAutoshopId(@PathVariable Integer id)
    {
        ApiResponse carNamesByAutoshopId = autoShopService.getCarNamesByAutoshopId(id);
        return carNamesByAutoshopId;
    }
}
