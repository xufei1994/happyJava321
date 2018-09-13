package cn.tedu.note.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		//发送照片
		byte[] png = createPng();
		response.setContentType("image/png"); 
		response.setContentLength(png.length);
		//在消息body中发送消息数据
		response.getOutputStream().write(png);
	}
	/**
	 * 创建一个图片, 并且编码为 png 格式, 返回
	 * 编码以后的数据 
	 */
	private byte[] createPng() throws IOException{ 
		BufferedImage img = 
			new BufferedImage(200, 80, 
			BufferedImage.TYPE_3BYTE_BGR);
		//在图片上绘制内容
		img.setRGB(100, 40, 0xffffff);
		//将图片编码为PNG
		ByteArrayOutputStream out = 
				new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		out.close();
		byte[] png = out.toByteArray();
		return png;
	}
}








