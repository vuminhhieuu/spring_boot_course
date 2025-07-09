import java.util.*;
import java.util.stream.Collectors;

public class CollectionDemo {
    public static void main(String[] args) {
        // 2. Tạo danh sách sinh viên (Collection)
        List<Student> students = new ArrayList<>();
        students.add(new Student("Nguyen Van A", "IT1", 3.2));
        students.add(new Student("Tran Thi B", "IT2", 3.9));
        students.add(new Student("Le Van C", "IT1", 2.8));
        students.add(new Student("Pham Van D", "IT2", 3.5));
        students.add(new Student("Do Thi E", "IT1", 3.9));

        // 3. Sắp xếp theo GPA giảm dần (lambda)
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        System.out.println("=== Sinh vien sap xep theo GPA giam dan ===");
        students.forEach(System.out::println);

        // 4. Nhóm theo lớp học (sử dụng Map + stream + lambda)
        Map<String, List<Student>> groupedByClass = students.stream()
                .collect(Collectors.groupingBy(Student::getClassName));

        System.out.println("\n=== Sinh vien hoc theo lop hoc ===");
        for (String className : groupedByClass.keySet()) {
            System.out.println("Lop: " + className);
            groupedByClass.get(className).forEach(System.out::println);
        }

        // 5. Tìm sinh viên có GPA cao nhất (generic method)
        Student topStudent = findMax(students, Comparator.comparing(Student::getGpa));
        System.out.println("\nSinh vien co GPA cao nhat: " + topStudent);
    }

    // 6. Generic method để tìm max theo tiêu chí
    public static <T> T findMax(List<T> list, Comparator<T> comparator) {
        return list.stream().max(comparator).orElse(null);
    }
}

class Student {
    private String name;
    private String className;
    private double gpa;

    public Student(String name, String className, double gpa) {
        this.name = name;
        this.className = className;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return name + " | Lop: " + className + " | GPA: " + gpa;
    }
}


