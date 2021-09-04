package es.capitol.comercio.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "prices", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prices implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "prices_id", unique = true, nullable = false)
	@NotNull
	private Integer pricesId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	@NotNull
	private Brands brands;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "price_list_id")
	@NotNull
	private PricesList pricesList;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@NotNull
	private Products products;
	@NotNull
	@NotEmpty
	@Size(max = 3)
	@Column(name = "curr", nullable = false)
	private String curr;
	@NotNull
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	@NotNull
	@Column(name = "price", nullable = false)
	private Double price;
	@NotNull
	@Column(name = "priority", nullable = false)
	private Long priority;
	@NotNull
	@Column(name = "start_date", nullable = false)
	private Date startDate;
}
