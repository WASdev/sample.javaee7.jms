"""
/*
 * COPYRIGHT LICENSE: This information contains sample code provided in source
 * code form. You may copy, modify, and distribute these sample programs in any 
 * form without payment to IBM for the purposes of developing, using, marketing 
 * or distributing application programs conforming to the application programming 
 * interface for the operating platform for which the sample code is written. 
 * 
 * Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE 
 * ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, 
 * BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, 
 * SATISFACTORY QUALITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR 
 * CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR
 * OPERATION OF THE SAMPLE SOURCE CODE. IBM HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE
 * SOURCE CODE.
 * 
 * (C) Copyright IBM Corp. 2015.
 * 
 * All Rights Reserved. Licensed Materials - Property of IBM.  
 */
 """
import sys

busName = "DEFAULT"
cellName = sys.argv[0]
node = sys.argv[1]
server = sys.argv[2]
queueName = "Queue1"
topicspaceName = "TS1"
jmsQConnFac = "jndi_JMS_BASE_QCF"
jmsTConnFac = "jmsTCF"
jmsQueueOnNode1 = "jndi_INPUT_Q"
jmsTopicOnNode1 = "jmsTopic"
topicName = "topicDest"
clientID = "client1"
appPath = sys.argv[3]
print "------------- ENV -------------"

print "Node : "+node

print "Server : "+server

print "Bus name : "+busName

print "-------------------------------"


print "Create default SIBus"

params = "-bus %s" % busName
AdminTask.createSIBus(params )

print "Add server as bus member"

params = "-bus %s -node %s -server %s -fileStore" % (busName, node, server)
AdminTask.addSIBusMember(params )

print "create SIB Queue destination %s" % queueName

params = "-bus %s -name %s -type queue -node %s -server %s" % (busName, queueName, node, server)
AdminTask.createSIBDestination(params )

print "create SIB Topic space destination %s" % topicspaceName

params = "-bus %s -name %s -type topicspace -node %s -server %s" % (busName, topicspaceName, node, server)
AdminTask.createSIBDestination(params )

scope = AdminConfig.getid("/Node:%s/" % node )

print "Using scope: %s" % scope

print "Creating QueueConnectionFactory %s " % jmsQConnFac

params = "-type %s -name %s -jndiName %s -busName %s" % ("queue", jmsQConnFac, jmsQConnFac, busName)
AdminTask.createSIBJMSConnectionFactory(scope, params )

print "Creating TopicConnectionFactory %s " % jmsTConnFac

durableSubHome = node +"."+server+"-"+busName
print durableSubHome
params = "-type %s -name %s -jndiName %s -busName %s -clientID %s -durableSubscriptionHome %s" % ("topic", jmsTConnFac, jmsTConnFac, busName, clientID, durableSubHome)
AdminTask.createSIBJMSConnectionFactory(scope, params )

print "Creating JMSQueue %s " % jmsQueueOnNode1

params = "-name %s -jndiName %s -queueName %s" % (jmsQueueOnNode1, jmsQueueOnNode1, queueName)
AdminTask.createSIBJMSQueue(scope, params )

print "Creating JMSTopic %s " % jmsTopicOnNode1

params = "-name %s -jndiName %s -topicName %s -busName %s -topicSpace %s" % (jmsTopicOnNode1, jmsTopicOnNode1, topicName, busName, topicspaceName)
AdminTask.createSIBJMSTopic(scope, params )

print "Install the application %s" % appPath

AdminApp.install(appPath, '[ -appname sample_javaee7_jms_war -contextroot sample.javaee7.jms -MapResRefToEJB [[ JMS20_Samples_P2PTEST "" sample.javaee7.jms.war,WEB-INF/web.xml jndi_JMS_BASE_QCF javax.jms.QueueConnectionFactory jndi_JMS_BASE_QCF ][ JMS20_Samples_P2PTEST "" sample.javaee7.jms.war,WEB-INF/web.xml jmsTCF javax.jms.TopicConnectionFactory jmsTCF ]] -MapModulesToServers [[ JMS20_Samples_P2PTEST sample.javaee7.jms.war,WEB-INF/web.xml WebSphere:cell='+cellName+',node='+node+',server='+server+' ]] -MapResEnvRefToRes [[ JMS20_Samples_P2PTEST "" sample.javaee7.jms.war,WEB-INF/web.xml jndi_INPUT_Q javax.jms.Queue jndi_INPUT_Q ][ JMS20_Samples_P2PTEST "" sample.javaee7.jms.war,WEB-INF/web.xml jmsTopic javax.jms.Topic jmsTopic ]]]' )

AdminConfig.save( )


