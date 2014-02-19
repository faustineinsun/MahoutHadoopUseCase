/**
 * Step 4
 * @author feiyu
 */
package feiyu.com.cf;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

public class ToItemCooccurrenceReducer extends Reducer<IntWritable, IntWritable, IntWritable, VectorWritable> {
  public void reduce(IntWritable itemIdx1, Iterable<IntWritable> itemIdx2Iterable, Context context)
      throws IOException, InterruptedException {
    Vector row = new RandomAccessSparseVector(Integer.MAX_VALUE);
    for (IntWritable intWritable : itemIdx2Iterable) {
      int itemIdx2 = intWritable.get();
      row.set(itemIdx2, row.get(itemIdx2) + 1.0);
    }
    context.write(itemIdx1, new VectorWritable(row));
  }
}
