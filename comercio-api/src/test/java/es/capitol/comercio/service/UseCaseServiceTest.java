package es.capitol.comercio.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import es.capitol.comercio.domain.Prices;
import es.capitol.comercio.dto.UseCaseDTO;
import lombok.extern.slf4j.Slf4j;


/**
* @author Harold Adrian Bolaños Rodriguez
*         haroldadrian@gmail.com
*
*/

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class UseCaseServiceTest{
	@Autowired
	private TestRestTemplate testRestTemplate;
	  
	@LocalServerPort
	private Integer port;
	
	@Autowired
    private PricesService pricesService;

/**
*   Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
*   Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
*   Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
*   Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
*   Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
*/    
    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void test1() {

    	log.info("Inicio Test 1");
    	UseCaseDTO peticion = new UseCaseDTO();
    	List<Prices> resultado = new ArrayList<Prices>();
    	
    	peticion.setApplicationDate("2020-06-14 10:00:00");
    	peticion.setProductId(35455L);
    	peticion.setBrandId(1L);
    	
    	log.info(peticion.toString());
    	assertNotNull(pricesService);
    	resultado = pricesService.findAll();
    	//resultado = pricesService.findByUseCase(peticion.getApplicationDate(), peticion.getProductId(), peticion.getBrandId());
    	
    	log.info("Validacion Coleccion");
    	assertFalse(resultado.size()>0);
    	
    	log.info("Validacion para cada item");
    	for (Prices item : resultado) {
			assertNotNull(item);
			log.info(item.toString());
		}
    	log.info("Fin Test 1");
    }
    
    /*
    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void test2() {
    	log.info("Inicio Test 2");
    	UseCaseDTO peticion = new UseCaseDTO();
    	List<Prices> resultado = new ArrayList<Prices>();
    	
    	peticion.setApplicationDate("2020-06-14 16:00:00");
    	peticion.setProductId(35455L);
    	peticion.setBrandId(1L);
    	
    	log.info(peticion.toString());

    	resultado = pricesService.findByUseCase(peticion.getApplicationDate(), peticion.getProductId(), peticion.getBrandId());
    	
    	log.info("Validacion Colecion Retornada");
    	assertTrue(resultado.size()>0);
    	
    	log.info("Validacion para cada item");
    	for (Prices item : resultado) {
			assertNotNull(item);
			log.info(item.toString());
		}
    	log.info("Fin Test 2");
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void test3() {
    	log.info("Inicio Test 3");
    	UseCaseDTO peticion = new UseCaseDTO();
    	List<Prices> resultado = new ArrayList<Prices>();
    	
    	peticion.setApplicationDate("2020-06-14 21:00:00");
    	peticion.setProductId(35455L);
    	peticion.setBrandId(1L);
    	
    	log.info(peticion.toString());

    	resultado = pricesService.findByUseCase(peticion.getApplicationDate(), peticion.getProductId(), peticion.getBrandId());
    	
    	log.info("Validacion Colecion Retornada");
    	assertTrue(resultado.size()>0);
    	
    	log.info("Validacion para cada item");
    	for (Prices item : resultado) {
			assertNotNull(item);
			log.info(item.toString());
		}
    	log.info("Fin Test 3");
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    public void test4() {
    	log.info("Inicio Test 4");
    	UseCaseDTO peticion = new UseCaseDTO();
    	List<Prices> resultado = new ArrayList<Prices>();
    	
    	peticion.setApplicationDate("2020-06-15 21:00:00");
    	peticion.setProductId(35455L);
    	peticion.setBrandId(1L);
    	
    	log.info(peticion.toString());

    	resultado = pricesService.findByUseCase(peticion.getApplicationDate(), peticion.getProductId(), peticion.getBrandId());
    	
    	log.info("Validacion Colecion Retornada");
    	assertTrue(resultado.size()>0);
    	
    	log.info("Validacion para cada item");
    	for (Prices item : resultado) {
			assertNotNull(item);
			log.info(item.toString());
		}
    	log.info("Fin Test 4");
    }
    
    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    public void test5() {
    	log.info("Inicio Test 5");
    	UseCaseDTO peticion = new UseCaseDTO();
    	List<Prices> resultado = new ArrayList<Prices>();
    	
    	peticion.setApplicationDate("2020-06-16 21:00:00");
    	peticion.setProductId(35455L);
    	peticion.setBrandId(1L);
    	
    	log.info(peticion.toString());

    	resultado = pricesService.findByUseCase(peticion.getApplicationDate(), peticion.getProductId(), peticion.getBrandId());
    	
    	log.info("Validacion Colecion Retornada");
    	assertTrue(resultado.size()>0);
    	
    	log.info("Validacion para cada item");
    	for (Prices item : resultado) {
			assertNotNull(item);
			log.info(item.toString());
		}
    	log.info("Fin Test 5");
    }
    */
    
}
