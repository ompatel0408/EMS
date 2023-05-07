package com.util;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;

public class Trial implements Job{
	
	private static Scheduler scheduler;
	private static JobDetail job;
	public void execute(JobExecutionContext context) throws JobExecutionException {
        // Your logic here
		SendMail.mailTokenEveryDay();
//		SendMail.sendMailForError();
    }
	
	public static void DailyMailService() {
		
		Date startDate = DateBuilder.todayAt(4,00, 00);
		 job = newJob(Trial.class)
			    .withIdentity("myJob", "group1")
			    .build();

			// Create a new trigger that runs the job every day at 10 AM
			Trigger trigger = newTrigger()
			    .withIdentity("myTrigger", "group1")
			    .startAt(startDate)
			    .withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever())
			    .build(); 

			// Schedule the job with the trigger
				
			try {
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void stopDailyMailService() {
		try {
			scheduler.interrupt(job.getKey());
		} catch (UnableToInterruptJobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
