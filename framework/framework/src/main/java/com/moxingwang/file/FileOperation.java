package com.moxingwang.file;

import java.io.InputStream;

public interface FileOperation {

	/**
	 * 删除文件
	 * @param path
	 * @param filename
	 * @return
	 */
	boolean delete(String path, String filename);
	
	/**
	 * 判断文件是否存在
	 * @param path
	 * @param filename
	 * @return
	 */
	boolean exists(String path, String filename);
	
	/**
	 * 取得文件长度
	 * @param path
	 * @param filename
	 * @return
	 */
	long length(String path, String filename);
	
	/**
	 * 文件改名
	 * @param path
	 * @param filename
	 * @param newName
	 * @return
	 */
	boolean renameTo(String path, String filename, String newName);
	
	/**
	 * 改变文件目录
	 * @param path
	 * @param filename
	 * @param newPath
	 * @return
	 */
	boolean moveTo(String path, String filename, String newPath);
	
	/**
	 * 保存文件
	 * @param path
	 * @param filename
	 * @param input
	 * @param content_type
	 * @param metadata
	 * @return
	 */
	Object saveFile(String path, String filename, InputStream input, String content_type, FileMetadata metadata);
	
	/**
	 * 读取文件
	 * @param path
	 * @param filename
	 * @param metadata
	 * @return
	 */
	InputStream readFile(String path, String filename, FileMetadata metadata);
}
