package com.sys.utils.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import com.sys.model.SysQuartzJob;

/**
 * @Description: 定时任务服务类
 * 
 * @ClassName: QuartzManager
 * @Copyright: Copyright (c) 2014
 * @version V2.0
 */
public class QuartzManager {
	private static SchedulerFactory schedulerfactory = new StdSchedulerFactory();

	/**
	 * 计划中JOB
	 * 
	 * @return
	 */
	public static List<SysQuartzJob> getPlaningJobs() {
		List<SysQuartzJob> jobList = new ArrayList<SysQuartzJob>();
		Scheduler scheduler;
		try {
			scheduler = schedulerfactory.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler
						.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					SysQuartzJob job = new SysQuartzJob();
					job.setJobName(jobKey.getName());
					job.setJobGroupName(jobKey.getGroup());
					job.setRemark("触发器:" + trigger.getKey());
					Trigger.TriggerState triggerState = scheduler
							.getTriggerState(trigger.getKey());
					job.setStatus(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setCronExpression(cronExpression);
					}
					jobList.add(job);
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobList;
	}

	/**
	 * 运行中JOB
	 * 
	 * @return
	 */
	public static List<SysQuartzJob> getRuningJobs() {
		List<SysQuartzJob> jobList = new ArrayList<SysQuartzJob>();
		Scheduler scheduler;
		try {
			scheduler = schedulerfactory.getScheduler();
			List<JobExecutionContext> executingJobs = scheduler
					.getCurrentlyExecutingJobs();
			for (JobExecutionContext executingJob : executingJobs) {
				SysQuartzJob job = new SysQuartzJob();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				job.setJobName(jobKey.getName());
				job.setJobName(jobKey.getGroup());
				job.setRemark("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler
						.getTriggerState(trigger.getKey());
				job.setStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobList;
	}

	/**
	 * 暂停任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws SchedulerException
	 */
	public static void pauseJob(SysQuartzJob job) throws SchedulerException {
		Scheduler scheduler;
		scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws SchedulerException
	 */
	public static void resumeJob(SysQuartzJob job) throws SchedulerException {
		Scheduler scheduler;
		scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除任务后，所对应的trigger也将被删除
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws SchedulerException
	 */
	public static void delJob(SysQuartzJob job) throws SchedulerException {
		Scheduler scheduler;
		scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 立即运行一次
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws SchedulerException
	 */
	public static void triggerJob(SysQuartzJob job) throws SchedulerException {
		Scheduler scheduler;
		scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新时间
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	public static void updateCronExpression(String jobName,
			String jobGroupName, String cronExpression)
			throws SchedulerException {
		Scheduler scheduler;
		scheduler = schedulerfactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(cronExpression);

		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();

		// 按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);

	}

	public static void runJob(SysQuartzJob job) throws SchedulerException,
			ClassNotFoundException {

		Scheduler scheduler = schedulerfactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
				job.getJobGroupName());

		CronTrigger trigger = null;
		JobDetail jobDetail = JobBuilder
				.newJob((Class<? extends Job>) Class.forName(job.getJobClass()))
				.withIdentity(job.getJobName(), job.getJobGroupName()).build();
		jobDetail.getJobDataMap().put("scheduleJob", job);

		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(job.getCronExpression());

		// 按新的cronExpression表达式构建一个新的trigger
		trigger = TriggerBuilder.newTrigger()
				.withIdentity(job.getJobName(), job.getJobGroupName())
				.withSchedule(scheduleBuilder).build();

		scheduler.scheduleJob(jobDetail, trigger);
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}

	}

	/**
	 * NONE：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除 NORMAL:正常状态 PAUSED：暂停状态
	 * COMPLETE：触发器完成，但是任务可能还正在执行中 BLOCKED：线程阻塞状态 ERROR：出现错误
	 * 
	 * @param job
	 * @return
	 */
	public static String getTriggerState(SysQuartzJob job) {
		Scheduler scheduler;
		try {
			scheduler = schedulerfactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
					job.getJobGroupName());
			return scheduler.getTriggerState(triggerKey).name();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}
}