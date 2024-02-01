package com.eshop.eshop.bootstrap;

import com.eshop.eshop.entity.RoleEntity;
import com.eshop.eshop.entity.UserEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.RoleRepository;
import com.eshop.eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class SuperUserSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${app.super-user-email}")
    private String superUserEmail;

    @Value("${app.super-user-phone}")
    private String superUserPhone;

    @Value("${app.super-user-password}")
    private String superUserPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperUser();
    }

    private void createSuperUser() {
        try {
           if (!(userRepository.existsByEmail(superUserEmail) || userRepository.existsByPhone(superUserPhone))){
               UserEntity existingSuperUser = userRepository.findByPhoneOrEmail(superUserEmail, superUserEmail).orElse(null);

               if (existingSuperUser == null) {

                   UserEntity superUser = new UserEntity();

                   superUser.setEmail(superUserEmail);
                   superUser.setPhone(superUserPhone);
                   superUser.setPassword(passwordEncoder.encode(superUserPassword));

                   Set<RoleEntity> roles = new HashSet<>();

                   RoleEntity role = roleRepository.findByName("SUPER_ADMIN").orElseThrow(() -> new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found!"));

                   roles.add(role);

                   superUser.setRoles(roles);

                   userRepository.save(superUser);
               }
           }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
