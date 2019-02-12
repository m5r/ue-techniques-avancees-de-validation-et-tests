JAVA = "java"
JAVAC = "javac"
OPENJMLJAR = "../../openjml/openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ../bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = del
JUNIT4 = "../../openjml/junit-4.12.jar"
HAMCREST = "../../openjml/hamcrest-core-1.3.jar"
JMLRUNTIME = "../../openjml/jmlruntime.jar"
JMLCLASSPATH = .:$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH):$(JUNIT4):$(HAMCREST)

../bin/Prime.class : Prime.java
	$(JMLC) Prime.java

../bin/PrimeArray.class : ../bin/Prime.class PrimeArray.java
	$(JMLC) -classpath ../bin PrimeArray.java

../bin/TestPrimeJUnit4.class : ../bin/Prime.class TestPrimeJUnit4.java
	$(JAVAC) -classpath ../bin:$(JMLJUNITCLASSPATH) -d ../bin TestPrimeJUnit4.java

TestPrimeJUnit4.run : ../bin/TestPrimeJUnit4.class
	 java -cp  ../bin:$(JMLJUNITCLASSPATH) TestPrimeJUnit4
	 
../bin/TestPrimeJUnit4Param.class : ../bin/Prime.class TestPrimeJUnit4Param.java
	$(JAVAC) -classpath ../bin:$(JMLJUNITCLASSPATH) -d ../bin TestPrimeJUnit4Param.java

TestPrimeJUnit4Param.run : ../bin/TestPrimeJUnit4Param.class
	 java -cp  ../bin:$(JMLJUNITCLASSPATH) TestPrimeJUnit4Param
	
../bin/TestPrimeArrayJUnit4.class : ../bin/PrimeArray.class TestPrimeArrayJUnit4.java
	$(JAVAC) -classpath  ../bin:$(JMLJUNITCLASSPATH)  -d ../bin TestPrimeArrayJUnit4.java

TestPrimeArrayJUnit4.run : ../bin/TestPrimeArrayJUnit4.class
	 java -cp  ../bin:$(JMLJUNITCLASSPATH) TestPrimeArrayJUnit4

clean : 
	$(RM) ../bin/*.class
