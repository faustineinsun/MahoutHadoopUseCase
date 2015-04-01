###Mahout Hadoop Use Case    

**Purpose of this program**:   
- ~~[Hadoop 2.2.0 is incompatible with mahout 0.8 problem](https://issues.apache.org/jira/browse/MAHOUT-1329)~~ (problem was resolved on 25/Feb/14), I would like to figure out an alternative method of running use case on Hadoop 2.2.0 with Mahout 0.8 collaborative filtering library         
- Run hadoop program on Eclipse and Yarn    

---

**Steps**:
- [Setup Hadoop Yarn, hadoop 2.2.0](http://faustineinsun.blogspot.com/2014/01/setup-hadoop-220-yarn-on-single-node.html)
- `sbin/start_classicCF.sh`   
    - This script is used to download the dataset,    
    - start and put data onto the hadoop HDFS,        
    - build a runnable Jar of this project  
- [Set up eclipse plugin for hadoop 2.2.0 (Mac OS X 10.9.1)](http://faustineinsun.blogspot.com/2014/01/setup-eclipse-plugin-for-hadoop-220-mac.html)    
- Import this Maven program into eclipse    
- In eclipse, open the file `RecommenderJob.java` in folder `feiyu.com.cfex`, and input the arguments as follows, later click `Run As -> Run on Hadoop`  

```
--input src/main/resources/datasets/preferenceData \\
--output src/main/resources/datasets/DB_output \\
--similarityClassname SIMILARITY_COOCCURRENCE \\
--usersFile src/main/resources/datasets/userid.txt \\
--itemsFile src/main/resources/datasets/itemid.txt \\
--filterFile src/main/resources/datasets/filterUserItemPairs.txt \\
--numRecommendations 8171 \\
--booleanData true \\
--outputPathForSimilarityMatrix src/main/resources/datasets/opfsm \\
--tempDir src/main/resources/datasets/temp
```

---    

`sbin/cleanUpBeforeCommit.sh`    
