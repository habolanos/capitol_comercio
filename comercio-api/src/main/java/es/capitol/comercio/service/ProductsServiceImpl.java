package es.capitol.comercio.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.capitol.comercio.domain.Prices;
import es.capitol.comercio.domain.PricesList;
import es.capitol.comercio.domain.Products;
import es.capitol.comercio.exception.ZMessManager;
import es.capitol.comercio.repository.ProductsRepository;
import es.capitol.comercio.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Products products) throws ConstraintViolationException {

		Set<ConstraintViolation<Products>> constraintViolations = validator.validate(products);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return productsRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Products> findAll() {
		log.debug("finding all Products instances");
		return productsRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Products save(Products entity) throws Exception {
		log.debug("saving Products instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Products");
		}

		validate(entity);

		if (productsRepository.existsById(entity.getProductId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return productsRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Products entity) throws Exception {
		log.debug("deleting Products instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Products");
		}

		if (entity.getProductId() == null) {
			throw new ZMessManager().new EmptyFieldException("productId");
		}

		if (productsRepository.existsById(entity.getProductId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getProductId()).ifPresent(entidad -> {
			
			List<Prices> priceses = entidad.getPriceses();
			if (Utilities.validationsList(priceses) == true) {
				throw new ZMessManager().new DeletingException("priceses");
			}
		});

		productsRepository.deleteById(entity.getProductId());
		log.debug("delete Products successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting Products instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("productId");
		}
		if (productsRepository.existsById(id)) {
			delete(productsRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Products update(Products entity) throws Exception {

		log.debug("updating Products instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Products");
		}

		validate(entity);

		if (productsRepository.existsById(entity.getProductId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return productsRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Products> findById(Long productId) {
		log.debug("getting Products instance");
		return productsRepository.findById(productId);
	}

}
