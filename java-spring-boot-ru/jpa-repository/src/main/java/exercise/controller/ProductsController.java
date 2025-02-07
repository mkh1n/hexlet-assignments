package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> indexByPrice(@RequestParam(value = "min", required = false) Optional<Integer> min, @RequestParam(value = "max", required = false) Optional<Integer> max) {
        if (!max.isPresent() && !min.isPresent()) {
            return productRepository.findAll();
        }
        else if(!min.isPresent()){
            return productRepository.findByPriceLessThanOrderByPrice(max.get());

        } else if (!max.isPresent()) {
            return productRepository.findByPriceGreaterThanOrderByPrice(min.get());
        }
        return productRepository.findByPriceBetweenOrderByPrice(min.get(), max.get());
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
