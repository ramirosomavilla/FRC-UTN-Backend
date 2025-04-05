package org.example;

public class Excercises {
    public static void excercise1() {
        System.out.println("Excercise 1");
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i <= 10; i++) {
                if (i % 2 == 0) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void excercise2() {
        System.out.println("Excercise 2");
        for (int j = 1; j < 5; j++) {
                    System.out.print("* ".repeat(j));
            System.out.println();
        }
    }

    public static void excercise3() {
        System.out.println("Excercise 3");
        for (int j = 0; j < 4; j++) {
            if (j % 2 == 1) {
                System.out.print(" ");
            };
            for (int i = 0; i <= 10; i++) {
                if (i % 2 == 0) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void excercise4() {
        System.out.println("Excercise 4");
        for (int i = 0; i < 11; i++) {
            if (i < 5) {
                System.out.print("* ".repeat(i));
            } else {
                System.out.print("* ".repeat(10 - i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        excercise1();
        excercise2();
        excercise3();
        excercise4();
    }
}
