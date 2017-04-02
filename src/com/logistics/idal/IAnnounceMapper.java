package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.AnnounceInfo;

public interface IAnnounceMapper {

	//获取公告数据行数
	@Select("CALL getAnnounceCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getAnnounceCount();
	
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
	//获取公告列表信息
	@Select("CALL getAnnounceList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<AnnounceInfo> getAnnounceList(int startIndex,int pageSize);
	
	//根据ID查询我们的公告
	//@Select("select * from announce where announce_id=#{id}")
	@Select("CALL getAnnounceById(#{0})")
	@Options(statementType = StatementType.CALLABLE)
	public AnnounceInfo getAnnounceById(int announce_id);
	
	//发布公告
	@Insert("CALL createAnnounce(#{announce_Title},#{announce_Content},#{manager_id},#{manager_name})")
	@Options(statementType = StatementType.CALLABLE)
	public int createAnnounce(AnnounceInfo ai);
	
	//修改公告
	@Update("CALL getUpdateAnnounce(#{announce_Title},#{announce_Content},#{manager_id},#{manager_name},#{announce_Id})")
	@Options(statementType = StatementType.CALLABLE)
	public int updateAnnounce(AnnounceInfo af);
	//@Update("update announce set annnounce_Title=#{announce_Title},announce_Content=#{announce_Content},manager_id=#{manager_id} where announce_id=#{announce_id}")
	
	//删除公告
	@Delete("CALL delAnnounce(#{0})")
	@Options(statementType = StatementType.CALLABLE)
	public int delAnnounce(int announce_id);
	
	//查看公共list
	@Select("CALL detailsAnnounceInfo()")
	@Options(statementType = StatementType.CALLABLE)
	public List<AnnounceInfo> detailsAnnounceInfo();
	
}
