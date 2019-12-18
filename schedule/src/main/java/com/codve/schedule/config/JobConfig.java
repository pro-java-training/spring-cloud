package com.codve.schedule.config;

import com.codve.schedule.job.PrintJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/18 18:14
 */
@Configuration
public class JobConfig {

    private String cron = "0/3 * * * * ?";
    private int shardingTotalCount = 3;
    private String shardingItemParameters = "0=A,1=B,2=C";
    private final String jobParameters = "parameter";

    @Autowired
    private ZookeeperRegistryCenter registryCenter;

    @Bean
    public PrintJob printJob() {
        return new PrintJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler scheduler(PrintJob printJob) {
        Class cls = printJob.getClass();
        return new SpringJobScheduler(printJob, registryCenter, jobConfig(cls));
    }

    private LiteJobConfiguration jobConfig(Class<? extends DataflowJob<String>> jobClass) {
        JobCoreConfiguration coreConfig = JobCoreConfiguration
                .newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters)
                .jobParameter(jobParameters).build();
        DataflowJobConfiguration simpleConfig = new DataflowJobConfiguration(coreConfig, jobClass.getCanonicalName(), true);
        return LiteJobConfiguration.newBuilder(simpleConfig).overwrite(true).build();

    }
}
