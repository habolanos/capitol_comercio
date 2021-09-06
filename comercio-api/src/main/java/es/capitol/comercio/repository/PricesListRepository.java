package es.capitol.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.capitol.comercio.domain.PricesList;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
public interface PricesListRepository extends JpaRepository<PricesList, Long> {
}
