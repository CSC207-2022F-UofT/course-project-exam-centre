package frameworks.daccess;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import iadapters.gateways.FileAccessGateway;

/**
 * The FtpAccessManager class is responsible for implementing the FileAccessGateway
 * interface in the interface adapters layer. The construction of this class establishes a
 * connection to an FTP server where our file storage system exists on. This class implements
 * all FTP server methods defined in the FileAccessGateway.
 *
 * @layer drivers and frameworks
 */
public class FtpAccessManager implements FileAccessGateway{
    
    private String remotePath;
    private String localPath;
    private String hostname;
    private int port;
    private String user;
    private String password;

    /** Constructs a new instance of FtpAccessManager by attempting to establish
     * a connection to an FTP server using the given connection data.
     *
     * @param hostname          the hostname of the ftp server 
     * @param port              the port used by the server for ftp connection
     * @param user              the user to be used in the ftp server connection
     * @param password          the password to be used in the ftp server connection
     * @param remotePath        the remote path to be used for file transfer
     * @param localPath         the local path to download files to
     */
    public FtpAccessManager(
        String hostname,
        int port,
        String user,
        String password,
        String remotePath,
        String localPath
    ){
        this.remotePath = remotePath;
        this.localPath = localPath;
        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.password = password; 
    }

    /** Establish connection to FTP server
     *
     * @return FTPClient object if connection and login to server is successful
     */
    private FTPClient connectFtpServer() {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(hostname, port);
            int replyCode = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.login(user, password);
                showServerReply(ftpClient);
                return ftpClient;
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /** Disconnect from the FTP server
     * 
     * @param ftpClient         ftpClient object to disconnect from the server
     */
    private void disconnectFtpServer(FTPClient ftpClient){
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** Check connection status to FTP server
     * 
     * @return true if connection is successful, false otherwise
     */
    @Override
    public boolean checkConnectionStatus() {
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null) {
            return false;
        } else {
            disconnectFtpServer(ftpClient);
            return true;
        }
    }

    /** Shows the FTP server's messages
     *
     * @param ftpClient FTPClient object
     */
    private void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    /** Upload a file to the FTP server
     *
     * @param localFilePath         file path of local file 
     * @param fileName              file name
     * @return returns true if file upload is successful, false otherwise
     */
    @Override
    public boolean uploadFile(String localFilePath, String fileName){
        FTPClient ftpClient = connectFtpServer();
        try {
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            File localFile = new File(localFilePath);           
            String remoteFile = remotePath + fileName + ".pdf";
            InputStream inputStream = new FileInputStream(localFile);
 
            boolean done = ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();
            if (done) {
                return true;
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            disconnectFtpServer(ftpClient);
        }
        return false;
    }

    /** Download a file from FTP server
     *
     * @param fileName file name to download
     * @return returns the local file path if file is downloaded successfully, null otherwise
     */
    @Override
    public String downloadFile(String fileName){
        FTPClient ftpClient = connectFtpServer();
        try {
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String remoteFile = remotePath + fileName + ".pdf";
            File downloadFile = new File(localPath + fileName + ".pdf");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
 
            if (success) {
                return localPath + fileName + ".pdf";
            }
 
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            disconnectFtpServer(ftpClient);
        }
        return null;
    }

}
