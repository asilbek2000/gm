package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.FactoryDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.AutoShopService;
import uz.pdp.appjpa.service.FactoryService;

@RestController
@RequestMapping("/factory")
public class FactoryController {
    @Autowired
    FactoryService factoryService;
    @Autowired
    AutoShopService autoShopService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse apiResponse = factoryService.getAll();
    return apiResponse;
    }
    @PostMapping
    public ApiResponse add(@RequestBody FactoryDto dto){
        ApiResponse apiResponse = factoryService.addFactory(dto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody FactoryDto dto){
        ApiResponse apiResponse = factoryService.editFactory(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = factoryService.delete(id);
        return apiResponse;
    }
    @GetMapping("/{id}")//Ushbu metod orqali factory Id kiritiladi va shu factoryga tegishli autoshop nomlari haqida ma'lumot olinadi
    public ApiResponse getByFactoryId(@PathVariable Integer id){
        ApiResponse shopNameByFactoryId = autoShopService.getShopNameByFactoryId(id);
        return shopNameByFactoryId;
    }
}
