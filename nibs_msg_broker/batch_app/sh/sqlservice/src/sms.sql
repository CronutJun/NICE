BEGIN
   		INSERT INTO sms.em_smt_tran   (
		        MT_PR,                -- sequence                       -- mt_pr
		        MT_REFKEY,            -- create_date || error_no        -- mt_refkey
		        RECIPIENT_NUM,        -- Receive Phone number      -- recipient_num
		--        tran_callback,          -- Send Phone number
		        DATE_CLIENT_REQ,
		        CALLBACK,
		        SERVICE_TYPE,
		        BROADCAST_YN,
		        MSG_STATUS,            -- 전송 상태                      -- msg_status
		        DATE_MT_SENT,              -- 전송 일자                  -- date_mt_sent
		        CONTENT,               -- 전송 메시지                    -- content
		        tran_etc1,              -- 업무구분 으로 활용 (장애:1320하지말것) -- ?
		        tran_etc2,              -- create_date                    -- ?
		        tran_etc3 )             -- error_no                       -- ?
		SELECT  sms.em_tran_pr.nextval,
		        to_char(sysdate,'yyyymmdd') || 'NIBS',
		        '01064330148',
		--        '02-2122-5452',
		        SYSDATE,
		        '0221229999',
		        '0',
		        'N',
		        '1',
		        sysdate,
		        a.msg,
		        '9000',
		        '',
		        ''
		FROM (
				select 'NIBSDB' ||' : '||
		                        nvl(b.tablespace_name,nvl(a.tablespace_name,'UNKNOWN')) ||' : '||
		                        round(((kbytes_alloc-nvl(kbytes_free,0))/kbytes_alloc)*100) ||'%' msg
		                 from ( select sum(bytes)/1024/1024 Kbytes_free,
		                                              max(bytes)/1024/1024 largest,
		                                              tablespace_name
		                                         from sys.dba_free_space
		                                        group by tablespace_name ) a,
		                                     ( select sum(bytes)/1024/1024 Kbytes_alloc,
		                                              tablespace_name,
		                                              count(*) data_files
		                                         from sys.dba_data_files
		                                        group by tablespace_name )b
		                 where a.tablespace_name (+) = b.tablespace_name
		          --       and (a.tablespace_name like 'NIBS_%' or a.tablespace_name like 'NBOS_%')
		                 and ((kbytes_alloc-nvl(kbytes_free,0))/kbytes_alloc) > 0.85
		      ) a;
COMMIT;
END;
/
EXIT;
