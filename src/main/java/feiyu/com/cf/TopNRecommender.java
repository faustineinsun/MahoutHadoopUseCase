package feiyu.com.cf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.mahout.math.VarLongWritable;

public class TopNRecommender {
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = new Job(conf, "TopNRecommender");
    job.setJarByClass(TopNRecommender.class);
    
    Path inputPath = new Path("src/main/resources/datasets/DBbook_train_binary.tsv");
    Path outputDir = new Path("src/main/resources/datasets/TrainDataToUserVector_Out");
    
    job.setMapperClass(TrainDataToUserTextMapper.class);
    job.setReducerClass(TrainDataToUserItemRateVectorReducer.class); 
    job.setNumReduceTasks(1);
    
    job.setOutputKeyClass(VarLongWritable.class);
    job.setOutputValueClass(Text.class);
    
    FileInputFormat.addInputPath(job, inputPath);
    job.setInputFormatClass(TextInputFormat.class);
    
    FileOutputFormat.setOutputPath(job, outputDir);
    job.setOutputFormatClass(TextOutputFormat.class);
    
    //Delet the output file if exist
    FileSystem hdfs = FileSystem.get(conf);
    if (hdfs.exists(outputDir))
      hdfs.delete(outputDir, true);
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);
    //JobClient.runJob(job);
  }
}

