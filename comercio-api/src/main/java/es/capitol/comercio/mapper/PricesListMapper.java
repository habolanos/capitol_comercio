package es.capitol.comercio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capitol.comercio.domain.PricesList;
import es.capitol.comercio.dto.PricesListDTO;

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
public interface PricesListMapper {
	@Mapping(source = "products.productId", target = "productId_Products")
	public PricesListDTO pricesListToPricesListDTO(PricesList pricesList);

	@Mapping(source = "productId_Products", target = "products.productId")
	public PricesList pricesListDTOToPricesList(PricesListDTO pricesListDTO);

	public List<PricesListDTO> listPricesListToListPricesListDTO(List<PricesList> pricesLists);

	public List<PricesList> listPricesListDTOToListPricesList(List<PricesListDTO> pricesListDTOs);
}
