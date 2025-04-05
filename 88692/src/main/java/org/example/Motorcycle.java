package org.example;

public class Motorcycle extends Vehicle {
    public Motorcycle(String brand) {
        super(brand);
    }

    @Override
    public void accelerate(int increment) {
        speed += increment * 2;
        System.out.println(getBrand() + " accelerating (motorcycle mode). Current speed: " + speed);
    }

    @Override
    public String toString() {
        return "Motorcycle " +
                "brand='" + getBrand() + '\'' +
                ", speed=" + speed ;
    }
}
