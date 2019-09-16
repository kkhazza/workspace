package t1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("파라메터 값 2개 필요합니다.");
			System.exit(1);
		}
		System.out.println("정상 동작 준비 완료!");
		
		try {
			// 설정 정보 객체 생성
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "test");
			// 실행 대상 클래스 지정
			job.setJarByClass(Main.class);
			
			// 1. 쪼개는 설정 > Map
			job.setMapperClass(JobMap.class);
			
			// 2. 결합 설정 > Reduce
			job.setReducerClass(JobReduce.class);
			
			// 3. 입력과 출력 대상 설정 > Input & Ouput
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			// 4. 테스크
			job.setNumReduceTasks(1);
			
			// 5. 경로 설정
			FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			// 실행 종료 처리
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
