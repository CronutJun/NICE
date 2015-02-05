1. Filemng 실행
 - com/nicetcm/nibsplus/scheduler/main/NibsScheduleExecuter.java 를 실행하면됨
  . 파일에서 마우스 우클릭 Run As > Run Configurations
  . 좌측창에서 NibsScheduleExecuter 선택
  . 우측창에서 Arguments탭
    -> FilemngService SH_FILE_READ SH (op.t_cm_schedule 에 입력될 내용) 
    -> VM arguments : -Dspring.profiles.action=local 입력
    
 - 실행순서 
  . NibsScheduleExecuter실행 
    -> context-scheduler.xml의 환경파일읽어들임 
    -> local/dev/stage등의 설정에따라 context-scheduler.local.properties 환경파일읽어들임
    -> args[0].equals("-L")인경우 전체 스케쥴 보여주고
       그렇지 않은경우 단건 실행함
    -> ScheduleInfoProvider를 context에서 가져온다(ScheduleInfoProvider안의 SchedulerMapper는 SchedulerMapper.xml과 자동 매핑됨)
    -> 전체스케쥴일경우 : ScheduleInfoProvider.selectScheduleJobGroup()을 통하여 t_cm_schedule스케쥴에 
                          등록되어 있는 리스트를 SchedulerVO에 담아서 가지고옴
       단건실행일경우 : ScheduleInfoProvider.selectScheduleByPk()를 통하여 t_cm_schedule스케쥴에 등록되어 있는
                        정보를 SchedulerVO에 담아서 가지고 옴
    -> NibsScheduleExecuter.executeJob()을 통해 가지고 온 SchedulerVO의 정보를 통해 스케쥴을 실행
    -> ex) JOB_CALSS에 있는 com.nicetcm.nibsplus.filemng.main.ShinhanFilemngJob 를 실행함
  . ShinhanFilemngJob등 job실행
    -> applicationContext와 config파일 가져오기 가져온 파일은 
       local이라면 filemng/properties/context-filemng.local.properties 를 읽어 들임 