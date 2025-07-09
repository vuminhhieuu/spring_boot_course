import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhap tu so: ");
            int numerator = scanner.nextInt();

            System.out.print("Nhap mau so: ");
            int denominator = scanner.nextInt();

            // Kiểm tra mẫu số có bằng 0 không
            if (denominator == 0) {
                throw new ArithmeticException("Khong the chia cho 0!");
            }

            // Kiểm tra nếu số âm
            if (numerator < 0 || denominator < 0) {
                throw new NegativeNumberException("Khong duoc nhap so am");
            }

            int result = numerator / denominator;
            System.out.println("Ket qua: " + result);
        }

        // 2. Bắt lỗi chia cho 0
        catch (ArithmeticException e) {
            System.out.println("Loi toan hoc " + e.getMessage());
        }

        // 3. Bắt lỗi nhập sai kiểu dữ liệu
        catch (InputMismatchException e) {
            System.out.println("Loi nhap du lieu: Vui long nhap so nguyen!");
        }

        // 4. Bắt lỗi custom do người dùng định nghĩa
        catch (NegativeNumberException e) {
            System.out.println("Loi tai ban: " + e.getMessage());
        }

        // 5. finally luôn chạy dù có lỗi hay không
        finally {
            System.out.println("Ket thuc chuong trinh");
            scanner.close();
        }
    }
}

class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}