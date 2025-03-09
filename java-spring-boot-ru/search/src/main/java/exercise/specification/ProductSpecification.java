package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification{
    public Specification<Product> build(ProductParamsDTO params) {
        return titleCont(params.getTitleCont())
                .and(categoryId(params.getCategoryId()))
                .and(priceLt(params.getPriceLt()))
                .and(priceGt(params.getPriceGt()))
                .and(ratingGt(params.getRatingGt()));
    }
    private Specification<Product> titleCont(String title){
        return ((root, query, cb)
                -> title == null ? cb.conjunction() : cb.like(root.get("title"), "%"+title+"%"));
    }
    private Specification<Product> categoryId(Long categoryId){
        return ((root, query, cb)
                -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId));
    }
    private Specification<Product> priceLt(Integer price){
        return ((root, query, cb)
                -> price == null ? cb.conjunction() : cb.lessThan(root.get("price"), price));
    }

    private Specification<Product> priceGt(Integer price){
        return ((root, query, cb)
                -> price == null ? cb.conjunction() : cb.greaterThan(root.get("price"), price));
    }

    private Specification<Product> ratingGt(Double rating){
        return ((root, query, cb)
                -> rating == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), rating));
    }

}
// END
