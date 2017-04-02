package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.CityInfo;

public interface IPagerMapper {

	//测试方法，计算total总数
		@Select("CALL getCityTotal()")
		@Options(statementType = StatementType.CALLABLE)
		public int getCityTotal();
		
		/*
		 * 参数：
		 * 第一个：当前页面数据的起始id
		 * 第二个：当前页面显示的数据内容
		 * 解释：假如数据有17条，每页显示5条，
		 * 第一页显示1-5
		 * 第二页显示6-10
		 * 第三页显示11-15
		 * 第四页显示16-17
		 * 如何取这里不管，这里只是分页查询
		 * */
		@Select("CALL getCityPager(#{0},#{1})")
		//@Options(statementType = StatementType.CALLABLE)
		public List<CityInfo> getCityPager(int startIndex,int pageSize);
}
