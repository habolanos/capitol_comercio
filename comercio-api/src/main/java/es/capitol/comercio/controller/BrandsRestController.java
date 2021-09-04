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

import es.capitol.comercio.domain.Brands;
import es.capitol.comercio.dto.BrandsDTO;
import es.capitol.comercio.mapper.BrandsMapper;
import es.capitol.comercio.service.BrandsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@RestController
@RequestMapping("/api/v1/brands")
@CrossOrigin(origins = "*")
@Slf4j
public class BrandsRestController {
	@Autowired
	private BrandsService brandsService;
	@Autowired
	private BrandsMapper brandsMapper;

	@GetMapping(value = "/{brandId}")
	public ResponseEntity<?> findById(@PathVariable("brandId") Long brandId) throws Exception {
		log.debug("Request to findById() Brands");

		Brands brands = (brandsService.findById(brandId).isPresent() == true) ? brandsService.findById(brandId).get()
				: null;

		return ResponseEntity.ok().body(brandsMapper.brandsToBrandsDTO(brands));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Brands");

		return ResponseEntity.ok().body(brandsMapper.listBrandsToListBrandsDTO(brandsService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody BrandsDTO brandsDTO) throws Exception {
		log.debug("Request to save Brands: {}", brandsDTO);

		Brands brands = brandsMapper.brandsDTOToBrands(brandsDTO);
		brands = brandsService.save(brands);

		return ResponseEntity.ok().body(brandsMapper.brandsToBrandsDTO(brands));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody BrandsDTO brandsDTO) throws Exception {
		log.debug("Request to update Brands: {}", brandsDTO);

		Brands brands = brandsMapper.brandsDTOToBrands(brandsDTO);
		brands = brandsService.update(brands);

		return ResponseEntity.ok().body(brandsMapper.brandsToBrandsDTO(brands));
	}

	@DeleteMapping(value = "/{brandId}")
	public ResponseEntity<?> delete(@PathVariable("brandId") Long brandId) throws Exception {
		log.debug("Request to delete Brands");

		brandsService.deleteById(brandId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(brandsService.count());
	}
}
