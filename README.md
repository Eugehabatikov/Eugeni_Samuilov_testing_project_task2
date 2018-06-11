# Eugenii_Samyilov_testing_project_task2
1. Inrtoduction
This class implements parsing of incoming strings received from the console, removes consecutive identical strings, has the following flags:
-u leaves only unique strings,
-i ignores the case of strings,
-c counts the number of rows removed for each string,
-o (name.txt) specifies the name of the output file,
-file specifies the name of the file from which to read the string,
-s digit, specifies the number of first characters to ignore when comparing,
-help to show help man.

2. Create JAR file
a) Create project in intelliJ IDEA from git repository;
b) In File menu click open project structure window;
c) On left panel click Artefacts;
d) Press Add botton click jar and then click From modules wizh dependencies;
e) Press Apply and then OK;
f) in Build menu click Build artefacts and then click Build.
For more information see https://www.youtube.com/watch?v=tA8rEz_xFrQ 

3. Use
To run programm in command line type java -jar path\to\jar\file\uniq2.jar
To see how to use programm type java -jar path\to\jar\file\uniq2.jar -help
