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
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 * 
 */
@Entity
@Table(name = "prices_list", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricesList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "price_list_id", unique = true, nullable = false)
	@NotNull
	private Long priceListId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@NotNull
	private Products products;

	@Column(name = "state")
	private Long state;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pricesList")
	private List<Prices> priceses = new ArrayList<>();

}