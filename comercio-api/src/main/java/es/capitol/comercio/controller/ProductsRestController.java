package es.capitol.comercio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.capitol.comercio.domain.Products;
import es.capitol.comercio.dto.ProductsDTO;
import es.capitol.comercio.mapper.ProductsMapper;
import es.capitol.comercio.service.ProductsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
@Slf4j
public class ProductsRestController {
	@Autowired
	private ProductsService productsService;
	@Autowired
	private ProductsMapper productsMapper;

	@GetMapping(value = "/{productId}")
	public ResponseEntity<?> findById(@PathVariable("productId") Long productId) throws Exception {
		log.debug("Request to findById() Products");

		Products products = (productsService.findById(productId).isPresent() == true)
				? productsService.findById(productId).get()
				: null;

		return ResponseEntity.ok().body(productsMapper.productsToProductsDTO(products));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Products");

		return ResponseEntity.ok().body(productsMapper.listProductsToListProductsDTO(productsService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ProductsDTO productsDTO) throws Exception {
		log.debug("Request to save Products: {}", productsDTO);

		Products products = productsMapper.productsDTOToProducts(productsDTO);
		products = productsService.save(products);

		return ResponseEntity.ok().body(productsMapper.productsToProductsDTO(products));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ProductsDTO productsDTO) throws Exception {
		log.debug("Request to update Products: {}", productsDTO);

		Products products = productsMapper.productsDTOToProducts(productsDTO);
		products = productsService.update(products);

		return ResponseEntity.ok().body(productsMapper.productsToProductsDTO(products));
	}

	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<?> delete(@PathVariable("productId") Long productId) throws Exception {
		log.debug("Request to delete Products");

		productsService.deleteById(productId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(productsService.count());
	}
}
