import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DemoStringArrayDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập thông tin cho 3 sinh viên
        String[] names = new String[3];
        double[] scores = new double[3];
        LocalDate[] birthdates = new LocalDate[3];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < 3; i++) {
            System.out.println("=== Nhap thong tin sinh vien thu " + (i + 1) + " ===");

            // Làm việc với String
            System.out.print("Ho ten: ");
            names[i] = scanner.nextLine().trim();  // dùng trim để loại bỏ khoảng trắng

            // Làm việc với Array
            System.out.print("Diem trung binh: ");
            scores[i] = Double.parseDouble(scanner.nextLine());

            // Làm việc với Date
            System.out.print("Ngay sinh (dd/MM/yyyy): ");
            String dateInput = scanner.nextLine();
            birthdates[i] = LocalDate.parse(dateInput, formatter);
        }

        // In ra danh sách
        System.out.println("\n=== Danh sach sinh vien ===");
        for (int i = 0; i < 3; i++) {
            System.out.println("Ten: " + names[i].toUpperCase()); // String method
            System.out.println("Diem: " + scores[i]);
            System.out.println("Ngay sinh: " + birthdates[i].format(formatter));
            System.out.println("Tuoi: " + (LocalDate.now().getYear() - birthdates[i].getYear()));
            System.out.println("---------------------------");
        }

        scanner.close();
    }
}
