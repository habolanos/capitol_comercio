package es.capitol.comercio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.capitol.comercio.domain.Prices;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
public interface PricesRepository extends JpaRepository<Prices, Integer> {

	
	/**
	 * Ejemplo base para el Caso de Uso
	 *
	 * SELECT * FROM PRICES P 
	 * WHERE
	 * P.PRODUCT_ID = 35445 
	 * AND P.BRAND_ID = 1 
	 * AND TO_TIMESTAMP('2020-06-15 08:00:00', 'YYYY-MM-DD HH24:MI:SS') BETWEEN P.START_DATE AND P.END_DATE ;
	 *
	 */
	@Query("select p from Prices p where p.products = ?1 and p.brands = ?2 and ?3 between p.startDate and p.endDate")
	List<Prices> findByUseCase(Date fechaAplicacion, Long productId, Long brandId);
	
	@Query(value = "SELECT * FROM PRICES P WHERE P.PRODUCT_ID = ?2 AND P.BRAND_ID = ?3 AND TO_TIMESTAMP(?1, 'YYYY-MM-DD HH24:MI:SS') BETWEEN P.START_DATE AND P.END_DATE", nativeQuery = true)
	List<Prices> findByUseCase(String fechaAplicacion, Long productId, Long brandId);
}
