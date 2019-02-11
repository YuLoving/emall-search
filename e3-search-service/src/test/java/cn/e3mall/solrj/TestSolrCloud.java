package cn.e3mall.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;


/**  

* <p>Title: TestSolrCloud</p>  

* <p>Description: </p>  

* @author 赵天宇

* @date 2018年12月25日  

*/
public class TestSolrCloud {
		
	
	//CloudSolr集群的插入
	@Test
	public void testsolrclouds() throws Exception{
		//1。创建一个集群的连接，应该使用CloudSolrServer创建。
		 CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.25.131:2181,192.168.25.131:2182,192.168.25.131:2183");
		//2。zKHost：zookeeper的地址列表
		//3。设置一个defaultCollection属性。
		 cloudSolrServer.setDefaultCollection("collection2");
		//4。创建一个文档对象。
		SolrInputDocument document = new SolrInputDocument();
		//5。向文档中添加域。
		document.setField("id", "cloudsolr01");
		document.addField("item_title", "测试商品");
		document.addField("item_price", "100");
		//6。把文件写入索引库。
		cloudSolrServer.add(document);
		//7。提交。
		cloudSolrServer.commit();
	}
	
	
	@Test
	//CloudSolr集群的查询
	public void queryCloudSolr() throws Exception{
		//1.通过CloudSolrServer创建连接，以及一个参数，ZKHOST：zookeeper的地址列表
		CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.25.131:2181,192.168.25.131:2182,192.168.25.131:2183");
		//2.设置defaultcollection属性
		cloudSolrServer.setDefaultCollection("collection2");
		//3.创建查询solrquery
		SolrQuery solrQuery = new SolrQuery();
		//4.设置查询条件
		solrQuery.setQuery("*:*");
		//5.执行查询
		QueryResponse response = cloudSolrServer.query(solrQuery);
		
		//取查询结果
		SolrDocumentList list = response.getResults();
		//打印
		System.out.println("总记录数："+list.getNumFound());
		
		for (SolrDocument solrDocument : list) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
	}
}
