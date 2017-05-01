package com.jft.market.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.Roles;
import com.jft.market.model.Role;
import com.jft.market.model.User;
import com.jft.market.repository.UserRepository;

@Component
public class CustomeAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserRepository userRepository;


	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = getUser(authentication);
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
			List<String> roles = new ArrayList<>();
			authorities.forEach(grantedAuthority -> {
				roles.add(grantedAuthority.getAuthority());
			});

			if (user.getEmail().equals(email) && user.getPassword().equals(password) && roles.contains(Roles.ROLE_USER.getName())) {
				return new UsernamePasswordAuthenticationToken(email, password, authorities);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Transactional
	public User getUser(Authentication authentication) {
		String email = authentication.getName();
		return userRepository.findByemail(email);
	}

	public boolean isUserAlreadyLoggedIn() {
		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
			System.out.println("LOGGED IN");
			return true;
		} else {
			System.out.println("NOT LOGGED IN");
			return false;
		}
	}
}
