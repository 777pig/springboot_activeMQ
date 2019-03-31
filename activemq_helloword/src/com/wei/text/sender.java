package com.wei.text;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;  


/**
 * 有2种发送消息的形式，一种发布订阅  (topic ) 一种 点对点 (queue)
 * 发布订阅要求必须实时在线否则接受不到消息 , 点对点则可以重新接受到这个消息
 *  #默认使用kahaDB来存储文件
 * @author 86185
 * @time 2019年3月31日 下午2:51:24
 */
public class sender {
	 // default connection username  
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;  
    // default connection password  
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;  
    // default connection url  
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
	public static void main(String[] args) {
		sort_send();
	}
	
	private static void Method(){  
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
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            

            // create a queue name= helloworld ，创建目的地 ，根据发布模式（ptp,pb）不同有很多其他的选择
            destination = session.createQueue("helloworld3");  
            // create MessageProducer  
            messageProducer = session.createProducer(destination);  
            // 设置持久化模式
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            
            for (int i = 0; i < 10; i++) {  
            	TextMessage msg = session.createTextMessage("hello" + i);  
//                messageProducer.send(msg);
                //消息队列，签收模式，优先级，存活时间
                messageProducer.send(destination,msg, DeliveryMode.NON_PERSISTENT,i,(1000*60));
                
            }
            //如果是事务模式才提交
            session.commit();  
            
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
	 * 顺序发送
	 */
	private static void sort_send(){ 
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
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            

            // create a queue name= helloworld ，创建目的地 ，根据发布模式（ptp,pb）不同有很多其他的选择
            destination = session.createQueue("sort_send");  
            // create MessageProducer  
            messageProducer = session.createProducer(destination);  
            // 设置持久化模式
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            	TextMessage msg = session.createTextMessage("hello2");  
//                messageProducer.send(msg);
                //消息队列，签收模式，优先级，存活时间
                messageProducer.send(destination,msg, DeliveryMode.NON_PERSISTENT,5,(1000*60));

            	TextMessage msg2 = session.createTextMessage("hello1");  
//              messageProducer.send(msg);
              //消息队列，签收模式，优先级，存活时间
              messageProducer.send(destination,msg2, DeliveryMode.NON_PERSISTENT,1,(1000*60));

          	TextMessage msg3 = session.createTextMessage("hello3");  
//          messageProducer.send(msg);
          //消息队列，签收模式，优先级，存活时间
          messageProducer.send(destination,msg3, DeliveryMode.NON_PERSISTENT,9,(1000*60));
              
                
            //如果是事务模式才提交
            session.commit();  
            
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
	
