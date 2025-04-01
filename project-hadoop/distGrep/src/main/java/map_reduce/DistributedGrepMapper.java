package map_reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.util.Arrays;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DistributedGrepMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {

        String grepPhrase = context.getConfiguration().get("GrepPhrase", "");
        String filename = ((FileSplit) context.getInputSplit()).getPath().toString();

        List<String> containGrep = Arrays.stream(value.toString().split("\\n"))
        .filter(line -> line.contains(grepPhrase)).collect(Collectors.toList());

        for(String line : containGrep){
            // emit (file_name, line)
            context.write(
                new Text(String.format("file %s",filename)),
                new Text(value.toString())
            );
        }
    }
}
