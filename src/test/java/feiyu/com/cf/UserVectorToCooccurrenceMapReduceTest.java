/**
 * @author feiyu
 */
package feiyu.com.cf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.junit.Before;
import org.junit.Test;

public class UserVectorToCooccurrenceMapReduceTest {
  private MapDriver<VarLongWritable, VectorWritable, IntWritable, IntWritable> mapDriver;
  private ReduceDriver<IntWritable, IntWritable, IntWritable, VectorWritable> reduceDriver;
  private MapReduceDriver<VarLongWritable, VectorWritable, IntWritable, IntWritable, IntWritable, VectorWritable> mapReduceDriver;

  @Before
  public void init(){
    ToItemCooccurrenceMapper mapper = new ToItemCooccurrenceMapper();
    ToItemCooccurrenceReducer reducer = new ToItemCooccurrenceReducer();
    mapDriver = MapDriver.newMapDriver(mapper);
    reduceDriver = ReduceDriver.newReduceDriver(reducer);
    mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    //mapReduceDriver = new MapReduceDriver<LongWritable, Text,VarLongWritable, Text, VarLongWritable, VectorWritable>(mapper,reducer);
  }

  @Test
  public void testMapper() throws IOException{   
    Vector vector = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vector.set(6006, 1.0);vector.set(5987, 1.0);vector.set(4581,1.0);
    mapDriver.withInput(new VarLongWritable(16),new VectorWritable(vector)) 
    .withOutput(new IntWritable(6006), new IntWritable(6006))
    .withOutput(new IntWritable(6006), new IntWritable(5987))
    .withOutput(new IntWritable(6006), new IntWritable(4581))
    .withOutput(new IntWritable(5987), new IntWritable(6006))
    .withOutput(new IntWritable(5987), new IntWritable(5987))
    .withOutput(new IntWritable(5987), new IntWritable(4581))
    .withOutput(new IntWritable(4581), new IntWritable(6006))
    .withOutput(new IntWritable(4581), new IntWritable(5987))
    .withOutput(new IntWritable(4581), new IntWritable(4581))
    .runTest();
  }
  @Test
  public void testReducer() throws IOException{   
    List<IntWritable> list = new ArrayList<IntWritable>();
    list.add(new IntWritable(6006));
    list.add(new IntWritable(5987));
    list.add(new IntWritable(4581));
    Vector vector = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vector.set(5987, 1.0);vector.set(4581, 1.0);vector.set(6006,1.0);
    reduceDriver.withInput(new IntWritable(6006), list)
    .withOutput(new IntWritable(6006), new VectorWritable(vector))
    .runTest();
  }

  @Test
  public void testMapReduce() throws RuntimeException, IOException{
    Vector vectorInput = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vectorInput.set(6006, 1.0);vectorInput.set(5987, 1.0);vectorInput.set(4581,1.0);

    Vector vectorOuput = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vectorOuput.set(5987, 1.0);vectorOuput.set(4581, 1.0);vectorOuput.set(6006,1.0);

    mapReduceDriver.withInput(new VarLongWritable(16),new VectorWritable(vectorInput)) 
    .withOutput(new IntWritable(4581), new VectorWritable(vectorOuput))
    .withOutput(new IntWritable(5987), new VectorWritable(vectorOuput))
    .withOutput(new IntWritable(6006), new VectorWritable(vectorOuput))
    .runTest();
    // Input : 16  {6006:1.0,5987:1.0,4581:1.0}
    // Output : 4581, {6006:1.0,5987:1.0,4581:1.0}
    // 5987, {6006:1.0,5987:1.0,4581:1.0}
    // 6006, {6006:1.0,5987:1.0,4581:1.0}
  }
}
