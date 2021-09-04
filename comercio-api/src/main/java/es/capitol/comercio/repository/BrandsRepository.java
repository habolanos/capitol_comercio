package es.capitol.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.capitol.comercio.domain.Brands;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
public interface BrandsRepository extends JpaRepository<Brands, Long> {
}
