package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	// 実行するタスクの数
	private static final int NUM_OF_TASK = 5;

	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(NUM_OF_TASK);
		List<SampleTask> tasks = new ArrayList<SampleTask>();
		
		// タスクを準備
		for (int i = 0; i < NUM_OF_TASK; i++) {

			// タスクに番号を振る。
			int no = i;
			
			// タスクの処理時間を1〜10秒でランダムに決める。
			int lifeTime = (int)(Math.random() * 9 + 1);

			tasks.add(new SampleTask(no, lifeTime,latch));
		}
		
		// タスクを起動する。
		for (SampleTask task : tasks) {
			service.submit(task);
		}
		
		System.out.println("タスクの起動完了");
		
		try {
			// ラッチのカウントが0になるのを待機する。
			System.out.println("ラッチを使って完了を待機");
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("全てのタスクの処理が完了したため、処理を再開");
		
		// タスクの計算結果を集計する。
		int sum = 0;
		for (SampleTask task : tasks) {
			System.out.println("No." + task.getNo() + " result:" + task.getResult());
			sum += task.getResult();
		}
		System.out.println("summary:" + sum);
		service.shutdown();
	}
}
