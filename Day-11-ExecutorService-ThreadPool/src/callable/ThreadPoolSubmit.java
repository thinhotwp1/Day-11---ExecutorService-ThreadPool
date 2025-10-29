package callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        // nếu dùng newCachedThreadPool() thì sẽ tạo ra số lượng thread bằng số lượng công việc, ở đây là 10
//        ExecutorService executor = Executors.newCachedThreadPool();

        // Còn 1 cách phổ biến nữa là tạo duy nhất 1 thread, thường dùng để tạo 1 luồng riêng không ảnh hưởng tới luồng chính
//        ExecutorService executors = Executors.newSingleThreadExecutor();

        // 1. Tạo một Thread Pool với 3 thread "công nhân"
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("Đã tạo Thread Pool với 3 thread.");
        // 2. Tạo một danh sách để chứa các "biên lai" (Future)
        List<Future<String>> futureList = new ArrayList<>();

        // 3. Nộp 10 task cho pool
        for (int i = 1; i <= 10; i++) {
            Callable<String> task = new TaskCallable(i);
            System.out.println("Start task " + i);
            futureList.add(executor.submit(task));
        }
        System.out.println("Đã nộp xong 10 task! (Các thread pool đang chạy song song)");

        // 4. SAU KHI NỘP HẾT, MỚI ĐI ĐÒI KẾT QUẢ
        System.out.println("Bắt đầu lấy kết quả...");
        for (Future<String> future : futureList) {
            // Lần lượt chờ từng cái một
            // .get() ở đây vẫn block, nhưng task đã chạy song song từ trước rồi
            String result = future.get();
            System.out.println("Nhận được kết quả: " + result);
        }

        // 5. BẮT BUỘC phải shutdown pool sau khi nộp xong
        System.out.println("Đã nhận hết task. Gọi shutdown().");
        executor.shutdown(); // Ngừng nhận task mới

        System.out.println("Tất cả thread trong pool đã kết thúc. Chương trình thoát.");
        long end = System.currentTimeMillis();
        System.out.println("time " + (end - start) + " ms");
    }
}
