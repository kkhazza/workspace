package t2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Main {
	
	public static String localStr = "/root/data";
	public static String hadoopStr = "/input";
	public static Path localPath = new Path(localStr);
	public static Path hadoopPath = new Path(hadoopStr);

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		try {
			FileSystem localSystem = FileSystem.getLocal(conf);
			FileSystem hadoopSystem = FileSystem.get(conf);
			FileStatus[] fileList = localSystem.listStatus(localPath);
			
//			System.out.println("fileList : " + fileList.length);
			
			for(int i = 0; i < 1; i++) {
				String path = localStr + "/" + fileList[i].getPath().getName();
				String path2 = hadoopStr + "/" + fileList[i].getPath().getName();
				System.out.println("local : " + path);
				System.out.println("hadoop : " + path2);
				
				FSDataInputStream fsis = localSystem.open(new Path(path));
				FSDataOutputStream fsos = hadoopSystem.create(new Path(path2));
				
				int byteRead = 0;
				while ((byteRead = fsis.read()) > 0) {
					fsos.write(byteRead);					
				}
				
				fsis.close();
				fsos.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
