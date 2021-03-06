package t1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// Mapper<(입력키<행번호> : 입력값<행의글자>) , (출력키<글자> : 출력값<1>)>
public class JobMap extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		String[] values = value.toString().split(",");
		
		String str_m = values[1];
		String str_UC = values[8];
		
		String str_T = values[11];
		
		if(str_T.equals("NA")) {
			str_T = "0";
		} 
		
		Text textKey = new Text();
		textKey.set(str_m + str_UC); 

		IntWritable intValue = new IntWritable(Integer.parseInt(str_T));
		context.write(textKey, intValue);
	}
}
