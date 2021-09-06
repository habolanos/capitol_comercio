package es.capitol.comercio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@NotEmpty
	@Size(max = 30)
	private String name;
	@NotNull
	private Long productId;
	private Long state;
	private String typeId_TypeProducts;
}
