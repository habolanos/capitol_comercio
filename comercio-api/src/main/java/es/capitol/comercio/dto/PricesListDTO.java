package es.capitol.comercio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
public class PricesListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Long priceListId;
	private Long state;
	private Long productId_Products;
}
