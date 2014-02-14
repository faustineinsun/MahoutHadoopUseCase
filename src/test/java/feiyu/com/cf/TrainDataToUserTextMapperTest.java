package feiyu.com.cf;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.mahout.math.VarLongWritable;
import org.junit.Before;
import org.junit.Test;

public class TrainDataToUserTextMapperTest {
  private Mapper<LongWritable, Text, VarLongWritable, Text> mapper;
  private MapDriver<LongWritable, Text, VarLongWritable, Text> driver;

  @Before
  public void init(){
    mapper = new TrainDataToUserTextMapper();
    driver = new MapDriver<LongWritable, Text, VarLongWritable, Text>(mapper);
  }

  @Test
  public void test() throws IOException{   
    String line = "6870\t1572\t1";
    driver.withInput(new LongWritable(1),new Text(line)) 
    .withOutput(new VarLongWritable(6870), new Text("1572 1"))
    .runTest();
  }
}
