package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

public class SFtpTransfer {
	
	public static void main(String[] args) throws FileMngException {
		TransferVO vo = new TransferVO();
		vo.setHost("10.63.3.101");
		vo.setAvailableServerPort(22);
		vo.setUserId("chapweb");
		vo.setPassword("chapweb");
		vo.setRemotePath("dir");
		vo.setFileName("test.txt");
		vo.setLocalPath("/nibs_dev_sftp_local");
		vo.setFileName("test.txt");
		
		SFtpTransfer.getFile(vo);
	}
	
	private static Connection connect(TransferVO transferVO) throws IOException, FileMngException {
		Connection connection = new Connection(transferVO.getHost(), transferVO.getAvailableServerPort());
        connection.connect();

        if (!connection.authenticateWithPassword(transferVO.getUserId(), transferVO.getPassword())) {
            throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 로그인하지 못했습니다.");
        }
        
        return connection;
	}
	
    /**
     * 원하는 파일을 로컬 폴더에 다운로드 한다
     * @param TransferVO 전송정보
     * @return File
     */
    public static File getFile(TransferVO transferVO) throws FileMngException {
        FileOutputStream fos = null;
        Connection connection = null;
        SFTPv3Client sftp = null;
        SFTPv3FileHandle rfile = null;
        File lfile = null;
        
        try {
            try {
	            sftp = new SFTPv3Client(connection = connect(transferVO));
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
    public static File putFile(TransferVO transferVO) throws FileMngException {
        FileInputStream fis = null;
        Connection connection = null;
        SFTPv3Client sftp = null;
        SFTPv3FileHandle rfile = null;
        File lfile = null;

        try {
            try {
	            sftp = new SFTPv3Client(connection = connect(transferVO));
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
    
    public static List<String> getFileNames(TransferVO transferVO, String... option) throws FileMngException, IOException {
    	StringBuffer sb = new StringBuffer();
    	
    	for (String op : option) {
    		sb.append(" " + transferVO.getRemotePath() + op);
    	}
    	
    	return execCommand(transferVO, "ls" + sb.toString());
    }
    
    private static List<String> execCommand(TransferVO transferVO, String cmd) throws IOException, FileMngException {
        Connection connection = null;
    	Session session = null;
    	BufferedReader in = null;
    	List<String> result = new ArrayList<String>();
    	
        try {
            try {
            	connection = connect(transferVO);
            	session = connection.openSession();
		    	session.execCommand(cmd);
		    	in = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStdout())));
		    	String line;
		    	
		    	while ((line = in.readLine()) != null) {
		    		result.add(line);
		    	}
            } finally {
            	in.close();
		    	session.close();
            	connection.close();
            }
        } catch (Exception e) {
            throw new FileMngException(ExceptionType.VM_STOP, e.getMessage());
        }
    	
    	return result;
    }
}
