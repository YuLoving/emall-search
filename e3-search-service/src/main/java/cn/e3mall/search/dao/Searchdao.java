package cn.e3mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

/**  

* <p>Title: Searchdao</p>  

* <p>Description: 商品搜索dao</p>  

* @author 赵天宇

* @date 2018年11月28日  

*/
@Repository
public class Searchdao {
	@Autowired
	private SolrServer solrServer;
	
	
		//根据查询条件查询索引库
	public SearchResult search(SolrQuery query) throws Exception{
		//根据query查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//取查询结果总记录数
		long numFound = solrDocumentList.getNumFound();
		SearchResult searchResult = new SearchResult();
		searchResult.setRecordCount(numFound);
		//取商品列表，需要高亮显示
				 //取高亮显示
				Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
				//商品列表list
				List<SearchItem> list = new ArrayList<>();
				for (SolrDocument solrDocument : solrDocumentList) {
					SearchItem searchItem = new SearchItem();
					searchItem.setId((String) solrDocument.get("id"));
					//取高亮显示
					List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
					String title="";
					if (list2!=null && list2.size()>0) {
						title=list2.get(0);
					} else {
						title= (String) solrDocument.get("item_title");		
					}
					searchItem.setTitle(title);
					searchItem.setPrice((long) solrDocument.get("item_price"));
					//添加到商品列表
					list.add(searchItem);
				}
		searchResult.setItemList(list);	
		//返回结果
		return searchResult;
		
		
		
		
	}
}
