package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;


import com.logistics.model.TransportInfo;

/*
 * 车辆管理
 * */
public interface ITransportMapper {

	//获取车辆数据行数
		@Select("CALL getTransportCount()")
		@Options(statementType = StatementType.CALLABLE)
		public int getTransportCount();
		
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
		 * 
		 * select * from sss LIMIT 1,5;
		 * 表示查找第2行开始到第6行之间的数据
		 * */
		//获取车辆列表信息
		@Select("CALL getTransportList(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public List<TransportInfo> getTransportList(int startIndex,int pageSize);
		
		//根据ID查询我们的车辆
		//@Select("select * from announce where transport_id=#{id}")
		@Select("CALL getTransportById(#{0})")
		@Options(statementType = StatementType.CALLABLE)
		public TransportInfo getTransportById(int transport_id);
		
		//发布车辆
		@Insert("CALL getCreateTransport(#{transport_name},#{transport_type},#{plate_number},#{transport_driver},#{transport_info})")
		@Options(statementType = StatementType.CALLABLE)
		public int getCreateTransport(TransportInfo tf);
		
		//修改车辆
		@Update("CALL getUpdateTransport(#{transport_name},#{transport_type},#{plate_number},#{transport_driver},#{transport_info},#{transport_id})")
		@Options(statementType = StatementType.CALLABLE)
		public int getUpdateTransport(TransportInfo tf);
		
		
		//删除车辆
		@Delete("CALL getDelTransport(#{0})")
		@Options(statementType = StatementType.CALLABLE)
		public int getDelTransport(int transport_id);
}
