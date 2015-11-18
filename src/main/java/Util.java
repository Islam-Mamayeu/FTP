import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Util {

	static FTPClient ftp = new FTPClient();
	static final private String FtpLogin = "anonymous";
	static final private String FtpPassword = "";

	public FTPClient getFtp() {
		return ftp;
	}

	public void setFtp(FTPClient ftp) {
		this.ftp = ftp;
	}

	public static boolean connect(String ftpUrl) throws SocketException, IOException {
		try {
			ftp.connect(ftpUrl);

			System.out.println("Connected to " + ftpUrl );
			ftp.login(FtpLogin, FtpPassword);
			ftp.enterLocalPassiveMode();
			// ftp.enterLocalActiveMode();

		} catch (Exception e) {
			return false;

		}
		return true;

	}

	public static void printFilesName() throws IOException {

        if(ftp.isConnected())
        {
		System.out.println("List of files:");
		System.out.println("**********************");
		
		for (String str : ftp.listNames()) {
			System.out.println(str);

		}
		System.out.println("**********************");
        }
        else
        {
        	System.out.println("Connect error!");
        	
        }

	}
	
	public static void downloadFiles(String nameOfFile) throws IOException 
	{
		
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        
        File downloadFile = new File("C:/Downloads/"+nameOfFile);
        
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
        boolean success = ftp.retrieveFile(nameOfFile, outputStream1);
        outputStream1.close();

        if (success) {
            System.out.println("File has been downloaded successfully.");
        }
        else
        {
        	System.out.println("File is not found");
        }

		

	}
	
	
}
