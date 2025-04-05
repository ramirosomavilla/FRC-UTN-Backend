package org.example;

public class ElectricCar extends Car implements Electric {
    private int battery;

    public ElectricCar(String brand) {
        super(brand);
        this.battery = 100;
    }

    @Override
    public void accelerate(int increment) {
        if (battery <= 0) {
            System.out.println(getBrand() + " cannot accelerate. Battery is empty.");
            return;
        }
        speed += increment;
        battery -= 10;
        if (battery < 0) battery = 0;
        System.out.println(getBrand() + " accelerating (electric). Current speed: " + speed + ", Battery: " + battery + "%");
    }

    @Override
    public void chargeBattery(int charge) {
        if (battery + charge > 100) {
            battery = 100;
        } else if (battery + charge < 0) {
            battery = 0;
        } else {
            battery = battery + charge;
        }
        System.out.println(getBrand() + " charging battery. Battery at " + battery + "%.");
    }

    @Override
    public String toString() {
        return "ElectricCar " +
                "brand='" + getBrand() + '\'' +
                ", speed=" + speed +
                ", battery=" + battery ;
    }
}
