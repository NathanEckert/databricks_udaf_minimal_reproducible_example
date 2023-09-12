# databricks_udaf_minimal_reproducible_example

Try to follow https://docs.databricks.com/en/sql/language-manual/sql-ref-functions-udf-aggregate.html#language-java

- Compile project by running `mvn clean install`
- Create a test table on your Databricks environment based on `create_table.sql`
- Register the UDAFs on Databricks, in a notebook:
  - Attach the notebook to a cluster
  - Click on "File"/"Open DBFS file browser"
  - Click on Upload, then upload your JAR.
  - In a SQL cell, run the command `CREATE FUNCTION myAverage AS 'MyAverage' USING JAR '/dbfs/FileStore/jars/databricks_example_udaf_0_0_1_SNAPSHOT.jar';`
  - In another SQL cell, run the command `SHOW USER FUNCTIONS;` to check the proper registration of you function
  - Restart the cluster.
  - Call your function: ```SELECT hive_metastore.default.mycustomaverage(TO.quantity) FROM `test_schema_debug`.`table_for_udaf` AS T0;```

This raise an error message:
```Error in SQL statement: AnalysisException: [CANNOT_LOAD_FUNCTION_CLASS] Cannot load class MyAverage when registering the function `hive_metastore`.`default`.`mycustomaverage`, please make sure it is on the classpath.; line 1 pos 7```