package es.capitol.comercio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capitol.comercio.domain.Prices;
import es.capitol.comercio.dto.PricesDTO;

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
public interface PricesMapper {
	@Mapping(source = "brands.brandId", target = "brandId_Brands")
	@Mapping(source = "pricesList.priceListId", target = "priceListId_PricesList")
	@Mapping(source = "products.productId", target = "productId_Products")
	public PricesDTO pricesToPricesDTO(Prices prices);

	@Mapping(source = "brandId_Brands", target = "brands.brandId")
	@Mapping(source = "priceListId_PricesList", target = "pricesList.priceListId")
	@Mapping(source = "productId_Products", target = "products.productId")
	public Prices pricesDTOToPrices(PricesDTO pricesDTO);

	public List<PricesDTO> listPricesToListPricesDTO(List<Prices> pricess);

	public List<Prices> listPricesDTOToListPrices(List<PricesDTO> pricesDTOs);
}
