package com.basics.support.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.basics.support.CommonSupport;
import com.basics.support.FileStoreService;

/**
 * Created by Administrator on 2018/5/31.
 */
@Service("baseFileStoreService")
public class LocalStoreService implements FileStoreService {
	@Override
	public long getStoreId() {
		return 0;
	}

	@Override
	public String getStoreName() {
		return "本地文件储存";
	}

	@Override
	public byte[] readToByteArray(String path) throws IOException {
		return FileUtils.readFileToByteArray(new File(getPath(path)));
	}

	@Override
	public InputStream readToInputStream(String path) throws IOException {
		return FileUtils.openInputStream(new File(getPath(path)));
	}

	@Override
	public boolean existed(String path) throws IOException {
		return new File(getPath(path)).exists();
	}

	@Override
	public void write(String path, byte[] content) throws IOException {
		File newFile = new File(getPath(path.substring(0, path.lastIndexOf("/"))));
		if (!newFile.exists()) {
			FileUtils.forceMkdir(newFile);
		}
		IOUtils.write(content, new FileOutputStream(getPath(path)));
	}

	@Override
	public void write(String path, InputStream content) throws IOException {
		this.write(path, IOUtils.toByteArray(content));
	}

	@Override
	public void write(String path, File file) throws IOException {
		this.write(path, new FileInputStream(file));
	}

	@Override
	public void delete(String path) throws IOException {
		FileUtils.forceDelete(FileUtils.toFile(new URL(path)));
	}

	@Override
	public String getInternetUrl(String filePath) {
		// 如果包含协议例如:http://,https://不添加域名.
		if (CommonSupport.contains(filePath, ":")) {
			return filePath;
		}
		return UploadUtil.BASE_URL + filePath;
	}

	@Override
	public String getPath(String filePath) {
		return UploadUtil.BASE_PATH + filePath;
	}
}
