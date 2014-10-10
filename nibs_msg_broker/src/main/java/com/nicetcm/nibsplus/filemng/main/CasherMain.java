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

public class CasherMain {
  public static void main(String[] args) {

    String[] springConfig  =
        {   "classpath:/filemng/spring/context-filemng.xml",
            "classpath:/filemng/spring/context-filemng-casherJob.xml"
        };

    ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

    JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
    // Job job = (Job) context.getBean("casherJob");
    String fileName = null;

    try {

        //  JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
        Map<String,JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
        parameters.put("casher.file.name", new JobParameter(fileName = "D:/pjt/old_nibs/nibsif/data_sample/casher_file/CK_09260000_001_Z023.txt"));
        // parameters.put("casher.file.name", new JobParameter(fileName = "D:/pjt/old_nibs/nibsif/data_sample/casher_file/TK_09260000_001_Z023.txt"));
        // parameters.put("casher.file.name", new JobParameter(fileName = "D:/pjt/old_nibs/nibsif/data_sample/casher_file/TR_09260000_001_Z023.txt"));

        JobParameters jobParameters = new JobParameters(parameters);

        JobExecution execution;
        
        if (fileName.indexOf("CK_") != -1) {
        	execution = jobLauncher.run((Job)context.getBean("casherCKJob"), jobParameters);
        } else if (fileName.indexOf("TK_") != -1) {
        	execution = jobLauncher.run((Job)context.getBean("casherTKJob"), jobParameters);
        } else if (fileName.indexOf("TR_") != -1) {
        	execution = jobLauncher.run((Job)context.getBean("casherTRJob"), jobParameters);
        } else {
        	throw new Exception("Do not execution.");
        }
        
        System.out.println("Exit Status : " + execution.getStatus());


    } catch (Exception e) {
        e.printStackTrace();
    }

    System.out.println("Done");

  }
}