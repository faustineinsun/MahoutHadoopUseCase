/**
 * @author feiyu 
 * Main function
 */
package feiyu.com.cf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.VectorWritable;

public class TopNRecommender extends AbstractJob {  

  @Override
  public int run(String[] args) throws Exception {
    
    //addInputOption();
    //addOutputOption();

    Map<String, List<String>> parsedArgs = parseArguments(args);
    if (parsedArgs == null) {
      return -1;
    }
    
    Path prefsFile = new Path("src/main/resources/datasets/DBbook_train_binary.tsv");//getInputPath();
    Path averagesOutputPath = new Path("src/main/resources/datasets/TrainDataToUserVector_Out"); //new Path(parsedArgs.get("--tempDir"));
    Path outputPath = new Path("src/main/resources/datasets/UserVectorToCooccurrenceMapper");//getOutputPath();
    
    FileSystem hdfs = FileSystem.get(new Configuration());
    if (hdfs.exists(averagesOutputPath))
      hdfs.delete(averagesOutputPath, true);
    if (hdfs.exists(outputPath))
      hdfs.delete(outputPath, true);

    AtomicInteger currentPhase = new AtomicInteger();

    if (shouldRunNextPhase(parsedArgs, currentPhase)) {
      Job prefsToDiffsJob = prepareJob(prefsFile, // Path inputPath
                                       averagesOutputPath, // Path outputPath
                                       TextInputFormat.class, // Class<? extends InputFormat> inputFormat
                                       TrainDataToUserTextMapper.class,  // Class<? extends Mapper> mapper
                                       VarLongWritable.class, // Class<? extends Writable> mapperKey
                                       Text.class, // Class<? extends Writable> mapperValue
                                       TrainDataToUserItemRateVectorReducer.class, // Class<? extends Reducer> reducer
                                       VarLongWritable.class, // Class<? extends Writable> reducerKey
                                       VectorWritable.class, //Class<? extends Writable> reducerValue
                                       SequenceFileOutputFormat.class); // Class<? extends OutputFormat> outputFormat
      prefsToDiffsJob.waitForCompletion(true);
    }


    if (shouldRunNextPhase(parsedArgs, currentPhase)) {
      Job diffsToAveragesJob = prepareJob(averagesOutputPath, // Path inputPath
                                          outputPath, // Path outputPath
                                          SequenceFileInputFormat.class, // Class<? extends InputFormat> inputFormat
                                          ToItemCooccurrenceMapper.class, // Class<? extends Mapper> mapper
                                          IntWritable.class, // Class<? extends Writable> mapperKey
                                          IntWritable.class,  // Class<? extends Writable> mapperValue
                                          ToItemCooccurrenceReducer.class, // Class<? extends Reducer> reducer
                                          IntWritable.class, // Class<? extends Writable> reducerKey
                                          IntWritable.class, //Class<? extends Writable> reducerValue
                                          TextOutputFormat.class); // Class<? extends OutputFormat> outputFormat
      FileOutputFormat.setOutputCompressorClass(diffsToAveragesJob, GzipCodec.class);
      diffsToAveragesJob.waitForCompletion(true);
    }
    return 0;
  }

  public static void main(String[] args) throws Exception {
    ToolRunner.run(new Configuration(), new TopNRecommender(), args);
  }
}
