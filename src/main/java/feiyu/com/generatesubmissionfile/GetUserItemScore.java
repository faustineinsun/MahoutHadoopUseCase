/**
 * @author feiyu
 */
package feiyu.com.generatesubmissionfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GetUserItemScore {
  public void getUserItemScore() {
    BufferedReader brOrg = null;
    BufferedReader brEvl = null;
    BufferedWriter bw = null;

    String line = "",outline = "";
    String inputOrgCFout = "src/main/resources/datasets/DB_output/part-r-00000";
    String inputEvaluation = "src/main/resources/datasets/task2_useritem_evaluation_data.tsv";
    String outputFile = "src/main/resources/datasets/Task2Submission.tsv";
    int prevUserId = -1, userID, itemID;
    double calculatedRate;
    String[] curLine;
    double defaultScore = 0; //@

    HashMap<Integer, String> hm = new HashMap<Integer, String>();
    HashMap<Integer, Double> hmPerUser = new HashMap<Integer, Double>();

    try {
      brOrg = new BufferedReader(new FileReader(inputOrgCFout));
      brEvl = new BufferedReader(new FileReader(inputEvaluation));
      bw = new BufferedWriter( new FileWriter(outputFile));

      // put <Integer userID, String itemRatePairs> into hashmap called hm
      while ((line = brOrg.readLine()) != null) {
        curLine = line.split("\t");
        hm.put(Integer.valueOf(curLine[0]), curLine[1]);
      }

      // add score to each <user,item> pair in task2_useritem_evaluation_data.tsv
      line = brEvl.readLine();
      bw.write("userID\titemID\tscore"+"\n");
      while ((line = brEvl.readLine()) != null) {
        curLine = line.split("\t");
        userID = Integer.valueOf(curLine[0]);
        itemID = Integer.valueOf(curLine[1]);

        if (hm.get(userID) == null) {
          calculatedRate = defaultScore; //@
        } else {
          // if current userID is different than previous userID, 
          //then put the user's all <item,rate> pairs into hashmap called hmPerUser;
          if (userID != prevUserId) { 
            String valueAry = hm.get(userID);
            valueAry = valueAry.substring(1, valueAry.length()-1);
            String[] itemRateAry = valueAry.split(",");

            hmPerUser = new HashMap<Integer, Double>();
            for (String itemRate: itemRateAry) {
              String[] pair = itemRate.split(":");
              int item = Integer.valueOf(pair[0]);
              double rate = Double.valueOf(pair[1]);
              hmPerUser.put(item, rate);
            }

            prevUserId = userID;
          }
          if (hmPerUser.get(itemID) == null) {
            calculatedRate = defaultScore; //@
          } else {
            calculatedRate = hmPerUser.get(itemID);
          }  
        }

        outline = userID + "\t" + itemID + "\t" + calculatedRate;
        bw.write(outline+"\n");
        System.out.println (outline);
      }
      brOrg.close();
      brEvl.close();
      bw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    GetUserItemScore getScore  = new GetUserItemScore();
    getScore.getUserItemScore();
  }
}
