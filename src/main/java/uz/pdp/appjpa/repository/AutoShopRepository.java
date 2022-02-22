package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.AutoShop;

import java.util.List;

public interface AutoShopRepository extends JpaRepository<AutoShop,Integer> {
    boolean existsByName(String name);
    @Query( value = "select auto_shop.name from auto_shop where auto_shop.factory_id=?1",nativeQuery = true)
    List<String> getAutoShopNameByFactoryId(Integer id);
}
