package com.fundases.springboot.backend.apirest.fundases.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/proveedores",
						"/api/proveedores/page/**", "/api/vendedores", "/api/vendedores/page/**",
						"/api/uploads/arch/**", "/api/proveedores/upload", "/api/solicitudCompraDetalles",
						"/api/solicitudCompraDetalles/page/**", "/api/solicitudCompras", "/api/solicitudCompras/page/**","/api/solicitudCotizacionDetalles",
						"/api/solicitudCotizacionDetalles/page/**", "/api/solicitudCotizacion", "/api/solicitudCotizacion/page/**","/api/proveedores/{id}")
				.permitAll()
				/*
				 * .antMatchers("/api/clientes/{id}").permitAll()
				 * .antMatchers("/api/facturas/**").permitAll() .antMatchers(HttpMethod.GET,
				 * "/api/clientes/{id}").hasAnyRole("CLIENTE", "ADMIN")
				 * .antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
				 * .antMatchers("/api/clientes/**").hasRole("ADMIN")
				 */
				.anyRequest().authenticated()
				.and().cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
