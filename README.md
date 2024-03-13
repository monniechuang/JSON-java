# SWE262 Milestones

### Build Instructions 

*Build the class files from the package root directory src/main/java*
```shell
javac org/json/*.java
```

*Create the jar file in the current directory*
```shell
jar cf json-java.jar org/json/*.class
```

*Compile a test program that uses the jar (from the package root directory src/test/java)*
```shell
javac -cp .;../../main/java/json-java.jar org/json/MilestoneTest.java (Windows)
javac -cp .:../../main/java/json-java.jar org/json/MilestoneTest.java (Unix Systems)
```

*Execute the file*
```shell 
java -cp .;../../main/java/json-java.jar org/json/MilestoneTest (Windows)
java -cp .:../../main/java/json-java.jar org/json/MilestoneTest (Unix Systems)
```

### Running the Unit Tests

*navigate to the project root directory in your terminal and execute the test suite with Maven:*
```shell
mvn clean test
```

# Milestone 2 

### New Functions
File Path: `src/main/java/org/json/XML.java`

```shell 
static JSONObject toJSONObject(Reader reader, JSONPointer path)
```
- **Purpose**: Read an XML file into a JSON object, and extract some smaller sub-object inside. Useful for efficiently parsing large XML files to extract targeted data without processing the entire document.
- **Line Number**: Starts at line 937.


```shell 
static JSONObject toJSONObject(Reader reader, JSONPointer path, JSONObject replacement)
```
- **Purpose**: 
Modifies a segment of an XML document by replacing it on a certain key path with a new JSONObject. This method is ideal for updating XML data and converting it directly to JSON.
- **Line Number**: Starts at line 989.


```shell 
static boolean parseMile2(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config, String specialKey, JSONObject specialData, boolean isReplaceMode)
```
- **Purpose**: A helper method used internally to navigate and parse XML content, facilitating the conversion and modification processes outlined above. It handles specific tasks like searching for elements, handling replacements, and building the JSONObject.
- **Line Number**: Starts at line 1034.


### New Unit Test

- File Path: `src/test/java/org/json/junit/XMLTest.java`
- Line Number: Starts at line 1331.

#### Test Scenarios
- **testToJSONObjectWithValidPath()**: Test for Valid Path Conversion
- **testToJSONObjectWithInvalidPath()**: Test for Invalid Path
- **testToJSONObjectWithInvalidPath()**: Test for Successful Replacement 
- **testToJSONObjectWithReplacement()**: Test for Replacement Path Not Found

# Milestone 3

### New Functions
File Path: `src/main/java/org/json/XML.java`

```shell 
public static JSONObject toJSONObject(Reader reader, Function keyTransformer)
```
- **Purpose**: Converts XML content read from a Reader into a JSONObject. It applies a transformation to every key encountered during the parsing process, according to the logic defined in the keyTransformer function.
- **Line Number**: Starts at line 1240.


```shell 
private static boolean parseMile3(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config,  Function keyTransformer)
```
- **Purpose**: 
A modified version of the existing parse method, which includes an additional parameter for the keyTransformer and applies it to keys during the parsing process.
- **Line Number**: Starts at line 1259.


#### Performance Implications
Applying key transformations inside the library during the parsing process, rather than after converting XML to JSON in client code, has significant performance implications:

- **Efficiency:** Transforming keys during parsing avoids the need for a second pass through the JSONObject to modify keys. This is more efficient, especially for large XML documents, as it reduces the overall computation and memory footprint.
- **Complexity:** Implementing key transformation logic within the library simplifies client code, reducing complexity and potential errors in client-side post-processing.
- **Performance Overhead:** While adding key transformation during parsing introduces additional checks and function calls for each key, this overhead is mitigated by eliminating the need for a costly second traversal of the resulting JSONObject.


### New Unit Test

- File Path: `src/test/java/org/json/junit/XMLTest.java`
- Line Number: Starts at line 1420.

#### Test Scenarios
- **testToJSONObjectKeyTransformerAddPrefix():** Tests the transformation of keys by adding a prefix to each key.
- **testToJSONObjectKeyTransformerReverse():** Verifies the functionality of reversing each key.
- **testToJSONObjectKeyTransformerUpperCase():** Ensures that keys are correctly transformed to uppercase.
Each test case validates not only the transformation of keys but also that the transformation does not alter the associated values in the JSONObject.

# Milestone 4

### New Functionality: Streaming JSON Nodes
File Path: `src/main/java/org/json/JSONObject.java`

#### JSONNode Class
```shell 
public static class JSONNode{}
```
- **Purpose**: Represents a node in the JSON object tree, containing the key, value, and path from the root.
- **Line Number**: Starts at line 2893.

#### Streaming Operations
```shell 
public Stream<JSONNode> toStream()
private Stream<JSONNode> toStream(String path, Object value)
```
- **Purpose**: Allows converting a JSONObject into a Stream of JSONNode objects for streaming operations, representing each node in the JSON object tree.
- **Line Number**: Starts at line 2921.


