package com.booknet.apis.authentication.security.services;

import com.booknet.apis.authentication.models.AppUser;
import com.booknet.apis.authentication.repositories.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AppUserService implements UserDetailsService {
    @Autowired
    IAppUserRepository userRepository;

    @Override
    @Transactional
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "FAILURE: [AppUserService --- loadUserByUsername] User Not Found with username: @username"
                                .replace("@username", username)
                ));
        return AppUserDetails.build(user);
    }
}
