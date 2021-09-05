package es.capitol.comercio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capitol.comercio.domain.PricesList;
import es.capitol.comercio.dto.PricesListDTO;

/**
 *
 *         Mapper Build with MapStruct https://mapstruct.org MapStruct is a code
 *         generator that greatly simplifies the implementation of mappings
 *         between Java bean type based on a convention over configuration
 *         approach.
 */
@Mapper
public interface PricesListMapper {
	public PricesListDTO pricesListToPricesListDTO(PricesList pricesList);

	public PricesList pricesListDTOToPricesList(PricesListDTO pricesListDTO);

	public List<PricesListDTO> listPricesListToListPricesListDTO(List<PricesList> pricesLists);

	public List<PricesList> listPricesListDTOToListPricesList(List<PricesListDTO> pricesListDTOs);
}
