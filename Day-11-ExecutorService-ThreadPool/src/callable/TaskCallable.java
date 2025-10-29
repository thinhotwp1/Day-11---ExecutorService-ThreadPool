package callable;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<String> {

    private int taskId;

    public TaskCallable(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws Exception {
        System.out.println("BẮT ĐẦU task " + taskId + " bởi thread: " + Thread.currentThread().getName());
        try {
            // Giả lập công việc tốn thời gian
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hoàn thành task " + taskId + " bởi thread: " + Thread.currentThread().getName();
    }
}
