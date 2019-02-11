package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;

/**  

* <p>Title: SearchService</p>  

* <p>Description: </p>  

* @author 赵天宇

* @date 2018年11月28日  

*/
public interface SearchService {
	
	SearchResult search(String keyword,int page,int rows) throws Exception;
	
}
