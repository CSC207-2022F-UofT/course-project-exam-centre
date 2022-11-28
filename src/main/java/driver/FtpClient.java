package driver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {
    // Return FTP server messages
    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    // Retrieve remote path
    public static String getRemotePath(){
        Properties config = new Properties();
        try {
            String configFilePath = "src/main/java/config/local.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            config.load(propsInput);
        } catch (IOException e) {
            System.out.println("Failed to load config file");
        }
        return config.getProperty("REMOTE_PATH");
    }

    // Establish connection to local FTP server
    public static FTPClient connectToServer(){
        // Load local config file
        Properties config = new Properties();
        try {
            String configFilePath = "src/main/java/config/local.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            config.load(propsInput);
        } catch (IOException e) {
            System.out.println("Failed to load config file");
        }

        String server = config.getProperty("FTP_HOST");
        int port = Integer.parseInt(config.getProperty("FTP_PORT"));
        String user = config.getProperty("FTP_USER");
        String pass = config.getProperty("FTP_PASS");

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return ftpClient;
            }
            boolean success = ftpClient.login(user, pass);
            showServerReply(ftpClient);
            if (!success) {
                System.out.println("Could not login to the server");
                return ftpClient;
            } else {
                System.out.println("LOGGED IN SERVER");
                return ftpClient;
            }
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
        }
        return ftpClient;
    }

    // Upload a file to local FTP server
    public static boolean uploadFile(String filePath, String fileName){
        // Establish connection to FTP server
        FTPClient ftpClient = connectToServer();
        
        // Upload file
        try {
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            File localFile = new File(filePath);           
            String remoteFile = getRemotePath() + fileName + ".pdf";
            InputStream inputStream = new FileInputStream(localFile);
 
            System.out.println("Start uploading file");
            boolean done = ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
                return true; // if true, insert into database
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                // Disconnect from server
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    // Download a file from local FTP server
    public static boolean downloadFile(String fileName, String downloadPath){
        // Establish connection to FTP server
        FTPClient ftpClient = connectToServer();

        // Download file
        try {
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String remoteFile = getRemotePath() + fileName + ".pdf";
            File downloadFile = new File(downloadPath + fileName + ".pdf");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
 
            if (success) {
                System.out.println("File has been downloaded successfully.");
                return true;
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                // Disconnect from server
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
