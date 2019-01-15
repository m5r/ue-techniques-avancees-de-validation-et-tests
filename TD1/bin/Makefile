JAVA = "java"
JAVAC = "javac"
OPENJMLJAR = "..\..\OpenJML\openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ..\bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = del
JUNIT4 = "..\..\OpenJML\junit-4.12.jar"
HAMCREST = "..\..\OpenJML\hamcrest-core-1.3.jar"
JMLRUNTIME = "..\..\OpenJML\jmlruntime.jar"
JMLCLASSPATH = .;$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH);$(JUNIT4);$(HAMCREST)

run : ..\bin\MainSetAsTree.class
	$(JAVA) -cp ..\bin;$(JMLCLASSPATH) MainSetAsTree

..\bin\SetAsTree.class : SetAsTree.java
	$(JMLC) SetAsTree.java



..\bin\MainSetAsTree.class : ..\bin\SetAsTree.class MainSetAsTree.java
	$(JAVAC) -cp ..\bin;$(JMLCLASSPATH) -d ..\bin MainSetAsTree.java

..\bin\TestSetAsTreeJUnit4.class : ..\bin\SetAsTree.class TestSetAsTreeJUnit4.java
	$(JAVAC) -cp ..\bin;$(JMLJUNITCLASSPATH) -d ..\bin TestSetAsTreeJUnit4.java

TestSetAsTreeJUnit4.run : ..\bin\TestSetAsTreeJUnit4.class
	$(JAVA) -cp ..\bin;$(JMLJUNITCLASSPATH) TestSetAsTreeJUnit4


..\bin\FailingTestSetAsTreeJUnit4.class : ..\bin\SetAsTree.class FailingTestSetAsTreeJUnit4.java
	$(JAVAC) -cp ..\bin;$(JMLJUNITCLASSPATH) -d ..\bin FailingTestSetAsTreeJUnit4.java


FailingTestSetAsTreeJUnit4.run : ..\bin\FailingTestSetAsTreeJUnit4.class
	$(JAVA) -cp ..\bin;$(JMLJUNITCLASSPATH) FailingTestSetAsTreeJUnit4

..\bin\AllTests.class : AllTests.java
	$(JAVAC) -cp ..\bin;$(JMLJUNITCLASSPATH) -d ..\bin AllTests.java

AllTests.run: ..\bin\AllTests.class ..\bin\TestSetAsTreeJUnit4.class ..\bin\FailingTestSetAsTreeJUnit4.class
	$(JAVA) -cp ..\bin;$(JMLJUNITCLASSPATH) AllTests


clean : 
	$(RM) ..\\bin\\*.class
	