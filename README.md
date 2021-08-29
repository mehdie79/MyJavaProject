# Rabbit Trails

The main goal of the game Rabbit Trails is to have the rabbit(main character) eat all the carrots(regular rewards) and make it out of the garden(maze) without getting caught by the eagle(moving enemy) or getting trapped into many animal traps(punishment).

## Video
Link for video of the demo of the game:
https://youtu.be/oOIhiXw4gkM

## Getting Started

To have the project set up and running locally follow the simple steps and examples below

### Prerequisites
Have an IDE downloaded such as Eclipse or Intellij. Make sure you have maven 3.6.3 or later and JDK 15.0.1 or later.  
If already installed, to check maven version
```
mvn -v
```

To check JDK version
```
java -version
```
or
```
javac -version
```

Ensure your JAVA_HOME path is set correctly  
Run this in terminal
```
echo JAVA_HOME
```
should give this or similar output
```
/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
```

### Installing and Building

1. Clone the repo
```
git clone https://csil-git1.cs.surrey.sfu.ca/cmpt276s21_group7/project.git
```

2. Compile the project
```
mvn compile
```

3. Build the maven project and install into local maven repo (this also creates jar files for the game and javadocs)
```
mvn install
```

4. Execute the project
```
mvn exec:java -Dexec.mainClass=Launcher
```

## Running the tests

To run tests
```
mvn test
```

## Javadocs
Create jar file for javadocs
```
mvn javadoc:jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
