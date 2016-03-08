package com.moxingwang.file;

import java.util.Date;

public class FileMetadata {
	/**
	 * 文件锁，自动设定
	 */
	public Date locked; 
	
	/**
	 * 一次性ID，检测写入冲突，自动设定
	 */
	public Object nonce; 
	
	/**
	 * 文件的全路径
	 */
	public String path;
	
	/**
	 * 文件名
	 */
	public String filename;
	
	/**
	 * 创建者名字
	 */
	public String author;
	
	/**
	 * 创建者ID
	 */
	public String author_id;
	
	/**
	 * 创建者ihefeID
	 */
	public String author_ihefe_id;


	/**
	 * 类型
	 */
	public String type; 
	
	/**
	 * 创建时间
	 */
	public Date created; 
	
	/**
	 * 父文档的id
	 */
	public String parent_id; 
	
	/**
	 * 企业、医院id
	 */
	public String org_id;

	/**
	 * 企业，医院名
	 */
	public String org;
	/**
	 * 科室id
	 */
	public String office_id;
	
	/**
	 * 科室名称
	 */
	public String office;
	
	/**
	 * 保存文件的系统名称，比如护理，医生等
	 */
	public String system_id;
}
