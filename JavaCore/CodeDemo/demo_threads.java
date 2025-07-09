// Demo: Multithreading trong Java - đếm số và in chữ song song với synchronized
public class ThreadDemo {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        // Tạo luồng bằng cách kế thừa Thread
        Thread t1 = new NumberPrinter(counter);

        // Tạo luồng bằng cách cài đặt Runnable
        Thread t2 = new Thread(new CharPrinter(counter));

        // Khởi động cả 2 luồng
        t1.start();
        t2.start();

        // Dùng join để đợi cả 2 luồng kết thúc trước khi in xong
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ca 2 luong da hoan thanh, tong count: " + counter.getCount());
    }
}

class SharedCounter {
    private int count = 0;

    // Phương thức tăng giá trị được đồng bộ hóa để tránh lỗi race condition
    public synchronized void increment(String threadName) {
        count++;
        System.out.println(threadName + " da tang count len: " + count);
    }

    public synchronized int getCount() {
        return count;
    }
}

class NumberPrinter extends Thread {
    private SharedCounter counter;

    public NumberPrinter(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("So: " + i);
            counter.increment("[Thread Number]");
            try {
                Thread.sleep(500); // tạm dừng 0.5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CharPrinter implements Runnable {
    private SharedCounter counter;

    public CharPrinter(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        char c = 'A';
        for (int i = 0; i < 5; i++) {
            System.out.println("Chu: " + c++);
            counter.increment("[Thread Char]");
            try {
                Thread.sleep(700); // tạm dừng 0.7 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


