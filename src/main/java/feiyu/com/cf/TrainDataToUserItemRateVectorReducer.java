/**
 * @author feiyu
 * Step 2
 * Input: <6870, "1572 1"> <6870, "100 0">
 * Ouput: <6870, [1572:1]> not <6870, [1572:1, 100:0]>
 * @ Solve this problem later
 * RandomAccessSparseVector does not set one's rate to 0 even if it's rate is 0
 * eg: 16  {6006:1.0,5987:1.0,4581:1.0,2923:1.0,5841:1.0,2535:1.0}
 * rate = Double.parseDouble(itemRateAry[1]) == 1.0 ? 1.0: -1; 
 * -1 means dislike
 */
package feiyu.com.cf;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.Vector;

public class TrainDataToUserItemRateVectorReducer
extends Reducer<VarLongWritable, Text, VarLongWritable, VectorWritable> {

  public void reduce(VarLongWritable userID, Iterable<Text> itemRates, Context context)
      throws IOException, InterruptedException {
    Vector userVector = new RandomAccessSparseVector(Integer.MAX_VALUE);
    //double rate = -1;
    for (Text itemRate : itemRates) {
      String[] itemRateAry = itemRate.toString().split(" ");
      //rate = Double.parseDouble(itemRateAry[1]) == 1.0 ? 1.0: -1; 

      userVector.set(Integer.valueOf(itemRateAry[0]), Double.parseDouble(itemRateAry[1]));
    }
    context.write(userID, new VectorWritable(userVector));
  }
}
