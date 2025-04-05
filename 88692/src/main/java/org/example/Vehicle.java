package org.example;

abstract class Vehicle {
    private final String brand;
    protected int speed;

    public Vehicle(String brand) {
        this.brand = brand;
        this.speed = 0;
    }

    public String getBrand() {
        return brand;
    }

    abstract void accelerate(int increment);

    public void brake(int decrement) {
        if (speed - decrement < 0) {
            speed = 0;
        } else {
            speed -= decrement;
        }
        System.out.println(brand + " braking. Current speed: " + speed);
    }
}
