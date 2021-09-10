run: compile
	java GraphDriver

compile: CS400Graph.java GraphADT.java GraphAddon.java FileReader.java GraphDriver.java
	  javac CS400Graph.java
	  javac GraphADT.java 
	  javac GraphAddon.java
	  javac FileReader.java 
	  javac GraphDriver.java

test: 
	javac -cp .:junit5.jar TestClass1.java
	java -jar junit5.jar -cp . --scan-classpath -n TestClass1

clean: 
	$(RM) *.class
