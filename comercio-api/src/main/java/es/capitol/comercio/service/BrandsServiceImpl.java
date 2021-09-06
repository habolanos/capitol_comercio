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

import es.capitol.comercio.domain.Brands;
import es.capitol.comercio.domain.Prices;
import es.capitol.comercio.exception.ZMessManager;
import es.capitol.comercio.repository.BrandsRepository;
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
public class BrandsServiceImpl implements BrandsService {

	@Autowired
	private BrandsRepository brandsRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Brands brands) throws ConstraintViolationException {

		Set<ConstraintViolation<Brands>> constraintViolations = validator.validate(brands);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return brandsRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Brands> findAll() {
		log.debug("finding all Brands instances");
		return brandsRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Brands save(Brands entity) throws Exception {
		log.debug("saving Brands instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Brands");
		}

		validate(entity);

		if (brandsRepository.existsById(entity.getBrandId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return brandsRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Brands entity) throws Exception {
		log.debug("deleting Brands instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Brands");
		}

		if (entity.getBrandId() == null) {
			throw new ZMessManager().new EmptyFieldException("brandId");
		}

		if (brandsRepository.existsById(entity.getBrandId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getBrandId()).ifPresent(entidad -> {
			List<Prices> priceses = entidad.getPriceses();
			if (Utilities.validationsList(priceses) == true) {
				throw new ZMessManager().new DeletingException("priceses");
			}
		});

		brandsRepository.deleteById(entity.getBrandId());
		log.debug("delete Brands successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting Brands instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("brandId");
		}
		if (brandsRepository.existsById(id)) {
			delete(brandsRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Brands update(Brands entity) throws Exception {

		log.debug("updating Brands instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Brands");
		}

		validate(entity);

		if (brandsRepository.existsById(entity.getBrandId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return brandsRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Brands> findById(Long brandId) {
		log.debug("getting Brands instance");
		return brandsRepository.findById(brandId);
	}

}
