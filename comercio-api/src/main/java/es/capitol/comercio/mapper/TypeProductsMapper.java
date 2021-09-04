package es.capitol.comercio.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import es.capitol.comercio.domain.TypeProducts;
import es.capitol.comercio.dto.TypeProductsDTO;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 *         Mapper Build with MapStruct https://mapstruct.org MapStruct is a code
 *         generator that greatly simplifies the implementation of mappings
 *         between Java bean type based on a convention over configuration
 *         approach.
 */
@Mapper
public interface TypeProductsMapper {
	public TypeProductsDTO typeProductsToTypeProductsDTO(TypeProducts typeProducts);

	public TypeProducts typeProductsDTOToTypeProducts(TypeProductsDTO typeProductsDTO);

	public List<TypeProductsDTO> listTypeProductsToListTypeProductsDTO(List<TypeProducts> typeProductss);

	public List<TypeProducts> listTypeProductsDTOToListTypeProducts(List<TypeProductsDTO> typeProductsDTOs);
}
