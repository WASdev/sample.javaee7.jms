  
  
# Sample Java EE 7 - JMS


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
2. If you are running the sample in WebSphere Liberty Profile follow the intructions in the sections below.


## Getting Started

Browse the code to see what it does, or build and run it yourself!


## Running in Eclipse

1. Download and install [Eclipse with the WebSphere Developer Tools](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/).
2. Create a new Liberty Profile Server.
3. Clone this repository.
4. Import the sample into Eclipse using *File -> Import -> Maven -> Existing Maven Projects* option.
5. Deploy the sample into Liberty server. Right click on the project and select *Run As -> Run on Server* option. Find and select the Liberty profile server and press *Finish*.
6. Go to: [http://localhost:9080/sample.javaee7.jms/](http://localhost:9080/sample.javaee7.jms/)

## Running with Maven

This project can be build with [Apache Maven](http://maven.apache.org/). The project uses [Liberty Maven Plug-in][] to automatically download and install Liberty profile runtime from the [Liberty repository](https://developer.ibm.com/wasdev/downloads/). Liberty Maven Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application with Maven:

1. Execute full Maven build. This will cause Liberty Maven Plug-in to download and install Liberty profile server.
    ```bash
    $ mvn clean install
    ```

2. To run the server with the JMS application execute:
    ```bash
    $ mvn liberty:run-server
    ```

Once the server is running, the application will be available under [http://localhost:9080/sample.javaee7.jms/](http://localhost:9080/sample.javaee7.jms/).

## Deploying to Bluemix

Click the button below to deploy your own copy of this application to [Bluemix](https://bluemix.net).

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https://github.com/WASdev/sample.javaee7.jms)

Once the application is deployed and running in Bluemix, it will be available under 
[http://MYAPPNAME.mybluemix.net/sample.javaee7.jms/](http://MYAPPNAME.mybluemix.net/sample.javaee7.jms/).
