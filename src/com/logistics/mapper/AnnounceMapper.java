package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IAnnounceMapper;
import com.logistics.idal.IPagerMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.CityInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class AnnounceMapper {

    //获取公告列表总行数
	public int getAnnounceCount(){
		
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
	    IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
		int row= mapper.getAnnounceCount();
		session.close();
		return row;
	}
	
	//获取公告列表信息
	public List<AnnounceInfo> getAnounceList(Map<String,Object> map){
		//String s=(String)map.get("startIndex");
		//String p=(String)map.get("pageSize");
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
	    IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
	    
		List<AnnounceInfo> annlist =mapper.getAnnounceList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

		session.close();
		return annlist;
	}
	
	//发布公告
	public boolean createAnnounce(AnnounceInfo af)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
	    IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
		int count = mapper.createAnnounce(af);
		System.out.println(count);
		if(count>0)
		{
			session.close();
			System.out.println("发布成功！");
//			JOptionPane.showMessageDialog(null, "更新成功!");
			return true;
		}else {
			return false;
		}
	}
	
	//编辑公告
	//修改公告
	public boolean updateAnnounce(AnnounceInfo af)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
		
		int rows = mapper.updateAnnounce(af); 
		if(rows>0)
		{
			System.out.println("修改成功");
			session.close();
			return true;
		}
		else {
			return false;
		}	
	}
	
	//删除公告
	public boolean delAnnounce(int announce_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
		int rows = mapper.delAnnounce(announce_id);
		if(rows>0)
		{
			System.out.println("删除成功！");
			return true;
		}else {
			return false;
		}
	}
	
	//根据ID查询公告
	public AnnounceInfo getAnnounceById(int id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
		
		AnnounceInfo listaf = mapper.getAnnounceById(id);
		System.out.println("查询成功，标题为："+listaf.getAnnounce_Title());
		session.close();
		return listaf;
	}
	
	//查看公告list,用户
	public List<AnnounceInfo> detailsAnnounceInfo()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);
		List<AnnounceInfo> listAnnounceInfos = mapper.detailsAnnounceInfo();
		return listAnnounceInfos;
		
	}
}
