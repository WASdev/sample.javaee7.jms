  
  
# Sample Java EE 7 - JMS


This sample uses simplified API of JMS2.0. It contains couple of servlets for performing Point to Point and Publish/Subscribe messaging.The JMS Servlet provides means to send/receive messages to a queue and also publish and subscribe messages from a Topic. 

This sample requires that you create some resources before you deploy the application. Complete the following steps to create the required resources: 

1. Create Bus
2. Add server as bus member
3. Create a Queue destination (Ex: Queue1)
4. Create a Topic Space destination (Ex: TS1)
5. Create a QueueConnectionFactory (Ex: jndi_JMS_BASE_QCF)
6. Create a TopicConnectionFactory (Ex: jmsTCF)
7. Create a JMSQueue (Ex: jndi_INPUT_Q)
8. Create a JMSTopic (Ex : jmsTopic)

If security is enabled, the following extra steps need to be done:

1. Create an authentication alias
2. Modify the connection factory to add the authentication alias details
3. Add the user to the bus connector role

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
