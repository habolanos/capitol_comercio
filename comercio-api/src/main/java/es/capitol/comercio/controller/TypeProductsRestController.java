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

import es.capitol.comercio.domain.TypeProducts;
import es.capitol.comercio.dto.TypeProductsDTO;
import es.capitol.comercio.mapper.TypeProductsMapper;
import es.capitol.comercio.service.TypeProductsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@RestController
@RequestMapping("/api/v1/typeProducts")
@CrossOrigin(origins = "*")
@Slf4j
public class TypeProductsRestController {
	@Autowired
	private TypeProductsService typeProductsService;
	@Autowired
	private TypeProductsMapper typeProductsMapper;

	@GetMapping(value = "/{typeId}")
	public ResponseEntity<?> findById(@PathVariable("typeId") String typeId) throws Exception {
		log.debug("Request to findById() TypeProducts");

		TypeProducts typeProducts = (typeProductsService.findById(typeId).isPresent() == true)
				? typeProductsService.findById(typeId).get()
				: null;

		return ResponseEntity.ok().body(typeProductsMapper.typeProductsToTypeProductsDTO(typeProducts));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() TypeProducts");

		return ResponseEntity.ok()
				.body(typeProductsMapper.listTypeProductsToListTypeProductsDTO(typeProductsService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody TypeProductsDTO typeProductsDTO) throws Exception {
		log.debug("Request to save TypeProducts: {}", typeProductsDTO);

		TypeProducts typeProducts = typeProductsMapper.typeProductsDTOToTypeProducts(typeProductsDTO);
		typeProducts = typeProductsService.save(typeProducts);

		return ResponseEntity.ok().body(typeProductsMapper.typeProductsToTypeProductsDTO(typeProducts));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody TypeProductsDTO typeProductsDTO) throws Exception {
		log.debug("Request to update TypeProducts: {}", typeProductsDTO);

		TypeProducts typeProducts = typeProductsMapper.typeProductsDTOToTypeProducts(typeProductsDTO);
		typeProducts = typeProductsService.update(typeProducts);

		return ResponseEntity.ok().body(typeProductsMapper.typeProductsToTypeProductsDTO(typeProducts));
	}

	@DeleteMapping(value = "/{typeId}")
	public ResponseEntity<?> delete(@PathVariable("typeId") String typeId) throws Exception {
		log.debug("Request to delete TypeProducts");

		typeProductsService.deleteById(typeId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(typeProductsService.count());
	}
}
