package es.capitol.comercio.service;

import java.util.Date;
import java.util.List;

import es.capitol.comercio.domain.Prices;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
public interface PricesService extends GenericService<Prices, Integer> {
	List<Prices> findByUseCase(String fechaAplicacion, Long productId, Long brandId);
}
