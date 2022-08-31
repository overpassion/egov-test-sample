package egovframework.example.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ResumeDownload {

	public static void main(String[] args) {
		
		final int DOWNLOAD_DONE = 0;
		final int DEFAULT_TIMEOUT = 600;
		 
		long fileSize = 1000; 
		long remains;
		long fileLength;
		 
		String url_ = "http://localhost:7070/testSimpleWeb/images/test_endofthefjord.jpeg";
		 
		File file = new File("D:\\TEMP\\mytest.jpeg");
		if (!file.exists()) {
		     try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RandomAccessFile output = null;
		try {
			output = new RandomAccessFile(file.getAbsolutePath(), "rw");
			fileSize = output.length();
			output.seek(fileSize);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		URL url;
		try {
			url = new URL(url_);
			URLConnection conn = url.openConnection();
			
			conn.setRequestProperty("Accept-Ranges", "bytes");
			conn.setRequestProperty("Range", "bytes=" + String.valueOf(fileSize) + '-' + "100000");
			conn.connect();
			conn.setConnectTimeout(DEFAULT_TIMEOUT);
			conn.setReadTimeout(DEFAULT_TIMEOUT);
			remains = conn.getContentLength();
			fileLength = remains + fileSize;
			
			    if((remains <= DOWNLOAD_DONE) || (remains == fileSize)) {
			        System.out.println("error");
			    }
			 
			    InputStream input = conn.getInputStream();
			    byte data[] = new byte[1024];
			    int count = 0;
			 
			    if (fileSize < fileLength) {
			        while((count = input.read(data)) != -1) {
			            output.write(data, 0, count);
			        }
			    }
			    output.close();
			    input.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		System.out.println("Success");
	}

}
