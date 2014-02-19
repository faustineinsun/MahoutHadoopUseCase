/**
 * @author feiyu
 */
package feiyu.com.cf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.junit.Before;
import org.junit.Test;

public class TrainDataToUserItemRateVectorReducerTest {
  private Reducer<VarLongWritable, Text, VarLongWritable, VectorWritable> reducer;
  private ReduceDriver<VarLongWritable, Text, VarLongWritable, VectorWritable> driver;

  @Before
  public void init(){
    reducer = new TrainDataToUserItemRateVectorReducer();
    driver = new ReduceDriver<VarLongWritable, Text, VarLongWritable, VectorWritable>(reducer);
  }

  @Test
  public void test() throws IOException{
    List<Text> texts = new ArrayList<Text>();
    texts.add(new Text("1572 1"));
    texts.add(new Text("100 0"));

    Vector vector = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vector.set(1572, 1);
    //vector.set(100, -1);

    driver.withInput(new VarLongWritable(6870), texts)
    .withOutput(new VarLongWritable(6870), new VectorWritable(vector))
    .runTest();
  }
}
