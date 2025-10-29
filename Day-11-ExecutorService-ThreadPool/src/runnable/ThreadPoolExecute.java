package runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecute {
    public static void main(String[] args) {
        // nếu dùng newCachedThreadPool() thì sẽ tạo ra số lượng thread bằng số lượng công việc, ở đây là 10
//        ExecutorService executor = Executors.newCachedThreadPool();

        // Còn 1 cách phổ biến nữa là tạo duy nhất 1 thread, thường dùng để tạo 1 luồng riêng không ảnh hưởng tới luồng chính
//        ExecutorService executors = Executors.newSingleThreadExecutor();

        // 1. Tạo một Thread Pool với 3 thread "công nhân"
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("Đã tạo Thread Pool với 3 thread.");

        // 2. Nộp 10 task cho pool
        for (int i = 1; i <= 10; i++) {
            Runnable task = new TaskRunnable(i);
            executor.execute(task);
            System.out.println("Start task " + i);
        }

        // 3. BẮT BUỘC phải shutdown pool sau khi nộp xong
        executor.shutdown(); // Ngừng nhận task mới

        System.out.println("Các thread trong pool đã được giao task, main thread đợi cho các thread hoàn thành, sẽ thoát chương trình sau khi các thread shutdown ...");
    }
}
