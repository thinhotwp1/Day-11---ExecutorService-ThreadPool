package runnable;

// Tạo "công việc" (Task)
class TaskRunnable implements Runnable {
    private int taskId;

    public TaskRunnable(int taskId) {
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
        System.out.println("Hoàn thành task " + taskId + " bởi thread: " + Thread.currentThread().getName());
    }
}