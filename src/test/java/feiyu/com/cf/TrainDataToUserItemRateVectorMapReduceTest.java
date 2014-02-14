package feiyu.com.cf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.junit.Before;
import org.junit.Test;

public class TrainDataToUserItemRateVectorMapReduceTest {
  private MapDriver<LongWritable, Text, VarLongWritable, Text> mapDriver;
  private ReduceDriver<VarLongWritable, Text, VarLongWritable, VectorWritable> reduceDriver;
  private MapReduceDriver<LongWritable, Text,VarLongWritable, Text, VarLongWritable, VectorWritable> mapReduceDriver;

  @Before
  public void init(){
    TrainDataToUserTextMapper mapper = new TrainDataToUserTextMapper();
    TrainDataToUserItemRateVectorReducer reducer = new TrainDataToUserItemRateVectorReducer();
    mapDriver = MapDriver.newMapDriver(mapper);
    reduceDriver = ReduceDriver.newReduceDriver(reducer);
    mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    //mapReduceDriver = new MapReduceDriver<LongWritable, Text,VarLongWritable, Text, VarLongWritable, VectorWritable>(mapper,reducer);
  }

  @Test
  public void testMapper() throws IOException{   
    mapDriver.withInput(new LongWritable(),new Text("6870\t1572\t1")) 
    .withOutput(new VarLongWritable(6870), new Text("1572 1"))
    .runTest();
  }

  @Test
  public void testReducer() throws IOException{   
    List<Text> texts = new ArrayList<Text>();
    texts.add(new Text("1572 1"));
    texts.add(new Text("100 0"));

    Vector vector = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vector.set(1572, 1);
    vector.set(100, -1);

    reduceDriver.withInput(new VarLongWritable(6870), texts)
    .withOutput(new VarLongWritable(6870), new VectorWritable(vector))
    .runTest();
  }

  @Test
  public void testMapReduce() throws RuntimeException, IOException{
    String line = "6870\t1572\t1";

    Vector vector = new RandomAccessSparseVector(Integer.MAX_VALUE);
    vector.set(1572, 1);

    mapReduceDriver.withInput(new LongWritable(1),new Text(line)) 
    .withOutput(new VarLongWritable(6870), new VectorWritable(vector))
    .runTest();
  }
}
