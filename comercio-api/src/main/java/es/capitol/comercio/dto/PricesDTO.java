package es.capitol.comercio.dto;

import java.io.Serializable;
import java.util.Date;

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
public class PricesDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@NotEmpty
	@Size(max = 3)
	private String curr;
	@NotNull
	private Date endDate;
	@NotNull
	private Double price;
	@NotNull
	private Integer pricesId;
	@NotNull
	private Long priority;
	@NotNull
	private Date startDate;
	private Long brandId_Brands;
	private Long priceListId_PricesList;
	private Long productId_Products;
}
