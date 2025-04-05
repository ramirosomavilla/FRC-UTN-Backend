package org.example;

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }

    @Override
    public void accelerate(int increment) {
        speed += increment;
        System.out.println(getBrand() + " accelerating. Current speed: " + speed);
    }

    @Override
    public String toString() {
        return "Car " +
                "brand='" + getBrand() + '\'' +
                ", speed=" + speed ;
    }
}
