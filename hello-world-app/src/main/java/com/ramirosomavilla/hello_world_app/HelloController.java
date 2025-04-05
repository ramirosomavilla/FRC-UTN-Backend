package com.ramirosomavilla.hello_world_app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Hello Controller", description = "API para operaciones básicas y operaciones con fracciones")
public class HelloController {

    @Operation(
            summary = "Saludo con operaciones de fracciones",
            description = "Retorna un saludo junto con el resultado de operaciones de suma entre fracciones"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Saluda a este huevon");
        Fraction fraction = new Fraction(120,20);
        Fraction f1 = new Fraction(4, 8);
        Fraction f2 = new Fraction(3, 4);
        Fraction suma = f1.sumar(f2);
        Fraction producto = f1.multiplicar(f2);
        System.out.println("Suma: " + suma); // Resultado esperado: 5/4
        System.out.println("Multiplicación: " + producto); // Resultado esperado: 3/8
        return "¡Hello World! \n" + f1.sumar(f2);
    }
}