// Add a new CronTrigger to existing durable job.
// args: config jobName triggerName cronExpression

import org.quartz.CronScheduleBuilder
import org.quartz.TriggerBuilder
import org.quartz.TriggerKey
import org.quartz.JobKey
import org.quartz.impl.StdSchedulerFactory

config = args[0]
scheduler = new StdSchedulerFactory(config).getScheduler()
try {
    jobKey = JobKey.jobKey(args[1])
    triggerKey = TriggerKey.triggerKey(args[2])
    cronExpression = args[3]
    trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .forJob(jobKey)
            .build()
    scheduler.scheduleJob(trigger)
    println("Job $triggerKey.name added")
} finally {
    scheduler.shutdown()
}