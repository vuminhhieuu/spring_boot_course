import java.util.*;
import java.util.regex.*;
import java.time.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        manager.run();
    }
}

class Owner {
    private String cmnd;
    private String fullName;
    private String email;

    public Owner(String cmnd, String fullName, String email) {
        if (!cmnd.matches("\\d{12}")) throw new IllegalArgumentException("CMND must be 12 digits");
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) throw new IllegalArgumentException("Invalid email format");
        this.cmnd = cmnd;
        this.fullName = fullName;
        this.email = email;
    }

    public String getCmnd() { return cmnd; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return fullName + " | CMND: " + cmnd + " | Email: " + email;
    }
}

abstract class Vehicle {
    protected String vehicleNumber;
    protected String manufacturer;
    protected int year;
    protected String color;
    protected Owner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int year, String color, Owner owner) {
        if (vehicleNumber.length() != 5) throw new IllegalArgumentException("Vehicle number must be 5 characters");
        if (!Arrays.asList("Honda", "Yamaha", "Toyota", "Suzuki").contains(manufacturer))
            throw new IllegalArgumentException("Invalid manufacturer");
        int currentYear = Year.now().getValue();
        if (year < 2001 || year > currentYear)
            throw new IllegalArgumentException("Year must be between 2001 and current year");

        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.year = year;
        this.color = color;
        this.owner = owner;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getManufacturer() { return manufacturer; }
    public Owner getOwner() { return owner; }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " - " + vehicleNumber + ", " + manufacturer + ", " + year + ", " + color + ", Owner: [" + owner + "]";
    }
}

class Car extends Vehicle {
    private int numberOfSeats;
    private String engineType;

    public Car(String vehicleNumber, String manufacturer, int year, String color, Owner owner,
               int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, year, color, owner);
        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType;
    }

    public String getType() { return "Car"; }
}

class Motorbike extends Vehicle {
    private int capacity;

    public Motorbike(String vehicleNumber, String manufacturer, int year, String color, Owner owner,
                     int capacity) {
        super(vehicleNumber, manufacturer, year, color, owner);
        this.capacity = capacity;
    }

    public String getType() { return "Motorbike"; }
}

class Truck extends Vehicle {
    private double tonnage;

    public Truck(String vehicleNumber, String manufacturer, int year, String color, Owner owner,
                 double tonnage) {
        super(vehicleNumber, manufacturer, year, color, owner);
        this.tonnage = tonnage;
    }

    public String getType() { return "Truck"; }
}

class VehicleManager {
    private List<Vehicle> vehicles = new ArrayList<>();
    private Set<String> usedVehicleNumbers = new HashSet<>();
    private Set<String> usedCMNDs = new HashSet<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n1. Add vehicle");
            System.out.println("2. Search by vehicle number");
            System.out.println("3. Search by owner CMND");
            System.out.println("4. Delete all vehicles by manufacturer");
            System.out.println("5. Find manufacturer with most vehicles");
            System.out.println("6. Sort vehicles by manufacturer count");
            System.out.println("7. Count vehicles by type");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addVehicle();
                case 2 -> searchByVehicleNumber();
                case 3 -> searchByCMND();
                case 4 -> deleteByManufacturer();
                case 5 -> findTopManufacturer();
                case 6 -> sortByManufacturerCount();
                case 7 -> countByType();
                case 0 -> {
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addVehicle() {
        try {
            System.out.print("Vehicle number (5 chars): ");
            String vn = scanner.nextLine();
            if (usedVehicleNumbers.contains(vn)) throw new IllegalArgumentException("Vehicle number already exists");

            System.out.print("Manufacturer (Honda/Yamaha/Toyota/Suzuki): ");
            String mfr = scanner.nextLine();
            System.out.print("Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Color: ");
            String color = scanner.nextLine();

            System.out.print("Owner CMND (12 digits): ");
            String cmnd = scanner.nextLine();
            if (usedCMNDs.contains(cmnd)) throw new IllegalArgumentException("CMND already exists");

            System.out.print("Owner full name: ");
            String fullName = scanner.nextLine();
            System.out.print("Owner email: ");
            String email = scanner.nextLine();
            Owner owner = new Owner(cmnd, fullName, email);
            usedCMNDs.add(cmnd);

            System.out.println("Type of vehicle: 1-Car, 2-Motorbike, 3-Truck");
            int type = Integer.parseInt(scanner.nextLine());
            Vehicle v = null;

            switch (type) {
                case 1 -> {
                    System.out.print("Seats: ");
                    int seats = Integer.parseInt(scanner.nextLine());
                    System.out.print("Engine type: ");
                    String engine = scanner.nextLine();
                    v = new Car(vn, mfr, year, color, owner, seats, engine);
                }
                case 2 -> {
                    System.out.print("Capacity (cc): ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    v = new Motorbike(vn, mfr, year, color, owner, capacity);
                }
                case 3 -> {
                    System.out.print("Tonnage (tons): ");
                    double tonnage = Double.parseDouble(scanner.nextLine());
                    v = new Truck(vn, mfr, year, color, owner, tonnage);
                }
                default -> throw new IllegalArgumentException("Invalid type");
            }
            vehicles.add(v);
            usedVehicleNumbers.add(vn);
            System.out.println("Vehicle added.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchByVehicleNumber() {
        System.out.print("Enter vehicle number: ");
        String vn = scanner.nextLine();
        vehicles.stream()
                .filter(v -> v.getVehicleNumber().equals(vn))
                .forEach(System.out::println);
    }

    private void searchByCMND() {
        System.out.print("Enter CMND: ");
        String cmnd = scanner.nextLine();
        vehicles.stream()
                .filter(v -> v.getOwner().getCmnd().equals(cmnd))
                .forEach(System.out::println);
    }

    private void deleteByManufacturer() {
        System.out.print("Enter manufacturer to delete: ");
        String mfr = scanner.nextLine();
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(mfr));
        System.out.println("Deleted all vehicles from " + mfr);
    }

    private void findTopManufacturer() {
        Map<String, Long> count = vehicles.stream().collect(
                Collectors.groupingBy(Vehicle::getManufacturer, Collectors.counting()));
        count.entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println("Top manufacturer: " + e.getKey() + " with " + e.getValue() + " vehicles"));
    }

    private void sortByManufacturerCount() {
        Map<String, Long> count = vehicles.stream().collect(
                Collectors.groupingBy(Vehicle::getManufacturer, Collectors.counting()));

        count.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    private void countByType() {
        Map<String, Long> count = vehicles.stream().collect(
                Collectors.groupingBy(Vehicle::getType, Collectors.counting()));
        count.forEach((type, c) -> System.out.println(type + ": " + c));
    }
}
