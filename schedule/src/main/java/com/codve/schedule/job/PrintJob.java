package com.codve.schedule.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author admin
 * @date 2019/12/18 18:12
 */
@Slf4j
public class PrintJob implements DataflowJob<String> {

    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        return Arrays.asList("hello", "world");
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> list) {
        log.warn(list.toString());
    }
}
