
package cn.e3mall.search.mapper;

import java.util.List;

import cn.e3mall.common.pojo.SearchItem;

/**  

* <p>Title: Itemmapper</p>  

* <p>Description: </p>  

* @author zty  

* @date 2018年10月19日  

*/
public interface Itemmapper {

	//查询所有商品列表
	List<SearchItem> getItemList();
	
	//通过ID查询
	SearchItem getbyid(long itemid);
}
