#!/bin/sh

mvn clean package

echo "##################################"
echo "##         RUNNING CODE         ##"
echo "##################################"
echo "********** Q1 - FINDING THE SOURCE"
echo "----- INPUT"
cat q1/src/test/resources/input_a.dat 
echo "----- OUTPUT"
java -jar q1/target/devtest-jar-with-dependencies.jar q1/src/test/resources/input_a.dat 

echo "********** Q2 - JUSTIFYING TEXT"
echo "----- INPUT"
cat q2/src/test/resources/input_a.dat 
echo "----- OUTPUT"
java -jar q2/target/devtest-jar-with-dependencies.jar q2/src/test/resources/input_a.dat 

echo "********** Q5 - SPIRAL PRINTING"
echo "----- INPUT"
cat q5/src/test/resources/input_a.dat 
echo "----- OUTPUT"
java -jar q5/target/devtest-jar-with-dependencies.jar q5/src/test/resources/input_a.dat 

