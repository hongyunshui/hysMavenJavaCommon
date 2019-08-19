package com.taotao.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传文件
 * @author hys
 *
 */
public class FtpUtil {
	
	/**
	 * 
	 * @param host FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登陆账号
	 * @param password FTP登陆密码
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01.文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 返回成功值为true,否则值为false
	 */
	public static boolean uploadFile(String host, int port, String username, String password, String basePath,
			String filePath, String filename, InputStream input){
		boolean result = false;
		FTPClient ftp = new FTPClient();
		
		int reply;
		try {
			ftp.connect(host, port);// 连接ftp服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				ftp.disconnect();
				return result;
			}
			// 切换到上传目录
			if(!ftp.changeWorkingDirectory(basePath + filePath)){
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String temPath = basePath;
				for(String dir:dirs){
					if(null == dir || "".equals(dir)) continue;
					temPath += "/" + dir;
					if(!ftp.changeWorkingDirectory(temPath)){
						if(!ftp.makeDirectory(temPath)){
							return result;
						}else{
							ftp.changeWorkingDirectory(temPath);
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件
			if(!ftp.storeFile(filename, input)){
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (SocketException e) {
			System.out.println("上传图片时套接字（Socket）异常");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("上传图片到ftp时IO异常");
			e.printStackTrace();
		}finally{
			if(ftp.isConnected()){
				try{
					ftp.disconnect();
				}catch(IOException ioe){
					
				}
			}
		}
				return result;
	}
}
