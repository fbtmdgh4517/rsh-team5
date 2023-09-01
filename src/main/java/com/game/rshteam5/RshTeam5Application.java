package com.game.rshteam5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan
@ServletComponentScan
public class RshTeam5Application {

	public static void main(String[] args) {
		SpringApplication.run(RshTeam5Application.class, args);
	}

}
