package es.capitol.comercio.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "type_products", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeProducts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "type_id", unique = true, nullable = false)
	@NotNull
	private String typeId;

	@NotNull
	@NotEmpty
	@Size(max = 30)
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeProducts")
	private List<Products> productses = new ArrayList<>();

}