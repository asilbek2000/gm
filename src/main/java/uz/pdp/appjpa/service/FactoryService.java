package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.Factory;
import uz.pdp.appjpa.payload.FactoryDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.FactoryRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class FactoryService {
    @Autowired
    FactoryRepository factoryRepository;
    @Autowired
    AddressRepository addressRepository;
    public ApiResponse getAll(){
        List<Factory> all = factoryRepository.findAll();
        return new ApiResponse("Factory List",true,all);
    }
    public ApiResponse addFactory(FactoryDto dto){
        if (!factoryRepository.existsByName(dto.getName())) {
            Address address=new Address();
            address.setHomeNumber(dto.getHomeNumber());
            address.setStreet(dto.getStreet());
            address.setDistrict(dto.getDistrict());
            address.setCity(dto.getCity());
            Address savedAddress = addressRepository.save(address);
            Factory factory=new Factory();
            factory.setAddress(savedAddress);
            factory.setName(dto.getName());
            factory.setOwner(dto.getOwner());
            Factory save = factoryRepository.save(factory);
            return new ApiResponse("Added successfully",true,save);
        }
        return new ApiResponse("factory already exist",true);
    }

    public ApiResponse editFactory(Integer id,FactoryDto dto){
        if (factoryRepository.existsById(id)) {
            Optional<Factory> byId = factoryRepository.findById(id);
            Factory factory = byId.get();
            Address address = factory.getAddress();
            address.setCity(dto.getCity());
            address.setStreet(dto.getStreet());
            address.setDistrict(dto.getDistrict());
            address.setHomeNumber(dto.getHomeNumber());
            Address savedAddress = addressRepository.save(address);
            factory.setAddress(savedAddress);
            factory.setName(dto.getName());
            factory.setOwner(dto.getOwner());
            Factory save = factoryRepository.save(factory);
            return new ApiResponse("found and updated",true,save);
        }
        return new ApiResponse("factory does not exist",false);
    }
   public ApiResponse delete(Integer id){
       if (factoryRepository.existsById(id)) {
           factoryRepository.deleteById(id);
           return new ApiResponse("Found and deleted",true);
       }
       return new ApiResponse("factory does not exist",false);
   }

}
