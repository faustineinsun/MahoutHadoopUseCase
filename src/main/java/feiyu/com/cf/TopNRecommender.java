package feiyu.com.cf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.mahout.math.VarLongWritable;

public class TopNRecommender {
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = new Job(conf, "TopNRecommender");
    job.setJarByClass(TopNRecommender.class);
    
    job.setOutputKeyClass(VarLongWritable.class);
    job.setOutputValueClass(Text.class);
    
    job.setMapperClass(TrainDataToUserTextMapper.class);
    job.setReducerClass(TrainDataToUserItemRateVectorReducer.class); 
    
    FileInputFormat.addInputPath(job, new Path("src/main/resources/datasets/DBbook_train_binary.tsv"));
    FileOutputFormat.setOutputPath(job, new Path("src/main/resources/datasets/TrainDataToUserVector_Out"));

    job.waitForCompletion(true);
    //System.exit(job.waitForCompletion(true) ? 0 : 1);
    //JobClient.runJob(job);
  }
}

