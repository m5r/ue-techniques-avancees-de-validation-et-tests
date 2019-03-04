JAVA = "java"
JAVAC = "javac"
OPENJMLJAR = "../../openjml/openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ../bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = rm
JUNIT4 = "../../openjml/junit-4.12.jar"
HAMCREST = "../../openjml/hamcrest-core-1.3.jar"
JMLRUNTIME = "../../openjml/jmlruntime.jar"
JMLCLASSPATH = .:$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH):$(JUNIT4):$(HAMCREST)

run : ../bin/ExplosivesMain.class
	$(JAVA) -cp ../bin:$(JMLCLASSPATH) ExplosivesMain

../bin/Explosives.class : Explosives.java
	$(JMLC) Explosives.java

../bin/ExplosivesMain.class : ../bin/Explosives.class ExplosivesMain.java
	$(JAVAC) -cp ../bin:$(JMLCLASSPATH) -d ../bin  ExplosivesMain.java


../bin/TestExplosivesJUnit4.class : ../bin/Explosives.class TestExplosivesJUnit4.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TestExplosivesJUnit4.java

TestExplosivesJUnit4.run : ../bin/TestExplosivesJUnit4.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TestExplosivesJUnit4

../bin/TestExplosivesJUnit4Public.class : ../bin/Explosives.class TestExplosivesJUnit4Public.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TestExplosivesJUnit4Public.java

TestExplosivesJUnit4Public.run : ../bin/TestExplosivesJUnit4Public.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) "TestExplosivesJUnit4Public"

../bin/TestExplosivesFindBat.class : ../bin/Explosives.class TestExplosivesFindBat.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TestExplosivesFindBat.java

TestExplosivesFindBat.run : ../bin/TestExplosivesFindBat.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) "TestExplosivesFindBat"

clean : 
	$(RM) ../bin/*.class
