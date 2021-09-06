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

import es.capitol.comercio.domain.Products;
import es.capitol.comercio.domain.TypeProducts;
import es.capitol.comercio.exception.ZMessManager;
import es.capitol.comercio.repository.TypeProductsRepository;
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
public class TypeProductsServiceImpl implements TypeProductsService {

	@Autowired
	private TypeProductsRepository typeProductsRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(TypeProducts typeProducts) throws ConstraintViolationException {

		Set<ConstraintViolation<TypeProducts>> constraintViolations = validator.validate(typeProducts);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return typeProductsRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TypeProducts> findAll() {
		log.debug("finding all TypeProducts instances");
		return typeProductsRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TypeProducts save(TypeProducts entity) throws Exception {
		log.debug("saving TypeProducts instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TypeProducts");
		}

		validate(entity);

		if (typeProductsRepository.existsById(entity.getTypeId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return typeProductsRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TypeProducts entity) throws Exception {
		log.debug("deleting TypeProducts instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TypeProducts");
		}

		if (entity.getTypeId() == null) {
			throw new ZMessManager().new EmptyFieldException("typeId");
		}

		if (typeProductsRepository.existsById(entity.getTypeId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getTypeId()).ifPresent(entidad -> {
			List<Products> productses = entidad.getProductses();
			if (Utilities.validationsList(productses) == true) {
				throw new ZMessManager().new DeletingException("productses");
			}
		});

		typeProductsRepository.deleteById(entity.getTypeId());
		log.debug("delete TypeProducts successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		log.debug("deleting TypeProducts instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("typeId");
		}
		if (typeProductsRepository.existsById(id)) {
			delete(typeProductsRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TypeProducts update(TypeProducts entity) throws Exception {

		log.debug("updating TypeProducts instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TypeProducts");
		}

		validate(entity);

		if (typeProductsRepository.existsById(entity.getTypeId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return typeProductsRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TypeProducts> findById(String typeId) {
		log.debug("getting TypeProducts instance");
		return typeProductsRepository.findById(typeId);
	}

}
