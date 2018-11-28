package kr.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtil {

	public static String UPLOAD_PATH="/upload";
	public static final String ENCODING_TYPE="utf-8";
	public static final int MAX_SIZE=10*1024*1024;

	public static MultipartRequest createFile(HttpServletRequest request)throws IOException{
		return new MultipartRequest(request,request.getServletContext().getRealPath(UPLOAD_PATH),MAX_SIZE,ENCODING_TYPE,
				new DefaultFileRenamePolicy());
	}
	public static String rename(HttpServletRequest request, String fileName)throws Exception{

		if(fileName==null) return null;

		String newName = Long.toString(System.currentTimeMillis())+(int)(Math.random()*50);

		return rename(request,fileName,newName);
	}
	public static String rename(HttpServletRequest request, String fileName,String newName)
			throws Exception{
		if(fileName == null) return null;

		File file = new File(request.getServletContext().getRealPath(UPLOAD_PATH),fileName);

		//파일명을 원하는 형식으로 변경하기
		int idx = fileName.lastIndexOf(".");

		String extention = "";
		String newFileName = "";

		if(idx !=-1){
			extention = fileName.substring(idx);
		}
		//newName 전달시 확장자를 제외해야 하지만 확장자를 포함할 경우는 제거함
		int newIdx = newName.lastIndexOf(".");
		if(newIdx !=-1){
			newName = newName.substring(0,newIdx);
		}
		//확장자 포함 새로운 파일명
		newFileName = newName + extention.toLowerCase();

		File fs = new File(request.getServletContext().getRealPath(UPLOAD_PATH),newFileName);
		file.renameTo(fs); //파일명을 변경

		return newFileName;
	}
	public static void removeFile(HttpServletRequest request, String fileName){
		if(fileName != null){
			File file = new File(request.getServletContext().getRealPath(UPLOAD_PATH),fileName);
			if(file.exists()) file.delete();
		}
	}
}
