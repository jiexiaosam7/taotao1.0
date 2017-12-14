package cn.itcast.fastdfs;

import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDFSTest {

	@Test
	public void test() throws Exception {
		
		//设置追踪服务器配置文件的路径
		String conf_filename = ClassLoader.getSystemResource("tracker.conf").getPath();
		
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
		String[] fileInfos = storageClient.upload_file("D:\\itcast\\pics\\575968fcN2faf4aa4.jpg", "jpg", null);
		
		/**
		 * 数组里面的内容为，如：
		 * group1
			M00/00/00/wKgMqFmfhoGAIzJGAABw0se6LsY966.jpg
		 */
		if(fileInfos != null && fileInfos.length > 1) {
			for (String str : fileInfos) {
				System.out.println(str);
			}
			String groupName = fileInfos[0];//组名
			String filename = fileInfos[1];//文件相对路径
			
			//获取存储服务器的ip；因为在存储服务器上配置了nginx
			ServerInfo[] serverInfos = trackerClient.getFetchStorages(trackerServer, groupName, filename);
			System.out.println("存储服务器的ip：" + serverInfos[0].getIpAddr() + 
					"; 端口号为：" + serverInfos[0].getPort());
			//组成一个完整的图片地址
			String url = "http://" + serverInfos[0].getIpAddr() + "/" + groupName + "/" + filename;
			
			System.out.println(url);
		}
	}

}
