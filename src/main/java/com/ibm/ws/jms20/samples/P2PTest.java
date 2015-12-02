package com.ibm.ws.jms20.samples;

import java.io.IOException;
import java.io.PrintWriter;


import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class P2PTset
 */
@WebServlet("/P2PTset")
public class P2PTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public P2PTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String strAction = request.getParameter("ACTION");
		PrintWriter output = response.getWriter();
		
	       output.println("<html>");
           output.println("<head>");
           output.println("<title>Reading asynchronously</title>");
           
           output.println("<meta charset=\"utf-8\"></meta>");
           output.println("<title>JMS WAS Classic tests</title>");
           output.println("<style>");
           output.println(".frm1{padding: 15px;background-color: #9666af; margin-bottom: 10px;}");
           output.println(".frm2{padding-left: 25px; font-family: Verdana; color: #440055;}");
           output.println(".big{font-size: 26px; color: white;}");
           output.println(".small{font-size: 12px;}");
           output.println("button, select{padding: 5px; padding-left: 20px; padding-right: 20px; margin:10px; width: 270px}");
           output.println("</style>");
           output.println("</head>");
           output.println("<body>");
           output.println("<body>");
           output.println("<div class=\"frm1\">");
           output.println("<div class=\"big\">  WAS Java EE 7 Sample - JMS</div>");
           output.println("</div>");
           output.println("<div class=\"frm2\">"); 
           output.println("</div>");
           output.println("</head>");
           output.println("<body>");
		
		try {

			if (strAction == null) {
				output.println("Please specify the Action");
				output.println("Example : http://<host>:<port>/JMS20_SamplesWeb/P2PTest?ACTION=sendAndReceive");
			} else if (strAction.equalsIgnoreCase("sendAndReceive")) {
				// call the Send and Receive Message
				sendAndReceive(request, response);
				output.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");
			} else if (strAction.equalsIgnoreCase("sendMessage")) {
				// Send Message only
				sendMessage(request, response);
				output.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			} else if (strAction.equalsIgnoreCase("receiveAllMessages")) {
				// Receive All messages from queue
				receiveAllMessages(request, response);
				output.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			} else if (strAction
					.equalsIgnoreCase("receiveAllMessagesSelectors")) {
				// receive all the messages using message selector
				receiveAllMessagesSelectors(request, response);
				output.println("<br><a href=\"/sample.javaee7.jms/\">Return to main page</a><br>");

			} else {
				output.println("Incorrect Action Specified, the valid actions are <br>");
				output.println("ACTION=sendAndReceive <br>");
				output.println("ACTION=sendMessage <br>");
				output.println("ACTION=receiveAllMessages <br>");
				output.println("ACTION=receiveAllMessagesSelectors <br>");

			}

		} catch (Exception e) {
			output.println("Something unexpected happened, check the logs or restart the server <br>");
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

	public void sendAndReceive(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		out.println("SendAndReceive Started <br>");

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
		// Creating Consumer using JMSContext
		JMSConsumer consumer = jmsContext.createConsumer(queue);
		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();

		message.setText("JMS20 Samples message");
		// Send message to Queue
		producer.send(queue, message);
		out.println("Message sent successfully <br>");

		// receive message from Queue
		TextMessage msg = (TextMessage) consumer.receive();

		out.println("Received Message Successfully : <br>" + msg + "<br>");

		if (jmsContext != null)
			jmsContext.close();
		out.println("SendAndReceive Completed <br>");
	}// end of SendAndReceive

	/**
	 * Scenario: Point to Point </br> Connects to ME using connection factory
	 * jndi_JMS_BASE_QCF </br> Sends one message to Queue defined in
	 * jndi_INPUT_Q </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */

	public void sendMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		out.println("SendMessage Started <br>");

		// create a queue connection factory
		QueueConnectionFactory cf1 = (QueueConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jndi_JMS_BASE_QCF");
		// create a queue by performing jndi lookup
		Queue queue = (Queue) new InitialContext()
				.lookup("java:comp/env/jndi_INPUT_Q");

		// Creating Context
		JMSContext jmsContext = cf1.createContext();
		// Creating Producer using JMSContext
		JMSProducer producer = jmsContext.createProducer();

		// Creating Text Message using JMSContext
		TextMessage message = jmsContext.createTextMessage();

		message.setStringProperty("COLOR", "BLUE");
		message.setText("JMS20 Samples message");
		// Sending message to Queue
		producer.send(queue, message);

		out.println("Message sent successfuly <br>");

		if (jmsContext != null)
			jmsContext.close();
		out.println("SendMessage Completed <br>");

	}// end of SendMessage

	/**
	 * Scenario: Point to Point </br> Connects to ME using connection factory
	 * jndi_JMS_BASE_QCF </br> Sends one message to the queue specified in
	 * jndi_INPUT_Q(i.e Queue Queue1) </br> Receives all the messages from the
	 * above Queue and prints them on console </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */

	public void receiveAllMessages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("ReceiveAllMessages Started <br>");
		// create queue connection factory
		QueueConnectionFactory cf1 = (QueueConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jndi_JMS_BASE_QCF");

		// create a queue by looking up from the JNDI repository
		Queue queue = (Queue) new InitialContext()
				.lookup("java:comp/env/jndi_INPUT_Q");

		// Creating Context
		JMSContext jmsContext = cf1.createContext();

		// Creating Consumer using JMSContext
		JMSConsumer consumer = jmsContext.createConsumer(queue);

		TextMessage msg = null;

		do {
			msg = (TextMessage) consumer.receive(2000);
			if (msg != null)
				out.println("Received  messages <br>" + msg+"<br>");
		} while (msg != null);

		if (jmsContext != null)
			jmsContext.close();

		out.println("ReceiveAllMessages Completed <br>");

	} // end of ReceiveAllMessages

	/**
	 * Scenario: Point to Point</br> Connects to ME using connection factory
	 * jndi_JMS_BASE_QCF </br> Sends one message to Queue jndi_INPUT_Q(i.e Queue
	 * Queue1) </br> Receives the messages with selector COLOR='BLUE' and prints
	 * them on console </br>
	 * 
	 * @param request
	 *            HTTP request
	 * @param response
	 *            HTTP response
	 * @throws Exception
	 *             if an error occurs.
	 */

	public void receiveAllMessagesSelectors(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("ReceiveAllMessagesSelectors Started <br>");

		// create a queue connection factory
		QueueConnectionFactory cf1 = (QueueConnectionFactory) new InitialContext()
				.lookup("java:comp/env/jndi_JMS_BASE_QCF");
		// create a queue by looking up from the JNDI repository
		Queue queue = (Queue) new InitialContext()
				.lookup("java:comp/env/jndi_INPUT_Q");

		// Creating Context
		JMSContext jmsContext = cf1.createContext();

		// Creating Consumer using JMSContext
		JMSConsumer consumer = jmsContext.createConsumer(queue, "COLOR='BLUE'");

		TextMessage msg = null;

		do {
			msg = (TextMessage) consumer.receive(2000);
			if (msg != null)
				out.println("Received  messages <br>" + msg+"<br>");
		} while (msg != null);

		if (jmsContext != null)
			jmsContext.close();

		out.println("ReceiveAllMessagesSelectors Completed <br>");

	} // end of ReceiveAllMessagesSelectors

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
