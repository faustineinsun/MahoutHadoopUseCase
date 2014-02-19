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

public class ReformatUserItemRating {
  public static void main(String[] args) {
    BufferedReader br = null;
    BufferedWriter bw = null;
    String line = "",outline = "";
    String inputFile = "src/main/resources/datasets/DBbook_train_binary.tsv";
    String outputFile = "src/main/resources/datasets/train.txt";
    try {
      br = new BufferedReader(new FileReader(inputFile));
      bw = new BufferedWriter( new FileWriter(outputFile));
      line = br.readLine();
      while ((line = br.readLine()) != null) {
        outline = line.replaceAll("\t", ",");
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
