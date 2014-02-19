/**
 * @author feiyu
 * Step 1 <36, “6870 1572 1”> <37, “6870 100 0”> 
 * to <6870, "1572 1"> <6870, "100 0">
 */
package feiyu.com.cf;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.math.VarLongWritable;

public final class TrainDataToUserTextMapper extends Mapper<LongWritable, Text, VarLongWritable, Text> {

  @Override
  protected void map(LongWritable key, Text rowText, Context context)
      throws IOException, InterruptedException {
    String[] columns = rowText.toString().split("\t");

    VarLongWritable userID = new VarLongWritable(Long.parseLong(columns[0]));
    Text itemIDRate = new Text(columns[1] + " " + columns[2]);

    context.write(userID, itemIDRate);
  }
}