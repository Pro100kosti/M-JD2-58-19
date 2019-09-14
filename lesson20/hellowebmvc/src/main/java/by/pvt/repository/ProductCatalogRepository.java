package by.pvt.repository;

import by.pvt.pojo.ProductCatalogItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductCatalogRepository {

    private static List<ProductCatalogItem> catalog = new ArrayList<>();

    static {
        for (int i = 1; i < 100; i++) {
            catalog.add(new ProductCatalogItem((long) i, "Product Item Name" + i, Math.random() * 1000));
        }
    }


    public List<ProductCatalogItem> findAll(int count) {
        return catalog.subList(0, count);
    }

    public ProductCatalogItem findItemById(Long id) {
        return catalog.stream()
                .filter(productCatalogItem -> productCatalogItem.getId().equals(id))
                .findFirst().orElseThrow();
    }

    public List<ProductCatalogItem> findByProductName(String str, int i) {
        return catalog.stream()
                .filter(productCatalogItem -> productCatalogItem.getItemName().contains(str))
                .limit(i)
                .collect(Collectors.toList());
    }

    public boolean add(ProductCatalogItem item) {
        return catalog.add(item);
    }

    public Long getMaxId() {
        return catalog.stream()
                .max((item1, item2) -> (int) (item1.getId() - item2.getId()))
                .orElseThrow()
                .getId();
    }
}
