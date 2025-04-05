package org.example;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println();
                        System.out.println("Select an option: 1. Show vehicle simulation 2. Enter vehicle simulator 3. Exit");
                        int mainChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (mainChoice == 3) {
                                break;
                        }

                        switch (mainChoice) {
                                case 1:
                                        showVehicleSimulation();
                                        break;
                                case 2:
                                        enterVehicleSimulator(scanner);
                                        break;
                                default:
                                        System.out.println("Invalid choice.");
                        }
                }

                scanner.close();
        }

        private static void showVehicleSimulation() {
                Vehicle car = new Car("Toyota");
                Vehicle motorcycle = new Motorcycle("Honda");
                ElectricCar electricCar = new ElectricCar("Tesla");
                SportsCar sportsCar = new SportsCar("Ferrari");

                car.accelerate(10);
                car.brake(5);
                System.out.println(car);
                System.out.println();

                motorcycle.accelerate(10);
                motorcycle.brake(5);
                System.out.println(motorcycle);
                System.out.println();

                electricCar.accelerate(10);
                electricCar.brake(5);
                electricCar.chargeBattery(20);
                System.out.println(electricCar);
                System.out.println();

                sportsCar.accelerate(10);
                sportsCar.activateTurbo();
                sportsCar.accelerate(10);
                sportsCar.deactivateTurbo();
                sportsCar.brake(5);
                System.out.println(sportsCar);
                System.out.println();
        }

        private static void enterVehicleSimulator(Scanner scanner) {
                Vehicle vehicle = null;

                while (true) {
                        System.out.println();
                        System.out.println("Select a vehicle type: 1. Car 2. Motorcycle 3. ElectricCar 4. SportsCar 5. Exit");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (choice == 5) {
                                break;
                        }

                        System.out.println("Enter the brand of the vehicle:");
                        String brand = scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        vehicle = new Car(brand);
                                        break;
                                case 2:
                                        vehicle = new Motorcycle(brand);
                                        break;
                                case 3:
                                        vehicle = new ElectricCar(brand);
                                        break;
                                case 4:
                                        vehicle = new SportsCar(brand);
                                        break;
                                default:
                                        System.out.println("Invalid choice.");
                                        continue;
                        }

                        while (true) {
                                System.out.println();
                                System.out.println("Select an action: 1. Accelerate 2. Brake 3. Charge Battery (ElectricCar only) 4. Activate Turbo (SportsCar only) 5. Deactivate Turbo (SportsCar only) 6. Show Status 7. Select another vehicle");
                                int action = scanner.nextInt();
                                scanner.nextLine(); // consume newline

                                if (action == 7) {
                                        break;
                                }

                                switch (action) {
                                        case 1:
                                                System.out.println("Enter the increment value:");
                                                int increment = scanner.nextInt();
                                                scanner.nextLine(); // consume newline
                                                vehicle.accelerate(increment);
                                                break;
                                        case 2:
                                                System.out.println("Enter the decrement value:");
                                                int decrement = scanner.nextInt();
                                                scanner.nextLine(); // consume newline
                                                vehicle.brake(decrement);
                                                break;
                                        case 3:
                                                if (vehicle instanceof ElectricCar) {
                                                        System.out.println("Enter the charge amount:");
                                                        int charge = scanner.nextInt();
                                                        scanner.nextLine(); // consume newline
                                                        ((ElectricCar) vehicle).chargeBattery(charge);
                                                } else {
                                                        System.out.println("This action is only available for ElectricCar.");
                                                }
                                                break;
                                        case 4:
                                                if (vehicle instanceof SportsCar) {
                                                        ((SportsCar) vehicle).activateTurbo();
                                                } else {
                                                        System.out.println("This action is only available for SportsCar.");
                                                }
                                                break;
                                        case 5:
                                                if (vehicle instanceof SportsCar) {
                                                        ((SportsCar) vehicle).deactivateTurbo();
                                                } else {
                                                        System.out.println("This action is only available for SportsCar.");
                                                }
                                                break;
                                        case 6:
                                                System.out.println(vehicle.toString());
                                                break;
                                        default:
                                                System.out.println("Invalid action.");
                                }
                        }
                }
        }
}