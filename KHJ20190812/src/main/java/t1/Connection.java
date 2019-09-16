package t1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Connection extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("시작! :)");
		
		
		String localStr = "D:\\input";
		String hadoopStr = "/input";
		Path localPath = new Path(localStr);
		Path hadoopPath = new Path(hadoopStr);
		
		
		// local info
		Configuration localConf = new Configuration();
		FileSystem localSystem = FileSystem.getLocal(localConf);
		FileStatus[] fileList = localSystem.listStatus(localPath);
		
		// hadoop info
		Configuration hadoopConf = new Configuration();
		hadoopConf.set("fs.defaultFS", "hdfs://192.168.3.91:9000");
		FileSystem hadoopSystem = FileSystem.get(hadoopConf);
		if(hadoopSystem.exists(new Path("/output"))){
			hadoopSystem.delete(new Path("/output"), true);
		}
		
		// local 파일 hadoop으로 이동
		for(int i = 0; i < fileList.length; i++) {
			System.out.println("input 파일: "+fileList[i].getPath().getName());
			String path = localStr + "/" + fileList[i].getPath().getName();
			String path2 = hadoopStr + "/" + fileList[i].getPath().getName();
			System.out.println("local 데이터 주소 : " + path);
			System.out.println("hadoop 데이터 주소 : " + path2);
			
			FSDataInputStream fsis = localSystem.open(new Path(path));
			FSDataOutputStream fsos = hadoopSystem.create(new Path(path2));
			
			int byteRead = 0;
			while ((byteRead = fsis.read()) > 0) {
				fsos.write(byteRead);					
			}
			
			fsis.close();
			fsos.close();
		}
		
	
		// Job  > map : reducer > 
		try {
			Job job = Job.getInstance(hadoopConf, "test");

			job.setJarByClass(Connection.class);
			job.setMapperClass(JobMap.class);
			job.setReducerClass(JobReduce.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			job.setNumReduceTasks(1);
			FileInputFormat.addInputPath(job, new Path("/input"));
		    FileOutputFormat.setOutputPath(job, new Path("/output"));
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = response.getWriter();
		String targetPath = "/output/part-r-00000";
		if(hadoopSystem.exists(new Path(targetPath))){			
			FSDataInputStream fsis = hadoopSystem.open(new Path(targetPath));
			int byteRead = 0;
			while((byteRead = fsis.read()) > 0) { 
				pw.write(byteRead);
			}
			return;
		}
		pw.write("Hello Hadoop!");
	}

}
