package com.nicetcm.nibsplus.filemng.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.VpnMapper;
import com.nicetcm.nibsplus.filemng.model.VpnVO;

public class VpnItemWriter implements ItemWriter<VpnVO>
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VpnMapper vpnMapper;

    @Override
    public void write(List<? extends VpnVO> items) throws Exception
    {
        for(VpnVO vpnVO : items) {
            vpnMapper.updateTCtNiceMac(vpnVO);

            String hIP_ADDR = vpnVO.getVpnIp();
            String hCompIP = "";

            int i = 0;
            int nCnt = 0;
            for( i = 0; i < hIP_ADDR.length(); i++ )
            {
                if( hIP_ADDR.substring(i, i+1).equals(".") && nCnt == 1 )
                {

                    /*20111025 박태희대리 요청 10.29.1~3번은 89, 10.29.4~6은 84 로 세부 분기 하게 수정 */
                    logger.debug("1> {}", hCompIP);
                    if(hCompIP.startsWith("10.29"))
                    {
                        hCompIP += hIP_ADDR.substring(i, i+1);
                        for( int j = i+1; j < hIP_ADDR.length(); j++ )
                        {

                            if( hIP_ADDR.substring(j, j+1).equals("."))
                            {
                                break;
                            }
                            else
                            {
                                hCompIP += hIP_ADDR.substring(j, j+1);
                            }
                        }
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    hCompIP += hIP_ADDR.substring(i, i+1);
                    if( hIP_ADDR.substring(i, i+1).equals("."))
                    {
                        nCnt++;
                    }
                }
            }

            vpnVO.setVpnIp(hCompIP);
            vpnMapper.updateTCmAdslInfo(vpnVO);

        }//end for

    }

}
