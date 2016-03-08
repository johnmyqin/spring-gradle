package com.moxingwang.file;

import java.io.InputStream;

import com.moxingwang.util.SpringContextUtil;

public class NetworkFile {
	public static final String SEP = "/";
	
	/**
	 * 文件所在的路径，不包含文件名
	 */
	private String path;
	/**
	 * 文件名，包含扩展名，不含路径
	 */
	private String filename;
	
	/** 
	 * Content Type
	 */
	private String content_type;
	
	private FileOperation fileOperation;
	
	public NetworkFile(String path) {
		if (path != null) {
			path = path.trim();
			path = path.toLowerCase();
			path = path.replace("\\", SEP);
			int lastsep = path.lastIndexOf(SEP);
			filename = path.substring(lastsep + 1);
			if (lastsep <= 0)
				path = SEP;
			else {
				path = path.substring(0, lastsep+1);
				if (!path.startsWith(SEP))
					path = SEP + path;
			}
		} else {
			path = SEP;
		}
		
		this.path = path;
		fileOperation = (FileOperation) SpringContextUtil.getBean("fileOperation"); 
	}
	
	public NetworkFile(String parent, String child) {
		if (child != null) {
			child = child.trim();
			child = child.toLowerCase();
			child = child.replace("\\", SEP);
			int lastsep = child.lastIndexOf(SEP);
			filename = child.substring(lastsep + 1);
			if (lastsep <= 0)
				child = "";
			else {
				child = child.substring(0, lastsep+1);
				if (child.startsWith(SEP))
					child = child.substring(1);
			}
		} else
			child = "";
		
		StringBuffer pathbuf = new StringBuffer();
		if (parent != null) {
			parent = parent.trim();
			parent = parent.toLowerCase();
			parent = parent.replace("\\", SEP);
			if (!parent.startsWith(SEP))
				pathbuf.append(SEP);
			pathbuf.append(parent);
			if (!parent.endsWith(SEP))
				pathbuf.append(SEP);
		} else {
			pathbuf.append(SEP);
		}
		
		pathbuf.append(child);
		path = pathbuf.toString();
		fileOperation = (FileOperation) SpringContextUtil.getBean("fileOperation"); 
	}
	
	
	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	/**
	 * 删除文件
	 * @return
	 */
	public boolean delete() {
		return fileOperation.delete(path, filename);
	}
	
	/**
	 * 判断文件是否存在 
	 * @return
	 */
	public boolean exists() {
		return fileOperation.exists(path, filename);
	}
	
	/**
	 * 取得文件路径
	 * 路径格式 /repo/path
	 * 
	 * @return
	 */
	public String getFilePath() {
		StringBuffer fullPath = new StringBuffer();
		fullPath.append(path);
		fullPath.append(filename);
		return fullPath.toString();
	}
	
	/**
	 * 取得文件的外部url
	 * url可以基于任意的host，格式为http://host/file/repo/path
	 * 其中host为当前应用的host，file为固定文字
	 * 
	 * @return
	 */
	public String getFileUrl(String host) {
		StringBuffer fullPath = new StringBuffer(host);
		if (!host.endsWith(SEP))
			fullPath.append(SEP);
		fullPath.append("file");
		fullPath.append(path);
		fullPath.append(filename);
		
		return fullPath.toString();
	}
	
	/**
	 * 修改文件名
	 * @param newName
	 * @return
	 */
	public boolean renameTo(String newName) {
		if (newName.indexOf(SEP) >= 0 || newName.indexOf("\\") >= 0)
			return false;
		
		newName = newName.toLowerCase();
		boolean re = fileOperation.renameTo(path, filename, newName);
		if (re) {
			filename = newName;			
		}
		return re;
	}
	
	/**
	 * 取得文件名
	 * @return
	 */
	public String getName() {
		return filename;
	}
	
	/**
	 * 修改文件路径
	 */
	public boolean moveTo(String newPath) {
		newPath = newPath.replace("\\", SEP);
		if (!newPath.startsWith(SEP))
			newPath = SEP + newPath;
		if (!newPath.endsWith(SEP)) 
			newPath += SEP;
		
		newPath = newPath.toLowerCase();
		boolean re = fileOperation.moveTo(path, filename, newPath);
		if (re) {
			path = newPath;			
		}
		return re;
	}
	
	/**
	 * 取得文件大小
	 * @return
	 */
	public long length() {
		return fileOperation.length(path, filename);
	}
	
	/**
	 * 保存文件, 如果文件存在则覆盖它
	 * @return 保存后的文件对象
	 */
	public Object saveFile(InputStream input, String content_type, FileMetadata metadata) {
		return fileOperation.saveFile(path, filename, input, content_type, metadata);
	}
	
	/**
	 * 读取文件
	 */
	public InputStream readFile(FileMetadata metadata) {
		return fileOperation.readFile(path, filename, metadata);
	}
}
