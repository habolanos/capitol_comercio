package es.capitol.comercio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capitol.comercio.domain.Products;
import es.capitol.comercio.dto.ProductsDTO;

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
public interface ProductsMapper {
	@Mapping(source = "typeProducts.typeId", target = "typeId_TypeProducts")
	public ProductsDTO productsToProductsDTO(Products products);

	@Mapping(source = "typeId_TypeProducts", target = "typeProducts.typeId")
	public Products productsDTOToProducts(ProductsDTO productsDTO);

	public List<ProductsDTO> listProductsToListProductsDTO(List<Products> productss);

	public List<Products> listProductsDTOToListProducts(List<ProductsDTO> productsDTOs);
}
