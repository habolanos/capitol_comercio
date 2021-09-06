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

import es.capitol.comercio.domain.PricesList;
import es.capitol.comercio.dto.PricesListDTO;
import es.capitol.comercio.mapper.PricesListMapper;
import es.capitol.comercio.service.PricesListService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@RestController
@RequestMapping("/api/v1/pricesList")
@CrossOrigin(origins = "*")
@Slf4j
public class PricesListRestController {
	@Autowired
	private PricesListService pricesListService;
	@Autowired
	private PricesListMapper pricesListMapper;

	@GetMapping(value = "/{priceListId}")
	public ResponseEntity<?> findById(@PathVariable("priceListId") Long priceListId) throws Exception {
		log.debug("Request to findById() PricesList");

		PricesList pricesList = (pricesListService.findById(priceListId).isPresent() == true)
				? pricesListService.findById(priceListId).get()
				: null;

		return ResponseEntity.ok().body(pricesListMapper.pricesListToPricesListDTO(pricesList));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() PricesList");

		return ResponseEntity.ok()
				.body(pricesListMapper.listPricesListToListPricesListDTO(pricesListService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody PricesListDTO pricesListDTO) throws Exception {
		log.debug("Request to save PricesList: {}", pricesListDTO);

		PricesList pricesList = pricesListMapper.pricesListDTOToPricesList(pricesListDTO);
		pricesList = pricesListService.save(pricesList);

		return ResponseEntity.ok().body(pricesListMapper.pricesListToPricesListDTO(pricesList));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody PricesListDTO pricesListDTO) throws Exception {
		log.debug("Request to update PricesList: {}", pricesListDTO);

		PricesList pricesList = pricesListMapper.pricesListDTOToPricesList(pricesListDTO);
		pricesList = pricesListService.update(pricesList);

		return ResponseEntity.ok().body(pricesListMapper.pricesListToPricesListDTO(pricesList));
	}

	@DeleteMapping(value = "/{priceListId}")
	public ResponseEntity<?> delete(@PathVariable("priceListId") Long priceListId) throws Exception {
		log.debug("Request to delete PricesList");

		pricesListService.deleteById(priceListId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(pricesListService.count());
	}
}
