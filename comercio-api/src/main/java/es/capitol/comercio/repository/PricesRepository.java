package es.capitol.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.capitol.comercio.domain.Prices;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
public interface PricesRepository extends JpaRepository<Prices, Integer> {
}
