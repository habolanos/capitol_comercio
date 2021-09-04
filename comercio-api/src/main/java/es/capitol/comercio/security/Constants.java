package  es.capitol.comercio.security;

/**
* @author Harold Adrian Bolaños Rodriguez
* 
*/
public class Constants {

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String ACTUATOR_URL = "/actuator/**";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String ISSUER_INFO = "haroldadrian@gmail.com";
	public static final String SUPER_SECRET_KEY = "h4r81d4dr14nh4r81d4dr14nh4r81d4dr14nh4r81d4dr14nh4r81d4dr14nh4B";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

}
