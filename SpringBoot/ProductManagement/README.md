# **Ứng Dụng Quản Lý Sản Phẩm - Spring Boot**

Đây là một dự án demo được xây dựng bằng **Spring Boot**, mục đích để minh họa các khái niệm cốt lõi và quan trọng trong việc phát triển ứng dụng web bằng Java, bao gồm: Spring MVC, Spring Data JPA, Spring Security, Quản lý Transaction và Xử lý Exception.

## 📸 Ảnh Chụp Màn Hình

Sau khi bạn đã khởi chạy ứng dụng thành công, bạn có thể xem các ảnh mẫu này trực tiếp từ trình duyệt bằng cách truy cập các đường dẫn sau:

* **Trang đăng nhập:** `http://localhost:8080/images/login_screen.png`
* **Giao diện Admin:** `http://localhost:8080/images/admin_screen.png`
* **Giao diện User:** `http://localhost:8080/images/user_screen.png`
* **Trang báo lỗi:** `http://localhost:8080/images/error.png`
* **H2 Console:** `http://localhost:8080/images/h2.png`

## ✨ Tính Năng Nổi Bật

* **Xác thực & Phân quyền (Security)**:
    * Sử dụng **Spring Security** để quản lý đăng nhập.
    * Phân chia vai trò rõ ràng:
        * `ADMIN`: Có toàn quyền thêm, sửa, xóa, và xem sản phẩm.
        * `USER`: Chỉ có quyền xem danh sách sản phẩm.
    * Trang đăng nhập tùy chỉnh.
* **Quản lý CRUD Sản phẩm**:
    * Thực hiện đầy đủ các thao tác Create, Read, Update, Delete đối với sản phẩm.
* **Quản lý Transaction an toàn**:
    * Áp dụng `@Transactional` để đảm bảo tính toàn vẹn dữ liệu khi thực hiện các thao tác phức tạp.
    * **Tính năng đặc biệt**: Cho phép người dùng `ADMIN` bật/tắt chế độ Transaction trên giao diện để trực tiếp kiểm tra và so sánh sự khác biệt, giúp hiểu sâu hơn về tầm quan trọng của Transaction.
* **Xử Lý Lỗi Toàn Cục (Exception Handling)**:
    * Sử dụng `@ControllerAdvice` để bắt các lỗi tập trung.
    * Hiển thị các trang lỗi thân thiện với người dùng (ví dụ: trang 404 khi không tìm thấy sản phẩm).
* **Giao diện người dùng**:
    * Xây dựng với **Thymeleaf** và **Bootstrap 5**, tích hợp với Spring Security để hiển thị/ẩn các chức năng dựa trên vai trò người dùng.

## 🔐 Phân Tích Bảo Mật: Authentication & Authorization

Ứng dụng sử dụng Spring Security để kiểm soát truy cập một cách chi tiết. Dưới đây là cách các quyền được phân chia:

### 1\. Truy Cập Công Khai (Không cần Authentication)

Đây là những tài nguyên mà **bất kỳ ai**, kể cả người dùng chưa đăng nhập, đều có thể truy cập. Chúng được cấu hình với `.permitAll()`.

* **`/login`**: Trang đăng nhập. Người dùng phải thấy được trang này để có thể tiến hành xác thực.
* **`/css/**`**: Tất cả các file CSS định dạng cho trang web. Giao diện cần được tải đúng cách ngay cả trên trang đăng nhập.
* **`/images/**`**: Tất cả các file hình ảnh.
* **`/js/**`**: Các file JavaScript (nếu có).
* **`/h2-console/**`**: Giao diện H2 Console. Mặc dù được phép truy cập, nó vẫn có cơ chế đăng nhập riêng và được bảo vệ khỏi tấn công CSRF.

### 2\. Truy Cập Cần Xác Thực (Cần Authentication)

Đây là những chức năng yêu cầu người dùng **phải đăng nhập** nhưng không yêu cầu một vai trò (role) cụ thể. Bất kỳ tài khoản nào (`USER` hay `ADMIN`) cũng có thể truy cập.

* **`/` (Trang chủ)**: Hiển thị danh sách sản phẩm. Đây là chức năng cơ bản mà mọi người dùng đã đăng nhập đều có thể sử dụng.

