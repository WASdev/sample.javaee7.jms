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
 
package com.ibm.ws.jms20.samples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
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
 * Servlet implementation class PublisherSubscriberTest
 */
@WebServlet("/PublisherSubscriberTest")
public class PublisherSubscriberTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublisherSubscriberTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
				out.println("Please specify the Action <br>");
				out.println("Example : http://<host>:<port>/JMS20_SamplesWeb/PublisherSubscriberTest?ACTION=consumerTest<br>");
			} else if (strAction.equalsIgnoreCase("consumerTest")) {
				// Create a non durable subscriber and publish and receive the
				// message from topic
				consumerTest(request, response);
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			} else if (strAction.equalsIgnoreCase("durableConsumer")) {
				// Create a Durable subscriber and publish and receive the
				// message from topic
				durableConsumer(request, response);
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			} else if (strAction.equalsIgnoreCase("publishMessages")) {
				// Publish 5 messages to the topic
				publishMessages(request, response);
				out.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			} else {
				out.println("Incorrect Action Specified, the valid actions are <br>");
				out.println("ACTION=consumerTest <br>");
				out.println("ACTION=durableConsumer <br>");
				out.println("ACTION=publishMessages <br>");								
			}

		} catch (Exception e) {
			out.println("Something unexpected happened, check the logs or restart the server <br>");
			e.printStackTrace();
		}
	}

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
	public void consumerTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("consumerTest Started <br>");
		
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
		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();

		message.setText("JMS20 Samples message");
		// Publish a message to the topic
		producer.send(topic, message);

		TextMessage msg = (TextMessage) consumer.receive(2000);
		if (null == msg) {
			throw new Exception("No message received");
		} else {
			out.println("Received message for subscriber <br> " + msg+"<br>");
		}

		if (jmsContext != null)
			jmsContext.close();

		out.println("consumerTest Completed <br>");

	} // consumerTest

	/**
	 * Test scenario: Performs Durable pub/sub flow</br> Connects to ME using
	 * connection factory jmsTCF </br> Creates durable subscriber(named
	 * DURATEST) for topic jmsTopic </br> Publishes a single message to the
	 * topic jmsTopic </br> Subscriber receives the message from topic jmsTopic
	 * </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */
	public void durableConsumer(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("Unshared durableConsumer Started <br>");
		
		// create a topic connection factory
		TopicConnectionFactory cf1 = (TopicConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jmsTCF");
		
		// lookup topic from JNDI
		Topic topic = (Topic) new InitialContext()
				.lookup("java:comp/env/jmsTopic");

		// Creating Context
		JMSContext jmsContext = cf1.createContext();
		//setting the client identifier
		try{
		jmsContext.setClientID("cID1");
		}catch(Exception e){
			
		}
		
		// Creating Consumer using JMSContext
		JMSConsumer consumer = jmsContext.createDurableConsumer(topic,
				"DURATEST1");

		// Creating Producer using JMSContext
		JMSProducer producer = jmsContext.createProducer();
		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();
		message.setText("JMS20 Samples message");

		// publish the message
		producer.send(topic, message);

		TextMessage msg = null;
		do {
			msg = (TextMessage) consumer.receive(2000);
			if (msg != null)
				out.println("Received  messages <br>" + msg+"<br>");
		} while (msg != null);

		if (jmsContext != null)
			jmsContext.close();

		out.println(" Unshared durableConsumer Completed <br>");
	}// end of durableConsumer

	/**
	 * Test scenario: Publish messages to Topic</br> Connects to ME using
	 * connection factory jmsTCF </br> Publishes 5 messages to the topic
	 * jmsTopic </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */
	public void publishMessages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("PublishMessage Started <br>");
		
		TopicConnectionFactory cf1 = (TopicConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jmsTCF");
		Topic topic = (Topic) new InitialContext()
				.lookup("java:comp/env/jmsTopic");

		int msgs = 5;

		// Creating Context
		JMSContext jmsContext = cf1.createContext();
		//setting the client identifier
		try{
		jmsContext.setClientID("cID2");
		}catch(Exception e){
		
		}
		// Creating Consumer using JMSContext
		@SuppressWarnings("unused")
		JMSConsumer consumer = jmsContext.createDurableConsumer(topic,
				"DURATEST2");

		// Creating Producer using JMSContext
		JMSProducer producer = jmsContext.createProducer();
		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();
		message.setText("JMS20 Samples message");

		// send 5 messages
		for (int i = 0; i < msgs; i++) {
			producer.send(topic, message);
		}
		if (jmsContext != null)
			jmsContext.close();
		out.println(msgs + "Messages published <br>");
		out.println("PublishMessage Completed <br>");
	}// PublishMessage




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
