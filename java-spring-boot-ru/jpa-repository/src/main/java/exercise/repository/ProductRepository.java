package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.PrimitiveIterator;

import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetweenOrderByPrice(Integer min, Integer max);
    List<Product> findByPriceGreaterThanOrderByPrice(Integer min);
    List<Product> findByPriceLessThanOrderByPrice(Integer max);

    // END
}