### 3\. Truy Cập Cần Phân Quyền (Cần Authorization - Vai trò ADMIN)

Đây là những chức năng nhạy cảm, có khả năng thay đổi dữ liệu của hệ thống. Chúng yêu cầu người dùng không chỉ đăng nhập mà còn phải có vai trò là `ADMIN`.

* **`/new`**: Hiển thị form để tạo sản phẩm mới.
* **`/edit/{id}`**: Hiển thị form để chỉnh sửa một sản phẩm đã có.
* **`/delete/{id}`**: Xóa một sản phẩm.
* **`/save`**: URL xử lý việc lưu (tạo mới hoặc cập nhật) sản phẩm.
* **`/deleteAllZeroQuantity`**: URL xử lý chức năng xóa hàng loạt để kiểm tra transaction.

Việc phân chia rạch ròi như vậy giúp đảm bảo chỉ những người có thẩm quyền mới có thể thực hiện các hành động quan trọng, trong khi vẫn giữ được trải nghiệm mượt mà cho người dùng thông thường.

## 🛠️ Công Nghệ Sử Dụng

* **Backend**: Java 17, Spring Boot 3.x
* **Frameworks**: Spring MVC, Spring Data JPA, Spring Security
* **Database**: H2 In-Memory Database (Cơ sở dữ liệu trong bộ nhớ)
* **Frontend**: Thymeleaf, Bootstrap 5
* **Build Tool**: Maven

## 🚀 Cài Đặt và Khởi Chạy

Để chạy dự án này trên máy của bạn, hãy làm theo các bước sau:

#### **Yêu cầu:**

* **JDK 17** hoặc phiên bản cao hơn.
* **Apache Maven**
* Một IDE hỗ trợ Java/Spring Boot (ví dụ: IntelliJ IDEA, Eclipse, VS Code).

#### **Các bước thực hiện:**

1.  **Clone repository về máy:**


2.  **Mở dự án bằng IDE:**
    * Mở IDE của bạn và chọn `File > Open...`
    * Trỏ đến thư mục gốc của dự án vừa clone (thư mục chứa file `pom.xml`).
    * Đợi IDE đồng bộ và tải về các dependencies của Maven.
3.  **Chạy ứng dụng:**
    * Tìm đến file `ProductManagementApplication.java` trong `src/main/java/com/example/practice`.
    * Chạy method `main` từ IDE (thường bằng cách nhấn vào biểu tượng ▶️ màu xanh).
    * Ứng dụng sẽ khởi động trên cổng `8080`.
4.  **Truy cập ứng dụng:**
    * Mở trình duyệt và truy cập: `http://localhost:8080`
    * Bạn sẽ được chuyển đến trang đăng nhập.

## 👤 Cách Sử Dụng

#### **Tài khoản đăng nhập:**

* **Admin:**
    * Username: `admin`
    * Password: `123`
* **User:**
    * Username: `user`
    * Password: `123`

#### **Truy cập H2 Console (Để xem database):**

* Đăng nhập với tài khoản `admin`.
* Địa chỉ: `http://localhost:8080/h2-console`
* **JDBC URL**: `jdbc:h2:mem:testdb`
* **User Name**: `sa`
* **Password**: (để trống)
* Nhấn `Connect`.

#### **Kiểm tra tính năng Transaction:**

1.  Đăng nhập với tài khoản `admin`.
2.  Thêm ít nhất **hai** sản phẩm có số lượng bằng `0`.
3.  Tại trang chủ, bạn sẽ thấy khu vực "Chức năng Admin".
4.  **Kịch bản 1 (An toàn):** Để nguyên dấu tick tại "Sử dụng Transaction" và nhấn nút "Kiểm tra xóa...". Bạn sẽ thấy thông báo lỗi (do code cố tình gây ra để kiểm thử), nhưng khi tải lại trang, các sản phẩm **vẫn còn nguyên** do transaction đã được rollback.
5.  **Kịch bản 2 (Không an toàn):** Bỏ dấu tick tại "Sử dụng Transaction" và nhấn nút. Bạn sẽ thấy lỗi, và khi tải lại trang, **một sản phẩm đã bị xóa mất**, minh họa cho việc không có transaction sẽ không thể hoàn tác khi có sự cố.