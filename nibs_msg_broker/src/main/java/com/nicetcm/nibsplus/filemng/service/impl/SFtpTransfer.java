package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileTransferService;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

@Service("sftpTransfer")
public class SFtpTransfer implements FileTransferService {
	
	public static void main(String[] args) throws FileMngException {
		TransferVO vo = new TransferVO();
		vo.setHost("127.0.0.1");
		vo.setAvailableServerPort(22);
		vo.setUserId("master");
		vo.setPassword("test");
		vo.setRemotePath("dir");
		vo.setFileName("test.txt");
		vo.setLocalPath("/nibs_dev_sftp_local");
		vo.setFileName("test.txt");
		
		SFtpTransfer sftpTransfer = new SFtpTransfer();
		sftpTransfer.getFile(vo);
	}
	
    /**
     * 원하는 파일을 로컬 폴더에 다운로드 한다
     * @param TransferVO 전송정보
     * @return File
     */
    @Override
    public File getFile(TransferVO transferVO) throws FileMngException {
        FileOutputStream fos = null;
        Connection connection;
        SFTPv3Client sftp = null;
        SFTPv3FileHandle rfile = null;
        File lfile = null;
        
        try {
        	connection = new Connection(transferVO.getHost(), transferVO.getAvailableServerPort());
            connection.connect();

            if (!connection.authenticateWithPassword(transferVO.getUserId(), transferVO.getPassword())) {
                throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 로그인하지 못했습니다.");
            }

            try {
	            sftp = new SFTPv3Client(connection);
	            rfile = sftp.openFileRO(transferVO.getRemotePath() + "/" + transferVO.getFileName());
	
	            if (rfile == null) {
	                throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 [" + transferVO.getFileName() + "] 파일이 존재하지 않습니다.");
	            }
	
	            lfile = new File(transferVO.getLocalPath(), transferVO.getFileName());
	            lfile.getParentFile().mkdirs();
	
	            {
	            	long fileOffset = 0;
	            	byte[] readBuf = new byte[32768];
	            	int readCnt=0;
	                fos = new FileOutputStream(lfile);
	            	
	                while ((readCnt = sftp.read(rfile, fileOffset, readBuf, 0, readCnt)) != -1) {
	                	fileOffset += readCnt;
	                	fos.write(readBuf, 0, readCnt);
	                }
	            }
            } finally {
                if (fos != null)
                    try {
                        fos.close();
                    } catch (IOException ex) {}
                if (sftp != null && sftp.isConnected()) {
                    try {
                    	if (rfile != null) sftp.closeFile(rfile);
                    	
                    	sftp.close();
                    	connection.close();
                    } catch (IOException e) {}
                }
            }
        } catch (Exception e) {
            throw new FileMngException(ExceptionType.VM_STOP, e.getMessage());
        }

        return lfile;
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
    @Override
    public File putFile(TransferVO transferVO) throws FileMngException {
        FileInputStream fis = null;
        Connection connection;
        SFTPv3Client sftp = null;
        SFTPv3FileHandle rfile = null;
        File lfile = null;

        try {
        	connection = new Connection(transferVO.getHost(), transferVO.getAvailableServerPort());
            connection.connect();

            if (!connection.authenticateWithPassword(transferVO.getUserId(), transferVO.getPassword())) {
                throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 로그인하지 못했습니다.");
            }

            try {
	            sftp = new SFTPv3Client(connection);
	            rfile = sftp.openFileWAppend(transferVO.getRemotePath() + "/" + transferVO.getFileName());
	
	            lfile = new File(transferVO.getLocalPath(), transferVO.getFileName());
	
	            {
	            	long fileOffset = 0;
	            	byte[] readBuf = new byte[32768];
	            	int readCnt=0;
	            	fis = new FileInputStream(lfile);
	            	
	                while ((readCnt = fis.read(readBuf)) != -1) {
	                	sftp.write(rfile, fileOffset, readBuf, 0, readCnt);
	                	
	                	fileOffset += readCnt;
	                }
	            }
            } finally {
                if (fis != null)
                    try {
                        fis.close();
                    } catch (IOException ex) {}
                if (sftp != null && sftp.isConnected()) {
                    try {
                    	if (rfile != null) sftp.closeFile(rfile);
                    	
                    	sftp.close();
                    	connection.close();
                    } catch (IOException e) {}
                }
            }
        } catch (Exception e) {
            throw new FileMngException(ExceptionType.VM_STOP, e.getMessage());
        }

        return lfile;
    }
}
