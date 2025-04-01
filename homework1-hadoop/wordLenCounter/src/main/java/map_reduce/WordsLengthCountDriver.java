package map_reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/*
 * Implements the - Driver class
 * */
public class WordsLengthCountDriver extends Configured implements Tool {

    final static int NUMBER_OF_REDUCERS = 2;

    public int run(String[] args) throws Exception {
        Path inputDir = new Path(args[0]);
        Path output = new Path(args[1]);

        Configuration conf = getConf();

        Job job = Job.getInstance(conf);

        job.setJobName("WordLengthCount");

        FileInputFormat.addInputPath(job, inputDir);
        FileOutputFormat.setOutputPath(job, output);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputValueClass(TextOutputFormat.class);

        job.setJarByClass(WordsLengthCountDriver.class);

        // Init mapper
        job.setMapperClass(WordLengthMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Init reducer
        job.setReducerClass(WordLengthReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(NUMBER_OF_REDUCERS);

        if (job.waitForCompletion(true))
            return 0;

        return 1;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new WordsLengthCountDriver(), args);
        System.exit(res);
    }
}
