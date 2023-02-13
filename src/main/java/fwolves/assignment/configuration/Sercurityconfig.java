package fwolves.assignment.configuration;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import fwolves.assignment.entity.User;
import fwolves.assignment.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Sercurityconfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder getPasswordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthorizationRequestRepository<OAuth2AuthorizationRequest> getRepository() {
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}

	@Bean
	public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> getToken() {
		return new DefaultAuthorizationCodeTokenResponseClient();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				User user = userRepository.getByUserName(username);
				String password = user.getPassword();
				String[] roles = user.getAuthority().stream().map(au -> au.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return org.springframework.security.core.userdetails.User.withUsername(username)
						.password(pe.encode(password)).roles(roles).build();
			} catch (Exception e) {
				throw new UsernameNotFoundException(username + " not found!");
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();

		http.authorizeRequests().antMatchers("/home/cart", "/home/order/**").hasAnyRole("GUEST", "ADMIN", "DIRECT")
				.antMatchers("/admin/**").hasAnyRole("ADMIN", "DIRECT")
				.antMatchers("/home/edit-profile", "/home/change-pasword").authenticated().anyRequest().permitAll();

		http.formLogin().loginPage("/access/login").loginProcessingUrl("/access/login")
				.defaultSuccessUrl("/home/index", false).failureUrl("/access/login?ps=Login Failed!")
				.usernameParameter("username").passwordParameter("password");
		http.rememberMe().rememberMeParameter("remember_me");

		http.oauth2Login().loginPage("/oauth2/login").defaultSuccessUrl("/oauth2/login/success", false)
				.failureUrl("/access/login?error=Somthing Went Wrong!").authorizationEndpoint()
				.baseUri("/oauth2/authorization");// .authorizationRequestRepository(getRepository()).and().tokenEndpoint().accessTokenResponseClient(getToken())

		http.logout().logoutUrl("/home/logout").logoutSuccessUrl("/home/index");
	}

}
