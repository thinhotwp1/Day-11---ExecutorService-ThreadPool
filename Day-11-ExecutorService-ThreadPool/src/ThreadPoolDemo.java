import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 1. Tạo "công việc" (Task)
class Task implements Runnable {
    private int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("BẮT ĐẦU task " + taskId + " bởi thread: " + Thread.currentThread().getName());
        try {
            // Giả lập công việc tốn thời gian
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("KẾT THÚC task " + taskId + " bởi thread: " + Thread.currentThread().getName());
    }
}

public class ThreadPoolDemo {
    public static void main(String[] args) {
        
        // 2. Tạo một Thread Pool với 3 thread "công nhân"
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("Đã tạo Thread Pool với 3 thread.");

        // nếu dùng thì sẽ tạo ra số lượng thread bằng số lượng công việc, ở đây là 10
//        ExecutorService executor = Executors.newCachedThreadPool();

        // 3. Nộp 10 task cho pool
        for (int i = 1; i <= 10; i++) {
            Runnable task = new Task(i);
            executor.execute(task);
            System.out.println("Start task " + i);
        }

        // 4. BẮT BUỘC phải shutdown pool sau khi nộp xong
        System.out.println("Đã chạy hết task. Gọi shutdown().");
        executor.shutdown(); // Ngừng nhận task mới

        try {
            // Chờ tối đa 1 minute để các task hoàn thành
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Một số task chưa xong, gọi shutdownNow().");
                executor.shutdownNow(); // Ép tắt nếu chờ quá lâu
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Tất cả thread trong pool đã kết thúc. Chương trình thoát.");
    }
}