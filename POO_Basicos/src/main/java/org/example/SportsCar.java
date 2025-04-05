package org.example;

public class SportsCar extends Car {
    private boolean turboActive;

    public SportsCar(String brand) {
        super(brand);
        this.turboActive = false;
    }

    public void activateTurbo() {
        turboActive = true;
        System.out.println(getBrand() + " has activated the turbo.");
    }

    public void deactivateTurbo() {
        turboActive = false;
        System.out.println(getBrand() + " has deactivated the turbo.");
    }

    public boolean isTurboActive() {
        return turboActive;
    }

    @Override
    public void accelerate(int increment) {
        if (turboActive) {
            super.accelerate(increment * 2);
        } else {
            super.accelerate(increment);
        }
    }

    @Override
    public String toString() {
        return "SportsCar " +
                "brand='" + getBrand() + '\'' +
                ", speed=" + speed +
                ", turboActive=" + turboActive;
    }
}
