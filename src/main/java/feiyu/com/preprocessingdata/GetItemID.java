/**
 * @author feiyu
 */
package feiyu.com.preprocessingdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GetItemID {
  public static void main(String[] args) {
    BufferedReader br = null;
    BufferedWriter bw = null;
    String line = "",outline = "";
    String[] items;
    String inputFile = "src/main/resources/datasets/DBbook_Items_DBpedia_mapping.tsv";
    String outputFile = "src/main/resources/datasets/itemid.txt";
    try {
      br = new BufferedReader(new FileReader(inputFile));
      bw = new BufferedWriter( new FileWriter(outputFile));
      line = br.readLine();
      while ((line = br.readLine()) != null) {
        items = line.split("\t");
        outline = items[0];
        System.out.println (outline);
        bw.write(outline+"\n");
      }
      br.close();
      bw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Done! File place: " + outputFile);
  }
}
