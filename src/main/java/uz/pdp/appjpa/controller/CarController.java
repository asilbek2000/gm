package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.CarDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.CarService;

import javax.smartcardio.Card;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse apiResponse = carService.getAll();
        return apiResponse;
    }
    @PostMapping
    public ApiResponse addCar(@RequestBody CarDto dto){
        ApiResponse apiResponse = carService.addCar(dto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody CarDto dto){
        ApiResponse apiResponse = carService.editCar(id,dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = carService.deleteCar(id);
        return apiResponse;
    }
}
