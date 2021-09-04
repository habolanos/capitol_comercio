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

import es.capitol.comercio.domain.Prices;
import es.capitol.comercio.dto.PricesDTO;
import es.capitol.comercio.mapper.PricesMapper;
import es.capitol.comercio.service.PricesService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@RestController
@RequestMapping("/api/v1/prices")
@CrossOrigin(origins = "*")
@Slf4j
public class PricesRestController {
	@Autowired
	private PricesService pricesService;
	@Autowired
	private PricesMapper pricesMapper;

	@GetMapping(value = "/{pricesId}")
	public ResponseEntity<?> findById(@PathVariable("pricesId") Integer pricesId) throws Exception {
		log.debug("Request to findById() Prices");

		Prices prices = (pricesService.findById(pricesId).isPresent() == true) ? pricesService.findById(pricesId).get()
				: null;

		return ResponseEntity.ok().body(pricesMapper.pricesToPricesDTO(prices));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Prices");

		return ResponseEntity.ok().body(pricesMapper.listPricesToListPricesDTO(pricesService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody PricesDTO pricesDTO) throws Exception {
		log.debug("Request to save Prices: {}", pricesDTO);

		Prices prices = pricesMapper.pricesDTOToPrices(pricesDTO);
		prices = pricesService.save(prices);

		return ResponseEntity.ok().body(pricesMapper.pricesToPricesDTO(prices));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody PricesDTO pricesDTO) throws Exception {
		log.debug("Request to update Prices: {}", pricesDTO);

		Prices prices = pricesMapper.pricesDTOToPrices(pricesDTO);
		prices = pricesService.update(prices);

		return ResponseEntity.ok().body(pricesMapper.pricesToPricesDTO(prices));
	}

	@DeleteMapping(value = "/{pricesId}")
	public ResponseEntity<?> delete(@PathVariable("pricesId") Integer pricesId) throws Exception {
		log.debug("Request to delete Prices");

		pricesService.deleteById(pricesId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(pricesService.count());
	}
}
