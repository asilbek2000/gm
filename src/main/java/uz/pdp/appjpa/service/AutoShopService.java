package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.AutoShop;
import uz.pdp.appjpa.entity.Car;
import uz.pdp.appjpa.entity.Factory;
import uz.pdp.appjpa.payload.AutoShopDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.AutoShopRepository;
import uz.pdp.appjpa.repository.CarRepository;
import uz.pdp.appjpa.repository.FactoryRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoShopService {
    @Autowired
    AutoShopRepository autoShopRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    FactoryRepository factoryRepository;
    @Autowired
    CarRepository carRepository;
    public ApiResponse getall(){
        List<AutoShop> all = autoShopRepository.findAll();
        return new ApiResponse("Shop List",true,all);
    }
    public ApiResponse addAutoShop(AutoShopDto dto){
        if (factoryRepository.existsById(dto.getFactoryId())) {
            if (autoShopRepository.existsByName(dto.getName())) {
                Optional<Factory> byId = factoryRepository.findById(dto.getFactoryId());
                Factory factory = byId.get();
                Address address=new Address();
                address.setHomeNumber(dto.getHomeNumber());
                address.setDistrict(dto.getDistrict());
                address.setStreet(dto.getStreet());
                address.setCity(dto.getCity());
                Address savedAddress = addressRepository.save(address);
                //List<Car> carList=new ArrayList<>();
                List<Integer> carIds = dto.getCarIds();
                List<Car> allById = carRepository.findAllById(carIds);
//                for (Integer carId : carIds) {
//                    if (carRepository.existsById(carId)) {
//                        Optional<Car> byId1 = carRepository.findById(carId);
//                        Car car = byId1.get();
//                        carList.add(car);
//                    }
//                }
                AutoShop autoShop=new AutoShop();
                autoShop.setAddress(savedAddress);
                autoShop.setFactory(factory);
                autoShop.setName(dto.getName());
                autoShop.setCarList(allById);
                AutoShop save = autoShopRepository.save(autoShop);
                return new ApiResponse("added successfully",true,save);
            }
            return new ApiResponse("Such autoshop already exist",false);
            }
        return new ApiResponse("Factory not found",false);


    }
    public ApiResponse editAutoShop(Integer id,AutoShopDto dto){
        if (autoShopRepository.existsById(id)) {
            Optional<Factory> byId = factoryRepository.findById(dto.getFactoryId());
            Factory factory = byId.get();
            Optional<AutoShop> byId1 = autoShopRepository.findById(id);
            AutoShop autoShop = byId1.get();
            Address address = autoShop.getAddress();
            address.setCity(dto.getCity());
            address.setDistrict(dto.getDistrict());
            address.setStreet(dto.getStreet());
            address.setHomeNumber(dto.getHomeNumber());
            Address savedAddress = addressRepository.save(address);
            //List<Car> carList=new ArrayList<>();
            List<Integer> carIds = dto.getCarIds();
            List<Car> allById = carRepository.findAllById(carIds);
//
//            for (Integer carId : carIds) {
//                if (carRepository.existsById(carId)) {
//                    Optional<Car> byId2 = carRepository.findById(carId);
//                    Car car = byId2.get();
//                    carList.add(car);
//                }
//            }
              autoShop.setCarList(allById);
            autoShop.setFactory(factory);
            autoShop.setName(dto.getName());
            autoShop.setAddress(savedAddress);
            AutoShop save = autoShopRepository.save(autoShop);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Autoshop not found",false);
    }
    public ApiResponse getShopNameByFactoryId(Integer id){
        List<String> autoShopNameByByFactoryId = autoShopRepository.getAutoShopNameByFactoryId(id);
        return new ApiResponse("List of Autoshop name taken by factory id",true,autoShopNameByByFactoryId);
    }
    public ApiResponse deleteAutoshop(Integer id){
        if (autoShopRepository.existsById(id)) {
            autoShopRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("autoshop does not exist",false);
    }
    public ApiResponse getCarNamesByAutoshopId(Integer id){
        if (autoShopRepository.existsById(id)) {
            Optional<AutoShop> byId = autoShopRepository.findById(id);
            AutoShop autoShop = byId.get();
            List<Car> carList = autoShop.getCarList();
            List<String> carNames=new ArrayList<>();
            for (Car car : carList) {
                carNames.add(car.getModel());
            }
            return new ApiResponse("Car Names List",true,carNames);

        }
           return new ApiResponse("Autoshop Not Found",true);
    }
}
