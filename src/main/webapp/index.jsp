<!-- 
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
 * (C) Copyright IBM Corp. 2015,2016.
 * 
 * All Rights Reserved. Licensed Materials - Property of IBM.  
 */
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"></meta>
<title>JMS WAS Classic tests</title>
<style>
.frm1{
	padding: 15px;
	background-color: #9666af; 
	margin-bottom: 10px;
}

.frm2{
	padding-left: 25px; 
	color: #440055;
}

.frm3 {
	padding-left: 25px;
	font-family: Verdana;
	font-size: 12px;
	color: #443355;
	white-space: nowrap;
}

.big {
	font-size: 26px;
	color: white;
}

button {
	padding: 5px;
	padding-left: 20px;
	padding-right: 20px;
	margin: 10px;
	width: 270px
}

.small{
	font-size: 12px;
}

</style>
</head>

<body>
<div class="frm1">
<div class="big"> WAS Java EE 7 Sample - JMS</div>

</div>
<div class="frm2">
<div class="small">This application has been tested with Firefox and Chrome </div>
<div class="small"> The source code for this application can be found on: <a href="https://github.com/WASdev/">https://github.com/WASdev/</a> </div>
</div>

<div class="frm3">
		
		
		<h3>Publisher Suscriber Tests</h3>
		<a href="/sample.javaee7.jms/PublisherSubscriberTest?ACTION=consumerTest">Consumer Test</a><br>
		Create a non durable subscriber and publish and receive the message from topic <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/PublisherSubscriberTest?ACTION=consumerTest <br></i>
		<br>
		<a href="/sample.javaee7.jms/PublisherSubscriberTest?ACTION=durableConsumer">Durable Consumer Test</a><br>
		Create a durable subscriber and publish and receive the message from topic <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/PublisherSubscriberTest?ACTION=durableConsumer <br></i>
		<br>
		<a href="/sample.javaee7.jms/PublisherSubscriberTest?ACTION=publishMessages">Publish Messages Test</a><br>
		Publish 5 messages to the topic <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/PublisherSubscriberTest?ACTION=publishMessages <br></i>
		<br>
		<h3>Point to Point Tests</h3>
		<a href="/sample.javaee7.jms/P2PTest?ACTION=sendAndReceive">Send And Receive Test</a><br>
		Call the Send and Receive Message <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/P2PTest?ACTION=sendAndReceive <br></i>
		<br>
		<a href="/sample.javaee7.jms/P2PTest?ACTION=sendMessage">Send Message Test</a><br>
		Send Message only <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/P2PTest?ACTION=sendMessage <br></i>
		<br>
		<a href="/sample.javaee7.jms/P2PTest?ACTION=receiveAllMessages">Receive All Messages Test</a><br>
		Receive All messages from queue<br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/P2PTest?ACTION=receiveAllMessages <br></i>
		<br>
		<a href="/sample.javaee7.jms/P2PTest?ACTION=receiveAllMessagesSelectors">Receive All Messages Selectors Test</a><br>
		Receive all the messages using message selector<br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/P2PTest?ACTION=receiveAllMessagesSelectors <br></i>
		<br>
			
		<h3>Delivery Delay Tests</h3>
		<a href="/sample.javaee7.jms/DeliveryDelayTest?ACTION=deliveryDelayQueue">Delivery Delay Queue Test</a><br>
		setDeliveryDelay on a JMSProducer with a Queue as destination is being showcased on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jmsDeliveryDelayTest?ACTION=deliveryDelayQueue <br></i>
		<br>
			<a href="/sample.javaee7.jms/DeliveryDelayTest?ACTION=deliveryDelayTopic">Delivery Delay Topic Test</a><br>
		setDeliveryDelay on JMSProducer with a Topic as destination is being showcased on this scenario<br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jmsDeliveryDelayTest?ACTION=deliveryDelayTopic <br></i>
		<br>

		<h3>Get Body Tests</h3>
		<a href="/sample.javaee7.jms/GetBodyTest?ACTION=getBodyQueue">Get Body Queue Test</a><br>
		getBodyQueue function is being showcased on this scenario<br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/GetBodyTest?ACTION=getBodyQueue <br></i>
		<br>
		<a href="/sample.javaee7.jms/GetBodyTest?ACTION=getBodyTopic">Get Body Topic Test</a><br>
		getBodyTopic function is being showcased on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/GetBodyTest?ACTION=getBodyTopic <br></i>
		<br>
			<a href="/sample.javaee7.jms/GetBodyTest?ACTION=receiveBodyQueue">Receive Body Queue Test</a><br>
		receiveBodyQueue function is being showcased on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/GetBodyTest?ACTION=receiveBodyQueue <br></i>
		<br>
				<a href="/sample.javaee7.jms/GetBodyTest?ACTION=receiveBodyTopic">Receive Body Topic Test</a><br>
		receiveBodyTopic function is being showcased on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/GetBodyTest?ACTION=receiveBodyTopic <br></i>
		<br>

		<h3>Method Chaining Tests</h3>
		<a href="/sample.javaee7.jms/MethodChainingTest?ACTION=MethodChainingQueue">Method Chaining Queue Test</a><br>
		Method chaining is being showcased with a Queue as destination on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/MethodChainingTest?ACTION=MethodChainingQueue <br></i>
		<br>		
		<a href="/sample.javaee7.jms/MethodChainingTest?ACTION=MethodChainingTopic">Method Chaining Topic Test</a><br>
		Method chaining is being showcased with a Topic as destination on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/MethodChainingTest?ACTION=MethodChainingTopic <br></i>
		<br>
		
		<h3>Suscription Tests</h3>
		<a href="/sample.javaee7.jms/SubscriptionTest?ACTION=createSharedConsumerTest">Create Shared Consumer Test</a><br>
		createSharedConsumer on JMSContext is being showcased on this scenario<br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/SubscriptionTest?ACTION=createSharedConsumerTest <br></i>
		<br>
		
		<a href="/sample.javaee7.jms/SubscriptionTest?ACTION=createSharedDurableConsumerTest">Create Shared Durable Consumer Test</a><br>
		createSharedDurableConsumer on JMSContext is being showcased on this scenario <br>
		<i>url: &lt;host:port&gt;/sample.javaee7.jms/SubscriptionTest?ACTION=createSharedDurableConsumerTest <br></i>
		<br>
		
		
	</div>

</html>
