package map_reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;

import java.io.IOException;
import java.util.stream.Collectors;

public class WordLengthMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

    static final String REGEX = "\\s+";
    static final int COUNTER = 1;

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context) throws IOException, InterruptedException {
        List<Integer> lens = Arrays.stream(value.toString().split(REGEX))
        .map(String::length).collect(Collectors.toList());
        for(int len : lens){
            context.write(new IntWritable(len), new IntWritable(COUNTER));
        }
    }

}
