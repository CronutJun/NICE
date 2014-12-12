package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

public class FtpTransfer {

	public static void main(String[] args) throws FileMngException {
		TransferVO vo = new TransferVO();
		vo.setHost("127.0.0.1");
		vo.setAvailableServerPort(21);
		vo.setUserId("master");
		vo.setPassword("master");
		vo.setRemotePath("/aa");
		vo.setFileName("20140430.txt");
		vo.setLocalPath("/");
		//vo.setFileName("test.txt");
		
		FtpTransfer.putFile(vo);
	}
	
    /**
     * 원하는 파일을 로컬 폴더에 다운로드 한다
     * @param TransferVO 전송정보
     * @return File
     */
    public static File getFile(TransferVO transferVO) throws FileMngException {
        FileOutputStream fos = null;
        FTPClient ftp = null;
        File f = null;
        try
        {
            ftp = new FTPClient();
            ftp.connect(transferVO.getHost(), transferVO.getAvailableServerPort());

            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                throw new FileMngException(ExceptionType.NETWORK, "FTP server refused connection.");
            }

            if (!ftp.login(transferVO.getUserId(), transferVO.getPassword()))
            {
                ftp.logout();
                throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 로그인하지 못했습니다.");
            }

            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();

            ftp.changeWorkingDirectory(transferVO.getRemotePath());

            FTPFile[] ftpFileList = ftp.listFiles();

            boolean existFile = false;
            for(FTPFile ftpFile : ftpFileList) {
                if(ftpFile.getName().equals(transferVO.getFileName())) {
                    existFile = true;
                    break;
                }
            }

            if (existFile == false)
            {
                ftp.logout();
                throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 [" + transferVO.getFileName() + "] 파일이 존재하지 않습니다.");
            }

            f = new File(transferVO.getLocalPath(), transferVO.getFileName());
            f.getParentFile().mkdirs();

            fos = new FileOutputStream(f);

            ftp.retrieveFile(transferVO.getFileName(), fos);
            ftp.logout();

        } catch (Exception e) {
            throw new FileMngException(ExceptionType.VM_STOP, transferVO.getHost() + " " + e.getMessage());
        } finally {
            if (fos != null)
                try
                {
                    fos.close();
                } catch (IOException ex)
                {
                }
            if (ftp != null && ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                } catch (IOException e)
                {

                }
            }
        }

        return f;
    }

    /**
     *
     * 원하는 파일을 원격폴더에 업로드
     * <pre>
     *
     * </pre>
     *
     * @param transferVO
     * @return
     * @throws FileMngException
     */
    public static File putFile(TransferVO transferVO) throws FileMngException {
        FileInputStream fis = null;
        FTPClient ftp = null;
        File f = null;
        try
        {
            ftp = new FTPClient();
            ftp.connect(transferVO.getHost(), transferVO.getAvailableServerPort());

            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                throw new FileMngException(ExceptionType.NETWORK, "FTP server refused connection.");
            }

            if (!ftp.login(transferVO.getUserId(), transferVO.getPassword()))
            {
                ftp.logout();
                throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 로그인하지 못했습니다.");
            }

            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();

            ftp.setKeepAlive(true);
            ftp.setControlKeepAliveTimeout(30);
            ftp.addProtocolCommandListener(new PrintCommandListener(System.out, true));
            ftp.setBufferSize(1024000);

            ftp.changeWorkingDirectory(transferVO.getRemotePath());

            // FTPFile[] ftpFileList = ftp.listFiles();

            f = new File(transferVO.getLocalPath(), transferVO.getFileName());

            fis = new FileInputStream(f);

            ftp.storeFile(transferVO.getFileName(), fis);
            ftp.logout();

        } catch (Exception e)
        {
            throw new FileMngException(ExceptionType.VM_STOP, transferVO.getHost() + " " + e.getMessage());
        } finally
        {
            if (fis != null)
                try
                {
                    fis.close();
                } catch (IOException ex)
                {
                }
            if (ftp != null && ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                } catch (IOException e)
                {

                }
            }
        }

        return f;
    }
}
