package com.socgen.stockmarketcharting.security.service;




import com.socgen.stockmarketcharting.model.UserEntity;
import com.socgen.stockmarketcharting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(userEntity);
    }
    public UserEntity findById(long id){
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if(userOptional.isPresent())
            return userOptional.get();
        throw new UsernameNotFoundException("Not found");
    }
}
