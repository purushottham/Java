package com.websystique.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String areg[]) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-context.xml");

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("examResultJob");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Exit Status : " + execution.getStatus());

		} catch (JobExecutionException e) {
			System.out.println("Job ExamResult failed");
			e.printStackTrace();
		}
	}

	//Useful links
	// reading data from database using spring batch example
	// https://howtodoinjava.com/spring-batch/csv-to-database-java-config-example/(H2DATbase)
	// https://www.jackrutorial.com/2018/03/spring-boot-batch-read-from-mysql-database-and-write-into-a-csv-file-tutorial.html
	// https://www.javaworld.com/article/2458888/spring-framework/open-source-java-projects-spring-batch.html

}
