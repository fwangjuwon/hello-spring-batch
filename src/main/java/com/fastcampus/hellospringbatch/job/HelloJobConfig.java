package com.fastcampus.hellospringbatch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor //생성자주입
public class HelloJobConfig {
    //job생성 시 필요한 것들

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean("helloJob") //bean생성
    public Job helloJob(Step helloStep){
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())//job실행 시 횟수 일정하게 증가
                .start(helloStep)
                .build();
    }

    @JobScope
    @Bean("helloStep")
    public Step helloStep(Tasklet tasklet){
        return stepBuilderFactory.get("helloStep")
                .tasklet(tasklet).build();
    }

    @StepScope
    @Bean
    public Tasklet tasklet() {
        return (((contribution, chunkContext) -> {
            System.out.println("hello Spring Batch!");
            return RepeatStatus.FINISHED;
        }));
    }
}
