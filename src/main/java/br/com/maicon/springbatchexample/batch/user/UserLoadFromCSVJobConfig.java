package br.com.maicon.springbatchexample.batch.user;

import br.com.maicon.springbatchexample.model.User;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class UserLoadFromCSVJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<User> itemReader;
    private final ItemProcessor<User, User> itemProcessor;
    private final ItemWriter<User> itemWriter;

    @Bean
    public Job userLoadFromCSVJob() {
        return jobBuilderFactory.get("UserLoadFromCSVJob")
                .incrementer(new RunIdIncrementer())
                .start(userLoadFromCSV())
                .build();
    }

    @Bean
    public Step userLoadFromCSV() {
        return stepBuilderFactory.get("UserLoadFromCSV")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}
