package es.capitol.comercio.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.capitol.comercio.domain.UserApplication;

/**
 * @author Harold Adrian Bolaños Rodriguez
 *
 * 
 */

@Service
public class UserApplicationDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null) {
			throw new UsernameNotFoundException(username);
		}

		if (username.isBlank() == true) {
			throw new UsernameNotFoundException(username);
		}

		if (username.equals("api") == false) {
			throw new UsernameNotFoundException(username);
		}

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		UserApplication userApplication = new UserApplication(username, bCryptPasswordEncoder.encode("password"));

		return new User(userApplication.getUsername(), userApplication.getPassword(), emptyList());
	}

}
