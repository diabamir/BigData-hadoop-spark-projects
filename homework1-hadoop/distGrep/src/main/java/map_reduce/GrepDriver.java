package map_reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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
public class GrepDriver extends Configured implements Tool {

    final static int NUMBER_OF_REDUCERS = 2;

    public int run(String[] args) throws Exception {
        Path inputDir = new Path(args[0]);
        Path output = new Path(args[1]);
        String grepPhrase = args[2];

        Configuration conf = getConf();
        conf.set("GrepPhrase", grepPhrase);

        Job job = Job.getInstance(conf);

        job.setJobName("DistributedGrep");

        FileInputFormat.addInputPath(job, inputDir);
        FileOutputFormat.setOutputPath(job, output);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputValueClass(TextOutputFormat.class);

        job.setJarByClass(GrepDriver.class);

        
        job.setMapperClass(DistributedGrepMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setNumReduceTasks(NUMBER_OF_REDUCERS);

        if (job.waitForCompletion(true))
            return 0;

        return 1;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new GrepDriver(), args);
        System.exit(res);
    }
}
