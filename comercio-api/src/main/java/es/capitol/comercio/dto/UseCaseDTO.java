package es.capitol.comercio.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
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
public class UseCaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String applicationDate;
	@NotNull
	private Long productId;
	@NotNull
	private Long brandId;
	
}
