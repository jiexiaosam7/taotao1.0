package com.taotao.manage.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.manage.vo.PicUploadResult;

@RequestMapping("/pic")
@Controller
public class PicUploadController {
	
	//允许上传的图片类型
	private static final String[] IMAGE_TYPE = {".jpg",".png",".bmp",".jpeg",".gif"};
	
	/**
	 * 将商品编辑页面中上传的多个图片到分布存储服务器fastDFS中并且，将这些图片的存储路径保存到商品的数据库表中
	 * @param multipartFile
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public PicUploadResult upload(@RequestParam("uploadFile")MultipartFile multipartFile) {
		PicUploadResult picUploadResult = new PicUploadResult();
		picUploadResult.setError(1);//非0表示上传失败
		
		boolean isLegal = false;
		//1、校验图片
		for (String type : IMAGE_TYPE) {
			if(multipartFile.getOriginalFilename().lastIndexOf(type) > 0) {
				isLegal = true;
				break;
			}
		}
		
		if(isLegal) {
			try {
				//校验图片内容
				BufferedImage image = ImageIO.read(multipartFile.getInputStream());
				picUploadResult.setWidth(image.getWidth()+"");
				picUploadResult.setHeight(image.getHeight()+"");
				
				//2、上传图片到fastDFS
				//设置追踪服务器配置文件的路径
				String conf_filename = this.getClass().getClassLoader().getResource("tracker.conf").getPath();
				
				ClientGlobal.init(conf_filename);
				
				//创建TrackerClient
				TrackerClient trackerClient = new TrackerClient();
				
				//创建trackerServer，根据trackerClient创建
				TrackerServer trackerServer = trackerClient.getConnection();
				
				//创建storageServer
				StorageServer storageServer = null;
				
				//创建存储对象StorageClient
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				
				//上传文件
				/**
				 * 参数1：文件名称（包括路径）
				 * 参数2：文件拓展名
				 * 参数3：文件拓展信息（meta）可以为Null
				 */
				String file_ext_name = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
				
				String[] fileInfos = storageClient.upload_appender_file(multipartFile.getBytes(), file_ext_name, null);
				
				/**
				 * 数组里面的内容为，如：
				 * group1
					M00/00/00/wKgMqFmfhoGAIzJGAABw0se6LsY966.jpg
				 */
				if(fileInfos != null && fileInfos.length > 1) {
					String groupName = fileInfos[0];//组名
					String filename = fileInfos[1];//文件相对路径
					
					//获取存储服务器的ip；因为在存储服务器上配置了nginx
					ServerInfo[] serverInfos = trackerClient.getFetchStorages(trackerServer, groupName, filename);
					
					//组成一个完整的图片地址
					String url = "http://" + serverInfos[0].getIpAddr() + "/" + groupName + "/" + filename;
					
					picUploadResult.setError(0);
					picUploadResult.setUrl(url);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//返回
		return picUploadResult;
	}

}
