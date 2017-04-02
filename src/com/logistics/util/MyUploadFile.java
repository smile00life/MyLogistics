package com.logistics.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MyUploadFile {
	// 上传文件存储目录
	// private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public static void upload(HttpServletRequest request, String path)
			throws IOException {
		IsFileUpload(request, path, null);
	}

	public static void upload(HttpServletRequest request, String path,
			String[] filetype) throws IOException {
		IsFileUpload(request, path, filetype);
	}

	public static boolean IsFileUpload(HttpServletRequest request, String path,
			String[] filetype) throws IOException {
		request.setCharacterEncoding("UTF-8");
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止

			request.setAttribute("message",
					"Error: 表单必须包含 enctype=multipart/form-data");
			return false;
		}

		/**
		 * 上传数据及保存文件
		 */

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String uploadSaveFolder = format.format(new Date());
		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = setrootPath(request, path + "\\" + uploadSaveFolder);

		//存入数据库的文件路径
		String uploadFile2Database=path+"\\"+uploadSaveFolder+"\\";
		
		System.out.println("uploadPath" + uploadPath);

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			// @SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {

				if (filetype != null) {
					// 迭代表单数据判断上传文件扩展名
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							// 处理不在表单中的字段
							String fileName = new File(item.getName())
									.getName();
							// 获取文件扩展名
							String pexfix = fileName.substring(
									fileName.lastIndexOf("."),
									fileName.length());
							// 判断扩展名是否符合要求
							// String[] filetype={".jpg",".png"};
							if (!IsAllowFile(filetype, pexfix)) {
								request.setAttribute("message", "上传失败，文件类型不符！");
								return false;
							}
						}
					}
				}
				
				//List<String> list=new ArrayList<String>();
				
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						// 获取文件扩展名
						String pexfix = fileName.substring(
								fileName.lastIndexOf("."), fileName.length())
								.toLowerCase();
						// 文件名根据时间自定义
						Date dNowDate = new Date();
						SimpleDateFormat ft = new SimpleDateFormat(
								"yyyyMMddhhmmss");
						String saveFileName = ft.format(dNowDate) + getUUID()
								+ pexfix;

						// System.out.println("fileName:" + fileName);

						String filePath = uploadPath + saveFileName;
						
						
						
						
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 数据库保存文件路径
						String savefilepath=uploadFile2Database+saveFileName;
						
						System.out.println("数据库保存路径:"+savefilepath);
						// 保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("message", "文件上传成功！");
						request.setAttribute("file", savefilepath);
					}
				}
			}
			return true;
		} catch (Exception ex) {
			request.setAttribute("message", "错误信息: " + ex.getMessage());
			return false;
		}
	}

	// 文件扩展名过滤方法
	private static boolean IsAllowFile(String[] mypexfix, String pexfix) {
		boolean flag = false;

		for (String s : mypexfix) {
			// 扩展名全部转小写匹配
			if (s.matches(pexfix.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
	}

	// 初始化文件上传路径
	private static String setrootPath(HttpServletRequest req, String mypath) {
		// 获得服务器的名字
		String serverName = req.getServerName();
		// 取得互联网程序的绝对地址
		String realPath = req.getRealPath(serverName);
		realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
		String rootPath = realPath + "\\" + mypath + "\\";
		return rootPath;
	}

	public static synchronized String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}
	
	
	//返回list数组
	public static List<String> FileUpload(HttpServletRequest request, String path,
			String[] filetype) throws IOException {
		request.setCharacterEncoding("UTF-8");
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止

			request.setAttribute("message",
					"Error: 表单必须包含 enctype=multipart/form-data");
			return null;
		}

		/**
		 * 上传数据及保存文件
		 */

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String uploadSaveFolder = format.format(new Date());
		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = setrootPath(request, path + "\\" + uploadSaveFolder);

		//存入数据库的文件路径
		String uploadFile2Database=path+"\\"+uploadSaveFolder+"\\";
		
		System.out.println("uploadPath" + uploadPath);

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			// @SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			List<String> list=new ArrayList<String>();
			
			if (formItems != null && formItems.size() > 0) {

				if (filetype != null) {
					// 迭代表单数据判断上传文件扩展名
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							// 处理不在表单中的字段
							String fileName = new File(item.getName())
									.getName();
							// 获取文件扩展名
							String pexfix = fileName.substring(
									fileName.lastIndexOf("."),
									fileName.length());
							// 判断扩展名是否符合要求
							// String[] filetype={".jpg",".png"};
							if (!IsAllowFile(filetype, pexfix)) {
								request.setAttribute("message", "上传失败，文件类型不符！");
								return null;
							}
						}
					}
				}
				
				
				
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						// 获取文件扩展名
						String pexfix = fileName.substring(
								fileName.lastIndexOf("."), fileName.length())
								.toLowerCase();
						// 文件名根据时间自定义
						Date dNowDate = new Date();
						SimpleDateFormat ft = new SimpleDateFormat(
								"yyyyMMddhhmmss");
						String saveFileName = ft.format(dNowDate) + getUUID()
								+ pexfix;

						// System.out.println("fileName:" + fileName);

						String filePath = uploadPath + saveFileName;
						
						
						
						
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 数据库保存文件路径
						String savefilepath=uploadFile2Database+saveFileName;
						list.add(savefilepath);
						System.out.println("数据库保存路径:"+savefilepath);
						// 保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("message", "文件上传成功！");
						request.setAttribute("file", savefilepath);
					}
				}
			}
			return list;
		} catch (Exception ex) {
			request.setAttribute("message", "错误信息: " + ex.getMessage());
			return null;
		}
	}

}
