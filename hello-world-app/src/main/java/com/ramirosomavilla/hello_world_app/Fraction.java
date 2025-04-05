package com.ramirosomavilla.hello_world_app;

public class Fraction {
    private int numerador;
    private int denominador;

    // Constructor con numerador y denominador opcional
    public Fraction(int numerador, int denominador) {
        if (denominador == 0) {
            denominador = 1;
        }
        this.numerador = numerador;
        this.denominador = denominador;
        simplificar();
    }

    // Constructor con solo numerador (denominador = 1 por defecto)
    public Fraction(int numerador) {
        this(numerador, 1);
    }

    // Método para simplificar la fracción
    private void simplificar() {
        int gcd = mcd(Math.abs(numerador), Math.abs(denominador));
        numerador /= gcd;
        denominador /= gcd;

        // Asegurar que el denominador siempre sea positivo
        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    // Metodo para calcular el máximo común divisor
    private int mcd(int a, int b) {
        return b == 0 ? a : mcd(b, a % b);
    }

    // Metodo para sumar Fractiones
    public Fraction sumar(Fraction otra) {
        int nuevoNumerador = this.numerador * otra.denominador + otra.numerador * this.denominador;
        int nuevoDenominador = this.denominador * otra.denominador;
        Fraction newFraction = new Fraction(nuevoNumerador, nuevoDenominador);
        System.out.println(this.toString() + " + " + otra.toString() + " = " + newFraction.toString());
        return new Fraction(nuevoNumerador, nuevoDenominador);
    }

    // Método para multiplicar Fractiones
    public Fraction multiplicar(Fraction otra) {
        int nuevoNumerador = this.numerador * otra.numerador;
        int nuevoDenominador = this.denominador * otra.denominador;
        return new Fraction(nuevoNumerador, nuevoDenominador);
    }

    @Override
    public String toString() {
        if (denominador == 1) {
            return String.valueOf(numerador);
        }
        return numerador + "/" + denominador;
    }

    public static void main(String[] args) {
        Fraction f1 = new Fraction(4, 8);
        Fraction f2 = new Fraction(3);
        Fraction f3 = new Fraction(-6, -9);
        System.out.println(f1); // 1/2
        System.out.println(f2); // 3
        System.out.println(f3); // 2/3
        f1.sumar(f3);
    }
}
