package com.imon.jobapp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {
		JdbcTemplateAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})
public class JobappApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(JobappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("application started");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("password");

		System.out.println(password);

		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("Select * from company");

		queryForList.forEach(item -> {
			System.out.println("id " + item.get("id"));
			System.out.println("description " + item.get("description"));
			System.out.println("name " + item.get("name"));

			System.out.println("--------------------------");
		});
	}

}
