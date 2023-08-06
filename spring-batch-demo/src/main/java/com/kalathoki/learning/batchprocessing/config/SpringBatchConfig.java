package com.kalathoki.learning.batchprocessing.config;

import com.kalathoki.learning.batchprocessing.entity.CustomerDetails;
import com.kalathoki.learning.batchprocessing.entity.Student;
import com.kalathoki.learning.batchprocessing.repository.CustomerDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 5:05 PM
 */

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private CustomerDetailsRepository customerDetailsRepository;


    @Bean
    public FlatFileItemReader<CustomerDetails> reader() {
        FlatFileItemReader<CustomerDetails> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }


    public JsonItemReader<Student> jsonItemReader() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Hello Ram");
        data.add("Hello Shyam");
        data.add("Hello Hari");
        return new JsonItemReaderBuilder<Student>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Student.class))
                .resource(new ClassPathResource("data.json"))
                .name("studentJsonItemReader")
                .build();
    }

    private LineMapper<CustomerDetails> lineMapper() {
        DefaultLineMapper<CustomerDetails> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

        BeanWrapperFieldSetMapper<CustomerDetails> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CustomerDetails.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public CustomerDetailsProcessor processor() {
        return new CustomerDetailsProcessor();
    }

    @Bean
    public RepositoryItemWriter<CustomerDetails> writer() {
        RepositoryItemWriter<CustomerDetails> writer = new RepositoryItemWriter<>();
        writer.setRepository(customerDetailsRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<CustomerDetails, CustomerDetails>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importCustomers")
                .flow(step1()).end().build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

}
