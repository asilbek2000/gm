package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.Factory;

public interface FactoryRepository  extends JpaRepository<Factory,Integer> {
    boolean existsByName(String name);
}
