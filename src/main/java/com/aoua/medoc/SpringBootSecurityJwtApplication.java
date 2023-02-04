package com.aoua.medoc;
import com.aoua.medoc.repository.RoleRepository;
import com.aoua.medoc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;


@AllArgsConstructor
@SpringBootApplication @EnableScheduling
public class SpringBootSecurityJwtApplication implements CommandLineRunner {

    @Autowired
    PasswordEncoder encoder;

    //le password que admin prendra lors de sa creation automatique


	@Autowired
    private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 String password = "Aoua1414";
		if (roleRepository.findAll().size()==0){
			roleRepository.creationRoleAdmin();


		}

		if (userRepository.findAll().size()==0) {
			userRepository.adminConnect(encoder.encode(password));
			roleRepository.addRoleToUser();
		}
	}
}
