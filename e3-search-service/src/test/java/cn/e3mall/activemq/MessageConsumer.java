package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**  

* <p>Title: MessageConsumer</p>  

* <p>Description: </p>  

* @author 赵天宇

* @date 2019年1月10日  

*/
public class MessageConsumer {
	
	@Test
	public void msgConsumer() throws Exception{
		//初始化spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//等待
		System.in.read();
	}
}