#### Implementation Details
- **JSONObject to Stream Conversion**: Facilitates streaming operations on JSON objects by converting them into a stream of nodes.
- **Path Handling**: Each node in the stream includes the path from the root to the node, aiding in identifying the node's location within the JSON structure.
- **Key Transformation**: Special handling for array indices, treating them differently from regular keys to accurately represent the JSON structure.


### New Unit Test

- File Path: `src/test/java/org/json/junit/JSONObjectTest.java`
- Line Number: Starts at line 3822.

#### Test Scenarios
- **testToStream**: Verifies the total number of nodes in the stream matches the expected count.
- **testToStreamExtractValues**: Tests extraction of specific values (e.g., book titles) from the stream, validating correct filtering and mapping operations.
- **testToStreamFilterReplace**: Checks the ability to filter nodes and apply transformations (e.g., modifying author names) within the stream, ensuring the transformations are applied as expected.

# Milestone 5
File Path: `src/main/java/org/json/XML.java`

### Helper Class
```shell 
public static class FutureObject{}
```
- **Purpose**: A helper class for managing asynchronous conversion tasks. It includes a method toJSONObject for asynchronously converting XML data into a JSONObject and a method stopFuture for stopping the associated executor service.
- **Line Number:** Starts at line 1471.

### New Function
```shell 
public static Future<JSONObject> toJSONObject(Reader reader, Function<String, String> keyTransformer, Consumer<Exception> exceptionHandler)
```
- **Purpose**: Initiates the asynchronous conversion of XML data into a JSONObject. It requires a Reader containing the XML data, a key transformer function, and an exception handler.
- **Line Number:** Starts at line 1501.

### New Unit Test

- File Path: `src/test/java/org/json/junit/XMLTest.java`
- Line Number: Starts at line 1508.

#### Test Scenarios
- **testToJSONObjectAsync():** Tests proper functioning of the asynchronous XML to JSON conversion process.

---
![Json-Java logo](https://github.com/stleary/JSON-java/blob/master/images/JsonJava.png?raw=true)

<sub><sup>image credit: Ismael Pérez Ortiz</sup></sub>


JSON in Java [package org.json]
===============================

[![Maven Central](https://img.shields.io/maven-central/v/org.json/json.svg)](https://mvnrepository.com/artifact/org.json/json)
[![Java CI with Maven](https://github.com/stleary/JSON-java/actions/workflows/pipeline.yml/badge.svg)](https://github.com/stleary/JSON-java/actions/workflows/pipeline.yml)
[![CodeQL](https://github.com/stleary/JSON-java/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/stleary/JSON-java/actions/workflows/codeql-analysis.yml)

**[Click here if you just want the latest release jar file.](https://search.maven.org/remotecontent?filepath=org/json/json/20231013/json-20231013.jar)**


# Overview

[JSON](http://www.JSON.org/) is a light-weight language-independent data interchange format.

The JSON-Java package is a reference implementation that demonstrates how to parse JSON documents into Java objects and how to generate new JSON documents from the Java classes.

Project goals include:
* Reliable and consistent results
* Adherence to the JSON specification 
* Easy to build, use, and include in other projects
* No external dependencies
* Fast execution and low memory footprint
* Maintain backward compatibility
* Designed and tested to use on Java versions 1.8 - 21


The files in this package implement JSON encoders and decoders. The package can also convert between JSON and XML, HTTP headers, Cookies, and CDL.

# If you would like to contribute to this project

For more information on contributions, please see [CONTRIBUTING.md](https://github.com/stleary/JSON-java/blob/master/docs/CONTRIBUTING.md)

Bug fixes, code improvements, and unit test coverage changes are welcome! Because this project is currently in the maintenance phase, the kinds of changes that can be accepted are limited. For more information, please read the [FAQ](https://github.com/stleary/JSON-java/wiki/FAQ).

# Build Instructions

The org.json package can be built from the command line, Maven, and Gradle. The unit tests can be executed from Maven, Gradle, or individually in an IDE e.g. Eclipse.
 
**Building from the command line**

*Build the class files from the package root directory src/main/java*
```shell
javac org/json/*.java
```

*Create the jar file in the current directory*
```shell
jar cf json-java.jar org/json/*.class
```

*Compile a program that uses the jar (see example code below)*
```shell
javac -cp .;json-java.jar Test.java (Windows)
javac -cp .:json-java.jar Test.java (Unix Systems)
```

*Test file contents*

```java
import org.json.JSONObject;
public class Test {
    public static void main(String args[]){
       JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
       System.out.println(jo);
    }
}
```

*Execute the Test file*
```shell 
java -cp .;json-java.jar Test (Windows)
java -cp .:json-java.jar Test (Unix Systems)
```

*Expected output*

```json
{"abc":"def"}
```

 
**Tools to build the package and execute the unit tests**

Execute the test suite with Maven:
```shell
mvn clean test
```

Execute the test suite with Gradlew:

```shell
gradlew clean build test
```

# Notes

For more information, please see [NOTES.md](https://github.com/stleary/JSON-java/blob/master/docs/NOTES.md)

# Files

For more information on files, please see [FILES.md](https://github.com/stleary/JSON-java/blob/master/docs/FILES.md)

# Release history:

For the release history, please see [RELEASES.md](https://github.com/stleary/JSON-java/blob/master/docs/RELEASES.md)
