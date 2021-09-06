package es.capitol.comercio.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import es.capitol.comercio.domain.Brands;
import es.capitol.comercio.dto.BrandsDTO;

/**
 *
 *         Mapper Build with MapStruct https://mapstruct.org MapStruct is a code
 *         generator that greatly simplifies the implementation of mappings
 *         between Java bean type based on a convention over configuration
 *         approach.
 */
@Mapper
public interface BrandsMapper {
	public BrandsDTO brandsToBrandsDTO(Brands brands);

	public Brands brandsDTOToBrands(BrandsDTO brandsDTO);

	public List<BrandsDTO> listBrandsToListBrandsDTO(List<Brands> brandss);

	public List<Brands> listBrandsDTOToListBrands(List<BrandsDTO> brandsDTOs);
}
