package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.Car;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsByModel(String model);
}
