package servlet6_FileUpload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUpload {
	private static final String SAVE_DIR = "upload";
	
	public static String upload(HttpServletRequest request) throws IOException, ServletException {
		String fileFullPath = "";

		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + SAVE_DIR;
		
//		서버에 savePath가 없으면, 만든다.
		File fileSaveDir = new File(savePath);
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		for(Part part :request.getParts()) {
			String fileName = extractFileName(part.getHeader("Content-Disposition"));
				//					"/"
			part.write(savePath+File.separator + fileName);
			String ContextPath = request.getContextPath(); // ContextPath : "/Servlet6_FileUpload"
			fileFullPath = "http://localhost:8082" + ContextPath + "/"+SAVE_DIR + "/"+fileName;
			
			 if( fileName != null) {
				 fileFullPath = "http://localhost:8082" + ContextPath +  "/" + SAVE_DIR + "/" + fileName;
			}
		}
		
		
		return fileFullPath;
	}
	static String extractFileName(String partHeader) {
		
		System.out.println("partHeader:"+partHeader);
		
		for (String cd : partHeader.split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf("=") +  1).trim().replace("\"", "");;
                int index = fileName.lastIndexOf(File.separator);
                return fileName.substring(index + 1); //"image1.png"
            }
        }
        return null;
	}
}
