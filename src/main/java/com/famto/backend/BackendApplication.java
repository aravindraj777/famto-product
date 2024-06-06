package com.famto.backend;

import com.famto.backend.enums.Role;
import com.famto.backend.model.Admin;
import com.famto.backend.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	IAdminRepository adminRepository;

	@Value("${admin.password}")
	private String adminPass ;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Admin admin = adminRepository.findByRole(Role.ADMIN);
		if (admin == null){
			adminRepository.save(
					Admin.builder().name("famAdmin")
							.email("adminfamto@gmail.com")
							.password(new BCryptPasswordEncoder().encode(adminPass))
							.role(Role.ADMIN)
							.build()
			);
		}

	}
}
