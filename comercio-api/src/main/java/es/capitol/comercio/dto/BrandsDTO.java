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
public class BrandsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Long brandId;
	@NotNull
	@NotEmpty
	@Size(max = 30)
	private String name;
	private Long state;
}
