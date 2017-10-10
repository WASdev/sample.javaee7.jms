  
  
# Sample Java EE 7 - JMS [![Build Status](https://travis-ci.org/WASdev/sample.javaee7.jms.svg?branch=master)](https://travis-ci.org/WASdev/sample.javaee7.jms)


This sample uses simplified API of JMS2.0. It contains couple of servlets for performing Point to Point and Publish/Subscribe messaging.The JMS Servlet provides means to send/receive messages to a queue and also publish and subscribe messages from a Topic. 

This sample requires that you create some resources before you deploy the application. 

1. If you are running the sample in WebSphere Application Server classic, you can create resources using configuration scripts published with the sample at [src.main.scripts](https://github.com/WASdev/sample.javaee7.jms/tree/master/src/main/scripts).

  First, run the createSIBusResources.py script from the app_server_root/bin directory to create the required resources.         Provide cell_name, node_name, and server_name values and the path to the sample.javaee7.jms.war file in the command:
  ```bash
    ./wsadmin.sh -f createSIBusResources.py cell_name node_name server_name path_to_file/sample.javaee7.jms.war
   ```
  Then, if security is enabled in the server, run the addAuthAlias.py script from the app_server_root/bin directory. Provide     your user name and password in the command:
  ```bash
    ./wsadmin.sh -f addAuthAlias.py user_name password
   ```
2. If you are running the sample in Liberty follow the instructions in the sections below.


## Getting Started

Browse the code to see what it does, or build and run it yourself!


## Running in Eclipse

1. Download and install [Eclipse with the WebSphere Developer Tools](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/).
2. Clone this repository.
3. Import the sample into Eclipse using *File -> Import -> Maven -> Existing Maven Projects* option.
4. Right click on the project and select *Run As -> Run on Server* option. Find and select the Liberty profile server and press *Finish*.
5. Go to: [http://localhost:9080/sample.javaee7.jms/](http://localhost:9080/sample.javaee7.jms/)

NOTE: When running this Java EE 7 sample using eclipse and WDT, some validation warnings or errors may be shown for the server.xml included with the project, you can remove these errors from your workspace by executing the following steps:  

1. Right click on your project and select "Properties"
2. Go to "Validation >> XML Validator >> settings"
3. Select "Exclude Group", then Click on "Add Rule"
4. Select "Folder or file name" option and click Next
5. Click "Browse" and select the server.xml file in your project folder.
6. Click Finish and execute a "Project >> Clean"


## Running with Maven

This project can be built with [Apache Maven](http://maven.apache.org/). The project uses [Liberty Maven Plug-in](https://github.com/WASdev/ci.maven) to automatically download and install Liberty with Java EE7 Full Platform runtime from [Maven Central](https://search.maven.org/). Liberty Maven Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application with Maven:

1. Execute the full Gradle build. The Liberty Gradle Plug-in will download and install the Liberty runtime and create the server.
    ```bash
    $ mvn clean install
    ```

2. To run the server with the JMS application execute:
    ```bash
    $ mvn liberty:run-server
    ```

Once the server is running, the application will be available under [http://localhost:9080/sample.javaee7.jms/](http://localhost:9080/sample.javaee7.jms/).

## Running with Gradle

This project can be built with [Apache Gradle]. The project uses [Liberty Gradle Plug-in](https://github.com/WASdev/ci.gradle) to automatically download and install Liberty with Java EE7 Full Platform runtime from [Maven Central]. Liberty Gradle Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application with Gradle:

1. Execute full Gradle build. This will cause Liberty Gradle Plug-in to download and install Liberty profile server.
    ```bash
    $ gradle clean build
    ```

2. To run the server with the JMS application execute:
    ```bash
    $ gradle libertyStart
    ```
    
Once the server is running, the application will be available under [http://localhost:9080/sample.javaee7.jms/](http://localhost:9080/sample.javaee7.jms/).
    
3. To stop the server with the JMS application execute:
    ```bash
    $ gradle libertyStop
    ```


## Deploying to Bluemix

Click the button below to deploy your own copy of this application to [Bluemix](https://bluemix.net).

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https://github.com/WASdev/sample.javaee7.jms)

Once the application is deployed and running in Bluemix, it will be available under 
[http://MYAPPNAME.mybluemix.net/sample.javaee7.jms/](http://MYAPPNAME.mybluemix.net/sample.javaee7.jms/).

## Notice

© Copyright IBM Corporation 2015, 2017.

## License

```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````

[Liberty Maven Plug-in]: https://github.com/WASdev/ci.maven
