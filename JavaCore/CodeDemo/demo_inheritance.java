// Java Demo: Working with Inheritance, Abstract, Final, Super

public class InheritanceDemo {
    public static void main(String[] args) {
        // Tạo đối tượng các lớp con và dùng thông qua lớp cha
        Employee emp1 = new Manager("Nguyen Van A", 2000, 5);
        Employee emp2 = new Developer("Tran Thi B", 1500, 3);
        Employee emp3 = new Intern("Le Van C", 800);

        System.out.println("=== Quan Ly ===");
        emp1.displayInfo();

        System.out.println("\n=== Lap Trinh Vien ===");
        emp2.displayInfo();

        System.out.println("\n=== Thuc Tap Sinh ===");
        emp3.displayInfo();
    }
}

// ====== 1. Lớp trừu tượng (abstract) ======
// Lớp này không thể tạo đối tượng trực tiếp, chỉ được kế thừa
abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // abstract method: bắt buộc lớp con phải override
    public abstract double calculateBonus();

    public void displayInfo() {
        System.out.println("Ten: " + name);
        System.out.println("Luong co ban: " + salary);
        System.out.println("Thuong: " + calculateBonus());
    }
}

// ====== 2. Lớp con: Manager kế thừa Employee ======
class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double salary, int teamSize) {
        super(name, salary); // Gọi constructor của lớp cha
        this.teamSize = teamSize;
    }

    // Override phương thức abstract từ lớp cha
    @Override
    public double calculateBonus() {
        return salary * 0.2 + teamSize * 100;
    }

    // Ghi đè phương thức displayInfo và dùng super để gọi hàm lớp cha
    public void displayInfo() {
        super.displayInfo(); // Gọi hàm displayInfo() của Employee
        System.out.println("So luong nhan vien quan ly: " + teamSize);
    }
}

// ====== 3. Lớp con: Developer kế thừa Employee ======
class Developer extends Employee {
    private int projectCount;

    public Developer(String name, double salary, int projectCount) {
        super(name, salary);
        this.projectCount = projectCount;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.1 + projectCount * 200;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("So du an dang lam: " + projectCount);
    }
}

// ====== 4. Lớp con: Intern kế thừa Employee và được đánh dấu là final ======
// Lớp này không được phép bị kế thừa
final class Intern extends Employee {
    public Intern(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return 0; // Thực tập sinh không có thưởng
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("(Intern - Khong co thuong)");
    }
}



