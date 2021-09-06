package es.capitol.comercio.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.capitol.comercio.domain.Prices;
import es.capitol.comercio.dto.PricesDTO;
import es.capitol.comercio.dto.UseCaseDTO;
import es.capitol.comercio.mapper.PricesMapper;
import es.capitol.comercio.service.PricesService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@RestController
@RequestMapping("/api/v1/usecase")
@CrossOrigin(origins = "*")
@Slf4j
public class UseCaseRestController {
	@Autowired
	private PricesService pricesService;
	@Autowired
	private PricesMapper pricesMapper;

	//Date fechaAplicacion, Long productId, Long brandId
	@GetMapping(value = "/{fechaAplicacion}/{productId}/{brandId}")
	public ResponseEntity<?> findByUseCaseGET(@PathVariable("fechaAplicacion") Date fechaAplicacion, 
									 @PathVariable("productId") Long productId, 
									 @PathVariable("brandId") Long brandId) {
		log.info("fechaAplicacion = "+fechaAplicacion);
		log.info("productId = "+productId);
		log.info("brandId = "+brandId);
		
		return ResponseEntity.ok().body(pricesMapper.listPricesToListPricesDTO(
										pricesService.findByUseCase(fechaAplicacion.toString(), productId, brandId)));
	}
	
	@PostMapping()
	public ResponseEntity<?> findByUseCase(@Valid @RequestBody UseCaseDTO useCaseDTO) throws Exception {
		log.info("UseCaseDTO = "+useCaseDTO);
		return ResponseEntity.ok().body(pricesMapper.listPricesToListPricesDTO(
										pricesService.findByUseCase(useCaseDTO.getApplicationDate(), 
																	useCaseDTO.getProductId(), 
																	useCaseDTO.getBrandId())));		
	}
}
