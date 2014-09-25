package com.nicetcm.nibsplus.filemng.service;

import java.io.File;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.model.TransferVO;


public interface FileTransferService
{
    public File getFile(TransferVO transferVO) throws FileMngException;
}