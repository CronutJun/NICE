package com.nicetcm.nibsplus.filemng.main;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NhMain {
  public static void main(String[] args) {

    String[] springConfig  =
        {   "classpath:/filemng/spring/context-filemng.xml",
            "classpath:/filemng/spring/context-filemng-nhJob.xml"
        };

    ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

    JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
    Job job = (Job) context.getBean("nhJob");

    try {

        //  JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
        Map<String,JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
        parameters.put("nh.file.name", new JobParameter("/Project_NIBS/FTP_RECEIVE/nh/R20140924B11.txt"));

        JobParameters jobParameters = new JobParameters(parameters);

        JobExecution execution = jobLauncher.run(job, jobParameters);
        System.out.println("Exit Status : " + execution.getStatus());


    } catch (Exception e) {
        e.printStackTrace();
    }

    System.out.println("Done");

  }
}