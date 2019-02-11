package cn.e3mall.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.Itemmapper;

/**  

* <p>Title: AddItemListener</p>  

* <p>Description: 监听商品信息，接收信息后，将对应的商品信息同步到索引库</p>  

* @author 赵天宇

* @date 2019年1月10日  

*/
public class AddItemListener implements MessageListener {
	@Autowired
	private Itemmapper itemmapper;
	@Autowired
	private SolrServer solrServer;
	
	
	@Override
	public void onMessage(Message message) {
		try {
			//1.从消息中取出商品ID
			TextMessage textMessage=(TextMessage) message;
			String text = textMessage.getText();
			Long itemid = new Long(text);
			
					//防止数据还没有入数据库，可以先等待一会
					Thread.sleep(1000);
			//2.根据商品ID来查询商品信息
			SearchItem searchItem = itemmapper.getbyid(itemid);
			//3.创建一个文档对象
			SolrInputDocument document = new SolrInputDocument();
			//4.向文档对象中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			//5.把文档写入索引库
			solrServer.add(document);
			//6.提交
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
