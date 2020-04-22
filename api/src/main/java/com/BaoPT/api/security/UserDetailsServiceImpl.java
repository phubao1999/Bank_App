/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.service.UserService;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/21      (VNEXT) BaoPT       Create new
*/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    /**
     * @author (VNEXT) BaoPT
     * @param username
     * Return UserEntity And Hash username and password to token
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("### loadbyusername Start ###");
        UserEntity user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " Not Found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        log.debug("### loadbyusername End ###");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
