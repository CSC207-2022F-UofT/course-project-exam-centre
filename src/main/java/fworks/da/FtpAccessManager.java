package fworks.da;

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

import ia.gateways.FileAccessGateway;

/**
 * The FtpAccessManager class is responsible for implementing the FileAccessGateway
 * interface in the interface adapters layer. The construction of this class establishes a
 * connection to an FTP server where our file storage system exists on. This class implements
 * all FTP server methods defined in the FileAccessGateway.
 *
 * @layer drivers and frameworks
 */
public class FtpAccessManager implements FileAccessGateway{
    
    private FTPClient ftpClient;
    private String remotePath;

    /** Constructs a new instance of FtpAccessManager by attempting to establish
     * a connection to an FTP server using the given connection data.
     *
     * @param hostname          the hostname of the ftp server 
     * @param port              the port used by the server for ftp connection
     * @param user              the user to be used in the ftp server connection
     * @param password          the password to be used in the ftp server connection
     * @param remotePath        the remote path to be used for file transfer
     */
    public FtpAccessManager(
        String hostname,
        int port,
        String user,
        String password,
        String remotePath
    ){
        this.remotePath = remotePath;
        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(hostname, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.login(user, password);
                showServerReply(ftpClient);
                this.ftpClient = ftpClient;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
     * @param localFilePath file path of local file 
     * @param fileName file name
     * @return returns true if file upload is successful, false otherwise
     */
    public boolean uploadFile(String localFilePath, String fileName){
        // Upload file
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

    /** Download a file from FTP server
     *
     * @param fileName file name to download
     * @param downloadPath file path of file to download
     * @return returns true if file is downloaded successfully, false otherwise
     */
    public boolean downloadFile(String fileName, String downloadPath){

        // Download file
        try {
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String remoteFile = remotePath + fileName + ".pdf";
            File downloadFile = new File(downloadPath + fileName + ".pdf");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
 
            if (success) {
                return true;
            }
 
        } catch (IOException ex) {
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
