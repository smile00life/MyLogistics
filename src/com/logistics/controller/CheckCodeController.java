package com.logistics.controller;
/*
 * 此处为验证码模块
 * 验证码
 * 
 * */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope(value="prototype")
@Controller
@RequestMapping("/")
public class CheckCodeController {
	private char code[]={'a','b','c','d','e','f','g','h','h','i','j','k','l','m','n','o','p',
			'q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6',
			'7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
				'R','S','T','U','V','W','X','Y','Z'};
	private static final int LENGTH=4;
	private static final int WIDTH=80;
	private static final int HEIGHT=30;
			
	@RequestMapping("/ValidateCodeController")
	public void ValidateCodeController(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//设置响应头，没有缓存
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		//创建一个指定宽,高,图片类型的的图片
		BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		//实例化Arial类型的字体
		Font myfont = new Font("Arial",Font.TRUETYPE_FONT,18);
		//实例化一个画布
		Graphics g = image.getGraphics();
		//实例化随机数
		Random rdm = new Random();
		//设置画布的颜色
		g.setColor(Color.LIGHT_GRAY);
		//在画布中画一个坐标为（0,0）的，定宽，定高的矩形
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//设置画布的字体
		g.setFont(myfont);
		//设置画布边框的颜色
		g.setColor(Color.blue);
		
		g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
	    
		//随机生成验证码字符串
		String result="";
		for(int i=0;i<LENGTH;i++){
			result+=code[rdm.nextInt(code.length)];
		}
		//将其保存如session中，方便登录验证
		HttpSession session = req.getSession();
		session.setAttribute("check_code", result);
		
		//在画布中添加验证码以随机产生的颜色
		for(int i=0;i<LENGTH;i++){
			g.setColor(new Color(rdm.nextInt(200),rdm.nextInt(200),rdm.nextInt(200)));
			g.drawString(result.charAt(i)+"",17*i+1,16);
		}
		//在画布中画两条线
		for(int i=0;i<2;i++){
			g.setColor(new Color(rdm.nextInt(200),rdm.nextInt(200),rdm.nextInt(200)));
			int x1 = rdm.nextInt(WIDTH);
			int y1 = rdm.nextInt(HEIGHT);
			int x2 = rdm.nextInt(WIDTH);
			int y2 = rdm.nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
		g.dispose();
		
		OutputStream os= resp.getOutputStream();
		
		ImageIO.write(image, "JPEG", os);
		os.flush();
		os.close();
		
		os=null;
		/*resp.reset(); 
		resp.resetBuffer();*/
	    
		
	}
}
