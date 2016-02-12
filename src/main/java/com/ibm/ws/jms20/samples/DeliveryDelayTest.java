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
 * (C) Copyright IBM Corp. 2016.
 * 
 * All Rights Reserved. Licensed Materials - Property of IBM.  
 */

package com.ibm.ws.jms20.samples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class P2PTset
 */
@WebServlet("/DeliveryDelayTest")
public class DeliveryDelayTest extends HttpServlet {

	/**
	 * Default constructor.
	 */
	public DeliveryDelayTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String strAction = request.getParameter("ACTION");
		PrintWriter out = response.getWriter();
				
		  out.println("<html>");
          out.println("<head>");
          out.println("<title>Reading asynchronously</title>");
          
          out.println("<meta charset=\"utf-8\"></meta>");
          out.println("<title>JMS WAS Classic tests</title>");
          out.println("<style>");
          out.println(".frm1{padding: 15px;background-color: #9666af; margin-bottom: 10px;}");
          out.println(".frm2{padding-left: 25px; font-family: Verdana; color: #440055;}");
          out.println(".big{font-size: 26px; color: white;}");
          out.println(".small{font-size: 12px;}");
          out.println("button, select{padding: 5px; padding-left: 20px; padding-right: 20px; margin:10px; width: 270px}");
          out.println("</style>");
          out.println("</head>");
          out.println("<body>");
          out.println("<body>");
          out.println("<div class=\"frm1\">");
          out.println("<div class=\"big\">  WAS Java EE 7 Sample - JMS</div>");
          out.println("</div>");
          out.println("<div class=\"frm2\">"); 
          out.println("</div>");
          out.println("</head>");
		
		try {

			if (strAction == null) {
				out.println("Please specify the Action");
				out.println("Example : http://<host>:<port>/JMS20_SamplesWeb/DeliveryDelayTest?ACTION=deliveryDelayQueue");
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");
			} else if (strAction.equalsIgnoreCase("deliveryDelayQueue")) {

				deliveryDelayQueue(request, response);
				
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");
			} else if (strAction.equalsIgnoreCase("deliveryDelayTopic")) {

				deliveryDelayTopic(request, response);
				
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");
			} else {
				out.println("Incorrect Action Specified, the valid actions are");
				out.println("ACTION=deliveryDelayQueue");
				out.println("ACTION=deliveryDelayTopic");
				
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			}

		} catch (Exception e) {
			out.println("Something unexpected happened, check the logs or restart the server");
			e.printStackTrace();
		}

	}

	/**
	 * Scenario: Point to Point</br> Connects to ME using connection factory
	 * jndi_JMS_BASE_QCF </br> Sends one message to Queue defined in
	 * jndi_INPUT_Q </br> Receives the message and prints it on console </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */

	public void deliveryDelayQueue(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		out.println("In this scenario, setDeliveryDelay on JMSProducer is being showcased");
		out.println("<br>");
		long deliveryDelay = 10000;
		/*
		 * Lookup Queue Connection Factory from JNDI
		 */
		QueueConnectionFactory cf1 = (QueueConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jndi_JMS_BASE_QCF");

		// Lookup Queue resource from JNDI
		Queue queue = (Queue) new InitialContext()
				.lookup("java:comp/env/jndi_INPUT_Q");

		// Creating Context
		JMSContext jmsContext = cf1.createContext();
		// Creating Producer using JMSContext
		JMSProducer producer = jmsContext.createProducer();
		// setting the delivery delay on producer
		producer.setDeliveryDelay(deliveryDelay);
		// Creating Consumer using JMSContext
		JMSConsumer consumer = jmsContext.createConsumer(queue);
		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();

		message.setText("JMS20 Samples message");
		// Send message to Queue
		producer.send(queue, message);
		long sendTime = System.currentTimeMillis();
		out.println("Time at send " +  sendTime);
		out.println("<br>");
		// receive message from Queue
		TextMessage msg = (TextMessage) consumer.receive(20000);
		
		long receiveTime = System.currentTimeMillis();
		out.println("Received Message Successfully : " + msg);
		out.println("<br>");
		out.println("Time at receive " + receiveTime);
		out.println("<br>");
		out.println("Delivery Delay is enabled to 10000ms and the difference between send time and receive time is seen to be " + (receiveTime - sendTime));
		out.println("<br>");


		
		if (jmsContext != null)
			jmsContext.close();
		out.println("deliveryDelayQueue Completed");
		out.println("<br>");
	}// end of deliveryDelayQueue

	/**
	 * scenario: Performs pub/sub flow</br> Connects to ME using connection
	 * factory jmsTCF </br> Creates a consumer for topic jmsTopic </br>
	 * Publishes a single message to the topic jmsTopic </br> Subscriber
	 * receives the message from topic jmsTopic and the message is printed on
	 * console </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */
	public void deliveryDelayTopic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("In this scenario, setDeliveryDelay on JMSProducer is being showcased");
		out.println("<br>");
		long deliveryDelay = 10000;
		// create a topic connection factory
		TopicConnectionFactory cf1 = (TopicConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jmsTCF");

		// Lookup topic from JNDI
		Topic topic = (Topic) new InitialContext()
				.lookup("java:comp/env/jmsTopic");

		// Creating Context
		JMSContext jmsContext = cf1.createContext();

		// Creating Consumer using JMSContext
		JMSConsumer consumer = jmsContext.createConsumer(topic);

		// Creating Producer using JMSContext
		JMSProducer producer = jmsContext.createProducer();
		// setting the delivery delay on producer
		producer.setDeliveryDelay(deliveryDelay);
		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();

		message.setText("JMS20 Samples message");
		// Publish a message to the topic
		producer.send(topic, message);
		long sendTime = System.currentTimeMillis();
		out.println("Time at send " +  sendTime);
		out.println("<br>");

		TextMessage msg = (TextMessage) consumer.receive(20000);
		long receiveTime = System.currentTimeMillis();
		out.println("Received Message Successfully : " + msg);
		out.println("<br>");
		out.println("Time at receive " + receiveTime);
		out.println("<br>");
		out.println("Delivery Delay is enabled to 10000ms and the difference between send time and receive time is seen to be " + (receiveTime - sendTime));
		out.println("<br>");
		
		if (jmsContext != null)
			jmsContext.close();

		out.println("deliveryDelayTopic Completed");
		out.println("<br>");
	} // deliveryDelayTopic

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

