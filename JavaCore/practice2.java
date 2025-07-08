import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Them hang hoa");
            System.out.println("2. Hien thi danh sach hang hoa");
            System.out.println("3. Danh gia tieu thu");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    manager.inputGoods(sc);
                    break;
                case 2:
                    manager.displayAll();
                    break;
                case 3:
                    manager.evaluateAll();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}

// ======= Abstract class Goods =======
abstract class Goods {
    protected String productCode;
    protected String name;
    protected int quantity;
    protected double unitPrice;

    public Goods(String productCode, String name, int quantity, double unitPrice) {
        this.productCode = productCode;
        this.name = name;
        this.quantity = Math.max(quantity, 0);
        this.unitPrice = unitPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public abstract double getVAT();

    public abstract String evaluateConsumption();

    @Override
    public String toString() {
        return String.format("Ma: %s | Ten: %s | SL: %d | Don gia: %.2f | VAT: %.1f%%",
                productCode, name, quantity, unitPrice, getVAT() * 100);
    }
}

// ======= Food =======
class Food extends Goods {
    private LocalDate dateManufacture;
    private LocalDate dateExpire;
    private String supplier;

    public Food(String productCode, String name, int quantity, double unitPrice,
                LocalDate dateManufacture, LocalDate dateExpire, String supplier) {
        super(productCode, name, quantity, unitPrice);
        this.dateManufacture = dateManufacture;
        this.dateExpire = dateExpire.isAfter(dateManufacture) ? dateExpire : dateManufacture.plusDays(1);
        this.supplier = supplier;
    }

    @Override
    public double getVAT() {
        return 0.05;
    }

    @Override
    public String evaluateConsumption() {
        if (quantity > 0 && dateExpire.isBefore(LocalDate.now())) {
            return "Kho ban (het han)";
        }
        return "Khong danh gia";
    }

    @Override
    public String toString() {
        return "[Thuc Pham] " + super.toString() +
                String.format(" | NSX: %s | HSD: %s | NCC: %s",
                        dateManufacture, dateExpire, supplier);
    }
}

// ======= ElectronicGoods =======
class ElectronicGoods extends Goods {
    private int warrantyMonths;
    private double capacityKW;

    public ElectronicGoods(String productCode, String name, int quantity, double unitPrice,
                           int warrantyMonths, double capacityKW) {
        super(productCode, name, quantity, unitPrice);
        this.warrantyMonths = Math.max(warrantyMonths, 0);
        this.capacityKW = Math.max(capacityKW, 0);
    }

    @Override
    public double getVAT() {
        return 0.10;
    }

    @Override
    public String evaluateConsumption() {
        return quantity < 3 ? "Da ban" : "Khong danh gia";
    }

    @Override
    public String toString() {
        return "[Dien tu] " + super.toString() +
                String.format(" | Bao hanh: %d tháng | Cong suat: %.2f KW",
                        warrantyMonths, capacityKW);
    }
}

// ======= Crockery =======
class Crockery extends Goods {
    private String manufacturer;
    private LocalDate dateArrival;

    public Crockery(String productCode, String name, int quantity, double unitPrice,
                    String manufacturer, LocalDate dateArrival) {
        super(productCode, name, quantity, unitPrice);
        this.manufacturer = manufacturer;
        this.dateArrival = dateArrival;
    }

    @Override
    public double getVAT() {
        return 0.10;
    }

    @Override
    public String evaluateConsumption() {
        long daysStored = ChronoUnit.DAYS.between(dateArrival, LocalDate.now());
        if (quantity > 50 && daysStored > 10) {
            return "Ban cham";
        }
        return "Khong danh gia";
    }

    @Override
    public String toString() {
        return "[Sanh su] " + super.toString() +
                String.format(" | NSX: %s | Ngay nhap: %s",
                        manufacturer, dateArrival);
    }
}

// ======= InventoryManager =======
class InventoryManager {
    private List<Goods> goodsList;

    public InventoryManager() {
        goodsList = new ArrayList<>();
    }

    public void addGoods(Goods g) {
        for (Goods item : goodsList) {
            if (item.getProductCode().equalsIgnoreCase(g.getProductCode())) {
                System.out.println("Ma hang da ton tai!");
                return;
            }
        }
        goodsList.add(g);
        System.out.println("Them thanh cong.");
    }

    public void displayAll() {
        if (goodsList.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        for (Goods g : goodsList) {
            System.out.println(g);
        }
    }

    public void evaluateAll() {
        if (goodsList.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        for (Goods g : goodsList) {
            System.out.printf("▶ %s → Danh gia: %s\n", g.getProductCode(), g.evaluateConsumption());
        }
    }

    public void inputGoods(Scanner sc) {
        System.out.println("Chon loai hang:");
        System.out.println("1. Thuc pham");
        System.out.println("2. Dien tu");
        System.out.println("3. Sanh su");
        System.out.print("Chon: ");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Ma SP: ");
        String code = sc.nextLine();
        System.out.print("Ten SP: ");
        String name = sc.nextLine();
        System.out.print("So luong: ");
        int qty = Integer.parseInt(sc.nextLine());
        System.out.print("Don gia: ");
        double price = Double.parseDouble(sc.nextLine());

        switch (type) {
            case 1:
                System.out.print("Ngay san xuat (yyyy-MM-dd): ");
                LocalDate nsx = LocalDate.parse(sc.nextLine());
                System.out.print("Han su dung (yyyy-MM-dd): ");
                LocalDate hsd = LocalDate.parse(sc.nextLine());
                System.out.print("Nha cung cap: ");
                String ncc = sc.nextLine();
                addGoods(new Food(code, name, qty, price, nsx, hsd, ncc));
                break;
            case 2:
                System.out.print("Bao hanh (thang): ");
                int bh = Integer.parseInt(sc.nextLine());
                System.out.print("Cong suat (KW): ");
                double kw = Double.parseDouble(sc.nextLine());
                addGoods(new ElectronicGoods(code, name, qty, price, bh, kw));
                break;
            case 3:
                System.out.print("Nha san xuat: ");
                String nsxss = sc.nextLine();
                System.out.print("Ngay nhap (yyyy-MM-dd): ");
                LocalDate ngayNhap = LocalDate.parse(sc.nextLine());
                addGoods(new Crockery(code, name, qty, price, nsxss, ngayNhap));
                break;
            default:
                System.out.println("Loai hang khong hop le.");
        }
    }
}
