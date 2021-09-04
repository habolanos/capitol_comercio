package es.capitol.comercio.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Entity
@Table(name = "products", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	@NotNull
	private Long productId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	@NotNull
	private TypeProducts typeProducts;

	@NotNull
	@NotEmpty
	@Size(max = 30)
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "state")
	private Long state;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
	private List<PricesList> pricesLists = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
	private List<Prices> priceses = new ArrayList<>();

}