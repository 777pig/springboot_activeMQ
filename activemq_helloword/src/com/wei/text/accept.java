package com.wei.text;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class accept {

	 // default connection username  
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;  
    // default connection password  
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;  
    // default connection url  
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    public static void main(String[] args) {
    	text_onlyconsumer();
		}
		
	private static void Method(){
		System.out.println("消费");
		
	    ConnectionFactory cf = null;  
	    Connection connection = null;  
	    // session used to revieve or send  
	    Session session = null;  
	    // message destination  
	    Destination destination = null;  
	    MessageProducer messageProducer = null;  
	    // create ConnectionFactory  
	    try {  
	        cf = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);  
	        // create activemq connection  
	        connection = cf.createConnection();  
	        connection.start();  
	        // create session  是否开启事务，签收模式
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
	
	        // 创建存储数据的目的地 ，根据发布模式（ptp,pb）不同有很多其他的选择 
	        destination = session.createQueue("sort_send");  
	        // create prducter
	         MessageConsumer consumer=session.createConsumer(destination);
	         
	         while(Boolean.TRUE) {
	        	 TextMessage data=(TextMessage)consumer.receive();
//	        	 data.acknowledge(); //第2种事务模式下需调用它手动签收
	        	 if(data==null) break;

	        	 String str=data.getText();
	        	 System.out.println(str);
	         }
	         
	    } catch (JMSException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            connection.close();  
	        } catch (JMSException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }
	  }
	
	/**
	 * 配合发送者独有消费，保证接受的顺序
	 */
	public static void text_onlyconsumer() {

		System.out.println("消费");
		
	    ConnectionFactory cf = null;  
	    Connection connection = null;  
	    // session used to revieve or send  
	    Session session = null;  
	    // message destination  
	    Destination destination = null;  
	    MessageProducer messageProducer = null;  
	    // create ConnectionFactory  
	    try {  
	    	
	        cf = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
	        
	       
	        // create activemq connection  
	        connection = cf.createConnection();  
	        connection.start();  
	        // create session  是否开启事务，签收模式
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
	
	        // 创建存储数据的目的地 ，根据发布模式（ptp,pb）不同有很多其他的选择 
//	        destination = session.createQueue("sort_send");
	        destination=new ActiveMQQueue("sort_send?consumer.exclusive=true");
//			new ActiveMQQueue("sort_send.QUEUE?consumer.exclusive=true");
			
	        // create prducter
	         MessageConsumer consumer=session.createConsumer(destination);
	         
	         while(Boolean.TRUE) {
	        	 TextMessage data=(TextMessage)consumer.receive();
//	        	 data.acknowledge(); //第2种事务模式下需调用它手动签收
	        	 if(data==null) break;

	        	 String str=data.getText();
	        	 System.out.println(str);
	         }
	         
	    } catch (JMSException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            connection.close();  
	        } catch (JMSException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }
		
	}

}
