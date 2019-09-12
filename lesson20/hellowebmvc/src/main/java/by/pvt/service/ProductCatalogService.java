package by.pvt.service;

import by.pvt.pojo.ProductCatalogItem;
import by.pvt.repository.ProductCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogService {

    @Autowired
    ProductCatalogRepository productCatalogRepository;

    public List<ProductCatalogItem> getFirstTopTenProducts() {
        return productCatalogRepository.findAll(10);
    }

    public ProductCatalogItem findItem(Long id) {
        return productCatalogRepository.findItemById(id);
    }

    public List<ProductCatalogItem> searchByProductName(String str) {
        return productCatalogRepository.findByProductName(str, 5);
    }
}
