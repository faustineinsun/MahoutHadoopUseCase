Hadoop 2.2.0 is incompatible with mahout 0.8  
sol -> https://issues.apache.org/jira/browse/MAHOUT-1329  
for hadoop version 1.2.1: `mvn clean package`  
for hadoop version 2.2.0: `mvn clean package -Dhadoop2.version=2.2.0`  

Name of distributed similarity measures class  
- SIMILARITY_COOCCURRENCE  
- SIMILARITY_LOGLIKELIHOOD  
- SIMILARITY_TANIMOTO_COEFFICIENT   
- SIMILARITY_CITY_BLOCK  
- SIMILARITY_COSINE  
- SIMILARITY_PEARSON_CORRELATION  
- SIMILARITY_EUCLIDEAN_DISTANCE  

