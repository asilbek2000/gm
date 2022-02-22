package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Car;
import uz.pdp.appjpa.payload.CarDto;
import uz.pdp.appjpa.repository.CarRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    public ApiResponse getAll(){
        List<Car> all = carRepository.findAll();
        return new ApiResponse("Car List",true,all);
    }
    public ApiResponse addCar(CarDto dto){
        if (!carRepository.existsByModel(dto.getModel())) {
            Car car=new Car();
            car.setModel(dto.getModel());
            car.setPrice(dto.getPrice());
            car.setYear(dto.getYear());
            Car save = carRepository.save(car);
            return new ApiResponse("Car added successfully",true,save);
        }
        return new ApiResponse("Car with this model already exist",false);

    }

    public ApiResponse editCar(Integer id,CarDto dto) {
        if (carRepository.existsById(id)) {
            Optional<Car> byId = carRepository.findById(id);
            Car car = byId.get();
            car.setYear(dto.getYear());
            car.setModel(dto.getModel());
            car.setPrice(dto.getPrice());
            Car save = carRepository.save(car);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Car does not exist",false);
    }
    public ApiResponse deleteCar(Integer id){
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Car does not exist",false);
    }

}
