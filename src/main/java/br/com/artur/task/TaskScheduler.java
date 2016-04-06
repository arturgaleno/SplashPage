package br.com.artur.task;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.deltaspike.scheduler.spi.Scheduler;

@Startup
@Singleton
public class TaskScheduler {

    @SuppressWarnings("rawtypes")
	@Inject
    private Scheduler jobScheduler;

    @SuppressWarnings("unchecked")
	@PostConstruct
    public void registerJobs() {
    	
        jobScheduler.registerNewJob(TaskSendPendingSubmits.class);
        jobScheduler.registerNewJob(TaskTransposeAnswers.class);
    }


}
