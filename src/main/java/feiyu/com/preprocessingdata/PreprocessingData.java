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
import java.util.HashSet;
import java.util.Set;

public class PreprocessingData {
  public void reformatUserItemRating() {
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
        //System.out.println (outline);
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

  public void getUserID() {
    BufferedReader br = null;
    BufferedWriter bw = null;
    String line = "",outline = "";
    String[] items;
    String inputFile = "src/main/resources/datasets/task2_useritem_evaluation_data.tsv";
    String outputFile = "src/main/resources/datasets/userid.txt";
    Set<String> hm = new HashSet<String>();
    try {
      br = new BufferedReader(new FileReader(inputFile));
      line = br.readLine();
      while ((line = br.readLine()) != null) {
        items = line.split("\t");
        hm.add(items[0]);
      }

      bw = new BufferedWriter( new FileWriter(outputFile));
      for (String s : hm) {
        outline = s;
        //System.out.println (outline);
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

  public void getItemID () {
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
        //System.out.println (outline);
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

  public static void main(String[] args) {
    PreprocessingData pd = new PreprocessingData();
    pd.getUserID();
    pd.getItemID();
    pd.reformatUserItemRating();
  }
}
