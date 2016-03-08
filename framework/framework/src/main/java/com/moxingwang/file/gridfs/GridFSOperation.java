package com.moxingwang.file.gridfs;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.moxingwang.exception.ServiceException;
import com.moxingwang.file.FileMetadata;
import com.moxingwang.file.FileOperation;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

public class GridFSOperation implements FileOperation {
	private static final long FIVEMIN = 5*60*1000;
	private static final String LOCKED = "locked";
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	
	@Override
	public boolean delete(String path, String filename) {
		GridFSDBFile result = findUnlockFile(path, filename);
		if (result != null) {	
			Query query = new Query();
			query.addCriteria(GridFsCriteria.whereMetaData("filename").is(filename));
			query.addCriteria(GridFsCriteria.whereMetaData("path").is(path));
			gridFsTemplate.delete(query);
			return true;
		} else
			return false;
	}

	@Override
	public boolean exists(String path, String filename) {
		GridFSDBFile result = findFile(path, filename);
		if (result != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public long length(String path, String filename) {
		GridFSDBFile result = findFile(path, filename);
		if (result != null) {
			return result.getLength();
		} else {
			return 0;
		}
	}

	@Override
	public boolean renameTo(String path, String filename, String newName) {
		//重名检查
		GridFSDBFile file = findFile(path, newName);
		if (file != null)
			return false;
		GridFSDBFile result = findUnlockFile(path, filename);
		if (result != null) {
			result.put("filename", newName);
			DBObject dbmeta = result.getMetaData();
			dbmeta.put("filename", newName);
			result.save();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean moveTo(String path, String filename, String newPath) {
		//重名检查
		GridFSDBFile file = findFile(newPath, filename);
		if (file != null)
			return false;
		GridFSDBFile result = findUnlockFile(path, filename);
		if (result != null) {
			DBObject dbmeta = result.getMetaData();
			dbmeta.put("path", newPath);
			result.save();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object saveFile(String path, String filename, InputStream input, String content_type, FileMetadata metadata) {
		if (input == null)
			return null;
		
		GridFSDBFile result = findUnlockFile(path, filename);
		if (result != null) {	
			if (content_type == null || "".equals(content_type)) {
				content_type = result.getContentType();
			}
			//delete old file
			Query query = new Query();
			query.addCriteria(GridFsCriteria.whereMetaData("filename").is(filename));
			query.addCriteria(GridFsCriteria.whereMetaData("path").is(path));
			gridFsTemplate.delete(query);
		}
		//save new file
		metadata.path = path;
		metadata.filename = filename;
		metadata.locked = new Date();
		metadata.created = new Date();
		if (content_type == null || "".equals(content_type)) {
			content_type = filename.substring(filename.lastIndexOf("."));
		}
		metadata.type = content_type;
		GridFSFile file = gridFsTemplate.store(input, filename, content_type, metadata);
		
		//unlock file
		DBObject dbMetadata = file.getMetaData();
		dbMetadata.put(LOCKED, null);
		file.setMetaData(dbMetadata);
		file.save();
		return file.getId();

	}

	@Override
	public InputStream readFile(String path, String filename, FileMetadata metadata) {
		GridFSDBFile result = findFile(path, filename);
		if (result != null) {
			//get metadata
			if (metadata != null) {
				populateMetadata(metadata, result.getMetaData());
			}
			return result.getInputStream();
		} else {
			return null;
		}
	}

	private GridFSDBFile findUnlockFile(String path, String filename) {
		Query query = new Query();
		query.addCriteria(GridFsCriteria.whereMetaData("filename").is(filename));
		query.addCriteria(GridFsCriteria.whereMetaData("path").is(path));
		
		//find file without lock
		GridFSDBFile result = gridFsTemplate.findOne(query);
		if (result != null) {
			DBObject meta = result.getMetaData();
			Date locked = (Date)meta.get(LOCKED);
			if (locked != null) {
				//find file with locked before 5 mins
				long cur = System.currentTimeMillis();
				long lockdate = locked.getTime();
				if (cur - lockdate < FIVEMIN) {
					//file is locked
					throw new ServiceException("SYS_ERR_2001");
				}
			}			
		}
		return result;
	}
	
	private GridFSDBFile findFile(String path, String filename) {
		Query query = new Query();
		query.addCriteria(GridFsCriteria.whereMetaData("filename").is(filename));
		query.addCriteria(GridFsCriteria.whereMetaData("path").is(path));
		
		GridFSDBFile result = gridFsTemplate.findOne(query);
		return result;
	}
	
	private void populateMetadata(FileMetadata metadata, DBObject dbmeta) {
		metadata.filename = (String) dbmeta.get("filename");
		metadata.path = (String) dbmeta.get("path");
		metadata.author = (String) dbmeta.get("author");
		metadata.author_id = (String) dbmeta.get("author_id");
		metadata.created = (Date) dbmeta.get("created");
		metadata.office = (String) dbmeta.get("office");
		metadata.office_id = (String) dbmeta.get("office_id");
		metadata.org = (String) dbmeta.get("org");
		metadata.org_id = (String) dbmeta.get("org_id");
		metadata.parent_id = (String) dbmeta.get("parent_id");
		metadata.system_id = (String) dbmeta.get("system_id");
		metadata.type = (String) dbmeta.get("type");
	}
	
}
