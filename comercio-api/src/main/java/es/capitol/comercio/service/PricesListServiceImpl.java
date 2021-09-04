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
import es.capitol.comercio.exception.ZMessManager;
import es.capitol.comercio.repository.PricesListRepository;
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
public class PricesListServiceImpl implements PricesListService {

	@Autowired
	private PricesListRepository pricesListRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(PricesList pricesList) throws ConstraintViolationException {

		Set<ConstraintViolation<PricesList>> constraintViolations = validator.validate(pricesList);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return pricesListRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PricesList> findAll() {
		log.debug("finding all PricesList instances");
		return pricesListRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PricesList save(PricesList entity) throws Exception {
		log.debug("saving PricesList instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("PricesList");
		}

		validate(entity);

		if (pricesListRepository.existsById(entity.getPriceListId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return pricesListRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(PricesList entity) throws Exception {
		log.debug("deleting PricesList instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("PricesList");
		}

		if (entity.getPriceListId() == null) {
			throw new ZMessManager().new EmptyFieldException("priceListId");
		}

		if (pricesListRepository.existsById(entity.getPriceListId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getPriceListId()).ifPresent(entidad -> {
			List<Prices> priceses = entidad.getPriceses();
			if (Utilities.validationsList(priceses) == true) {
				throw new ZMessManager().new DeletingException("priceses");
			}
		});

		pricesListRepository.deleteById(entity.getPriceListId());
		log.debug("delete PricesList successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting PricesList instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("priceListId");
		}
		if (pricesListRepository.existsById(id)) {
			delete(pricesListRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PricesList update(PricesList entity) throws Exception {

		log.debug("updating PricesList instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("PricesList");
		}

		validate(entity);

		if (pricesListRepository.existsById(entity.getPriceListId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return pricesListRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PricesList> findById(Long priceListId) {
		log.debug("getting PricesList instance");
		return pricesListRepository.findById(priceListId);
	}

}
