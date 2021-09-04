package es.capitol.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.capitol.comercio.domain.Products;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
public interface ProductsRepository extends JpaRepository<Products, Long> {
}
