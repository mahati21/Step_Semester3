package categoryB.Class_problemsW10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class W10CampusVehiclePassSystem {
    interface W10Chargeable {
        void bookChargingBay();
    }

    static class W10CampusVehicle {
        String passNumber;
        String owner;
        String type;

        W10CampusVehicle(
                String passNumber, String owner, String type) {
            this.passNumber = passNumber;
            this.owner = owner;
            this.type = type;
        }

        int getPassFee() {
            return (type.equals("Bike") ||
                    type.equals("EBike")) ? 300 : 1000;
        }
    }

    static class W10ElectricCampusVehicle extends W10CampusVehicle implements W10Chargeable {
        W10ElectricCampusVehicle(
                String passNumber, String owner, String type) {
            super(passNumber, owner, type);
        }
        public void bookChargingBay() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, W10CampusVehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();
            if (operation.equals("PASS")) {
                String type = sc.next();
                String pass = sc.next();
                String owner = sc.next();
                W10CampusVehicle vehicle;

                if (type.equals("EBike") || type.equals("ECar")) {
                    vehicle = new W10ElectricCampusVehicle(pass, owner, type);
                }
                else {
                    vehicle = new W10CampusVehicle(pass, owner, type);
                }

                vehicles.put(pass, vehicle);
                System.out.println(pass + " (" + type + ") pass fee " + vehicle.getPassFee());

            }
            else if (operation.equals("CHARGE")) {
                String pass = sc.next();
                W10CampusVehicle vehicle = vehicles.get(pass);
                if (vehicle == null) {
                    System.out.println("Vehicle not found");
                }
                else if (vehicle instanceof W10Chargeable) {
                    ((W10Chargeable) vehicle).bookChargingBay();
                }
                else {
                    System.out.println(pass + " rejected: charging unsupported");
                }
            }
        }
        sc.close();
    }
}
