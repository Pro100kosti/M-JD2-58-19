package by.pvt.service;

import by.pvt.TestHelloWebMvcConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = TestHelloWebMvcConfiguration.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class ProductCatalogServiceTest {

    @Autowired
    ProductCatalogService productCatalogService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFirstTopTenProducts() {
        assertNotNull(productCatalogService);
        assertNotNull(productCatalogService.getFirstTopTenProducts());
    }

    @Test
    public void findItem() {
    }

    @Test
    public void searchByProductName() {
    }

    @Test
    public void addItem() {
    }
}