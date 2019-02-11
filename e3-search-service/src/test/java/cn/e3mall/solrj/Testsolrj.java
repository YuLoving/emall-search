
package cn.e3mall.solrj;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**  

* <p>Title: Testsolrj</p>  

* <p>Description: </p>  

* @author zty  

* @date 2018年10月19日  

*/
public class Testsolrj {
	
/*	@Test
	//在索引库里面添加文档
	public void addDocument() throws Exception{
		//创建一个solrserver对象，创建一个连接，参数solr服务的URL
		SolrServer SolrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//创建一个文档对象solrinputDocument
		SolrInputDocument document = new SolrInputDocument();
		//向文档对象中添加域。文档中必须包含ID，所有的域的名称必须在schema.xml中定义
		document.addField("id", "doc1");
		document.addField("item_title", "商品测试01");
		document.addField("item_price", 1000);
		//把文档写入索引库
		SolrServer.add(document);
		//提交
		SolrServer.commit();
	}
		//创建一个solrserver对象，创建一个连接，参数solr服务的URL
HttpSolrServer solrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//创建一个文档对象solrinputDocument
		SolrInputDocument document = new SolrInputDocument();
		//向文档对象中添加域。文档中必须包含ID，所有的域的名称必须在schema.xml中定义
		document.addField("id", "doc4");
		document.addField("item_title", "三山街委员会");
		document.addField("item_price", "20000");
		//把文档写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}*/

	/*@Test
	//在索引库里面删除文档
		public void deletedocument() throws Exception{
		
		//创建一个solrserver对象，创建一个连接，参数solr服务的URL
		SolrServer SolrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//删除(2个方式)
		SolrServer.deleteById("doc4"); //通过ID查
		//SolrServer.deleteById("id:doc2");// //通过查询条件查
		//提交
		SolrServer.commit();
		
	}*/
	
	
	@Test
	//查询索引库
		public void query() throws Exception{
		//创建一个solrsever对象
		SolrServer SolrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//创建一个solrquery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
			query.setQuery("*:*");
			//query.set("q", "*:*");
		//执行查询，取得QueryResponse对象
			QueryResponse queryResponse = SolrServer.query(query);
		//取文档列表，取查询结果的总记录数
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			System.out.println("查询结果总记录数："+solrDocumentList.getNumFound());
		//遍历文档列表，取域的内容
			for (SolrDocument solrDocument : solrDocumentList) {
				System.out.println(solrDocument.get("id"));
				System.out.println(solrDocument.get("item_title"));
				System.out.println(solrDocument.get("item_price"));
			}
		
	}
	
	
	//带高亮显示
	public void queryDocumentWithHighLighting()throws Exception{
		//创建一个solrsever对象
		HttpSolrServer solrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//创建一个solrquery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
					query.setQuery("手机");//设置查询对象
						//设置分页的起始页和每页的数据
					query.setStart(0);
					query.setRows(20);
					//设置默认搜索域
					query.set("df", "item_title");
					//开启高亮
					query.setHighlight(true);
					//设置高亮的字段区域
					query.addHighlightField("item_title");
					//设置高亮的字段区域的 前缀和后缀
					query.setHighlightSimplePre("<em>");
					query.setHighlightSimplePost("</em>");
					
		//执行查询，取得QueryResponse对象
				QueryResponse queryResponse = solrServer.query(query);
		//取文档列表，取查询结果的总记录数
				SolrDocumentList solrDocumentList = queryResponse.getResults();
				System.out.println("查询结果总记录数："+solrDocumentList.getNumFound());
		//遍历文档列表，取域的内容
				
				Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
				for (SolrDocument solrDocument : solrDocumentList) {
					System.out.println(solrDocument.get("id"));
					//调取高亮显示的字段区域
					String title="";
					List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
					if (list!=null && list.size()>0) {
						title=list.get(0);
					} else {
						title=(String) solrDocument.get("item_title");
					}
					System.out.println(title);
					System.out.println(solrDocument.get("item_price"));
				}
				
	}
	
	
	
	
}
