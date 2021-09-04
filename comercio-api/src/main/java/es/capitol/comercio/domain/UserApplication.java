package es.capitol.comercio.domain;

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
public class UserApplication {

	private String username;

	private String password;

}
