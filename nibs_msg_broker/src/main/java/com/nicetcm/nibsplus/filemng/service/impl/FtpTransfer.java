package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileTransferService;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

@Service("ftpTransfer")
public class FtpTransfer implements FileTransferService
{
    /**
     * 원하는 파일을 로컬 폴더에 다운로드 한다
     * @param localdir 디렉토리명
     * @param fileName 파일명
     * @return
     */
    public File getFile(TransferVO transferVO) throws FileMngException
    {
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

            fos = new FileOutputStream(f);


            boolean isSuccess = ftp.retrieveFile(transferVO.getFileName(), fos);

            ftp.logout();

        } catch (Exception e)
        {
            throw new FileMngException(ExceptionType.VM_STOP, e.getMessage());
        } finally
        {
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
}