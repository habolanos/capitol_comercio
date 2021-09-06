package es.capitol.comercio.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


/**
* @author Harold Adrian Bolaños Rodriguez
*         haroldadrian@gmail.com
*
*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TypeProductsServiceTest {
    @Autowired
    private TypeProductsService typeProductsService;

    @Test
    @DisplayName("findAll")
    public void findAll() {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }

    @Test
    @DisplayName("save")
    public void save() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }

    @Test
    @DisplayName("delete")
    public void delete() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }

    @Test
    @DisplayName("deleteById")
    public void deleteById() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }

    @Test
    @DisplayName("update")
    public void update() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }

    @Test
    @DisplayName("findById")
    public void findById() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }

    @Test
    @DisplayName("count")
    public void count() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(typeProductsService);
    }
}
