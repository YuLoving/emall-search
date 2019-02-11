package cn.e3mall.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**  

* <p>Title: MyMessageListener</p>  

* <p>Description: </p>  

* @author 赵天宇

* @date 2019年1月10日  

*/
public class MyMessageListener implements MessageListener {

	/* *
	 * 取消息的内容
	 */
	@Override
	public void onMessage(Message message) {
	
		TextMessage textMessage=(TextMessage) message;
		try {
			String text = textMessage.getText();
			System.out.println(text);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
