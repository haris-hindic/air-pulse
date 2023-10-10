package com.pulse.air.auth.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pulse.air.auth.dao.UserRepository;
import com.pulse.air.auth.model.auth.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username);
		return user.map(x -> new CustomUserDetails(x.getUsername(), x.getPassword()))
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User (%s) not found!", username)));
	}

}
