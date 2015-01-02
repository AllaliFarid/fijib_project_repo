package com.fijib.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.impl.domain.service.IUtilisateurMetier;
import com.fijib.impl.persistence.entity.Role;
 

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
    private IUtilisateurMetier userDAO;   
    private Role r;
	@Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	       
	        com.fijib.impl.persistence.entity.Utilisateur utilisateur = userDAO.findUserByName(email);
 	        boolean enabled = true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
	        Collection<GrantedAuthority> authoes = new ArrayList<GrantedAuthority>();
	    	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
 			Iterator<Role> i=utilisateur.getRoles().iterator();
			while(i.hasNext())
				r=i.next();
			authorities.add(new GrantedAuthorityImpl(r.getNomRole()));
	        return new User(
	        	utilisateur.getEmail(),
	        	utilisateur.getPassword(),
	            enabled,
	            accountNonExpired,
	            credentialsNonExpired,
	            accountNonLocked,
	            authorities
	        );
	    }
   
    


}