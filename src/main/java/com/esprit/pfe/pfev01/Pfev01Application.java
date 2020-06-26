package com.esprit.pfe.pfev01;

import com.esprit.pfe.pfev01.Model.*;
import com.esprit.pfe.pfev01.Repository.RoleRepository;
import com.esprit.pfe.pfev01.Repository.UserRepository;
import com.esprit.pfe.pfev01.Repository.structureCRERepository;
import com.esprit.pfe.pfev01.Service.DataCREService;
import com.esprit.pfe.pfev01.security.WebSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableBatchProcessing
public class Pfev01Application {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WebSecurityConfig webSecurityConfig;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(Pfev01Application.class, args);

    }
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    structureCRERepository structureCRERepository;

    @Bean
    CommandLineRunner start() {
        return args -> {
            System.out.println("run");
            List<Role> roles=roleRepository.findAll();
            if (roles.isEmpty()){
                Role role=new Role();
                role.setName(ERole.ROLE_USER);
                roleRepository.save(role);
                Role role2=new Role();
                role2.setName(ERole.ROLE_ADMIN);
                roleRepository.save(role2);
            }
            List<structure> structures = structureCRERepository.findAll();
            if (structures.isEmpty()){
                structure structure = new structure();
                structure.setLabel("numerocompte");
                structure.setDatatype("String");
                structure.setPosition(0);
                structureCRERepository.save(structure);
                structure structure2 = new structure();
                structure2.setLabel("nom");
                structure2.setDatatype("String");
                structure2.setPosition(10);
                structureCRERepository.save(structure2);
                structure structure3 = new structure();
                structure3.setLabel("prenom");
                structure3.setDatatype("String");
                structure3.setPosition(20);
                structureCRERepository.save(structure3);
                structure structure4 = new structure();
                structure4.setLabel("debit");
                structure4.setDatatype("float");
                structure4.setPosition(30);
                structureCRERepository.save(structure4);
                structure structure5 = new structure();
                structure5.setLabel("credit");
                structure5.setDatatype("float");
                structure5.setPosition(45);
                structureCRERepository.save(structure5);
            }
            if (!userRepository.existsByEmail("ramy.hajji1@gmail.com")){
                String bcrypt=webSecurityConfig.passwordEncoder().encode("123456789");
                System.out.println(bcrypt);
                Set<Role> rolesAdmin=new HashSet<>();
                Role r=roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                rolesAdmin.add(r);
                User user=new User();
                user.setRoles(rolesAdmin);
                user.setUsername("admin");
                user.setPassword(bcrypt);
                user.setEmail("ramy.hajji1@gmail.com");
                user.setActive(1);
                userRepository.save(user);
            }
        };
    }
}
