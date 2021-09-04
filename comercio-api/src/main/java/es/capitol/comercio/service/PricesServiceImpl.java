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
import es.capitol.comercio.exception.ZMessManager;
import es.capitol.comercio.repository.PricesRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@Scope("singleton")
@Service
@Slf4j
public class PricesServiceImpl implements PricesService {
	@Autowired
	private PricesRepository pricesRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(Prices prices) throws ConstraintViolationException {
		Set<ConstraintViolation<Prices>> constraintViolations = validator.validate(prices);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return pricesRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prices> findAll() {
		log.debug("finding all Prices instances");

		return pricesRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Prices save(Prices entity) throws Exception {
		log.debug("saving Prices instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Prices");
		}

		validate(entity);

		if (pricesRepository.existsById(entity.getPricesId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return pricesRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Prices entity) throws Exception {
		log.debug("deleting Prices instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Prices");
		}

		if (entity.getPricesId() == null) {
			throw new ZMessManager().new EmptyFieldException("pricesId");
		}

		if (pricesRepository.existsById(entity.getPricesId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		pricesRepository.deleteById(entity.getPricesId());
		log.debug("delete Prices successful");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Prices instance");

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("pricesId");
		}

		if (pricesRepository.existsById(id)) {
			delete(pricesRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Prices update(Prices entity) throws Exception {
		log.debug("updating Prices instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Prices");
		}

		validate(entity);

		if (pricesRepository.existsById(entity.getPricesId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return pricesRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Prices> findById(Integer pricesId) {
		log.debug("getting Prices instance");

		return pricesRepository.findById(pricesId);
	}
}
