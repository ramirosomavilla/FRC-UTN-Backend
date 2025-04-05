package com.ramirosomavilla.hello_world_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldAppApplication.class, args);

		// Crear y usar Fraction de manera normal
		Fraction f1 = new Fraction(4, 8);
		Fraction f2 = new Fraction(3);
		Fraction f3 = new Fraction(-6, -9);
		System.out.println(f1); // 1/2
		System.out.println(f2); // 3
		System.out.println(f3); // 2/3
		f1.sumar(f3);
	}
}

