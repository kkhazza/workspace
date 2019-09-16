package com.java.hdfs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper<(입력키<행번호> : 입력값<행의글자>) , (출력키<글자> : 출력값<1>)>
public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	// 출력 키 변수
	protected Text textKey = new Text();
	// 출력 값 변수
	protected IntWritable intValue = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		// 년도 월별 항공 출발지연시간 > 150,000시간 이하인것만 DepDelay[15]->출발지연시간,분으로표현
		
		String[] values = value.toString().split(",");
		
		String str_Y = values[0]; // 년도
		String str_M = values[1]; // 월별
		String str_DD = values[15]; // 출발 지연 시간, 분으로 표현
		
		if(str_DD.equals("NA")) {
			str_DD = "0";
		} 
		
		Text textKey = new Text();
		textKey.set(str_Y + "\t" + str_M + "\t"); 
		IntWritable intValue = new IntWritable(Integer.parseInt(str_DD));
		context.write(textKey, intValue);		
		
//		if(Integer.parseInt(str_DD) <= 150000) {
//			textKey.set(str_Y + "\t" + str_M + "\t"); 
//			IntWritable intValue = new IntWritable(Integer.parseInt(str_DD));
//			context.write(textKey, intValue);
//		} 
	}
	
}
