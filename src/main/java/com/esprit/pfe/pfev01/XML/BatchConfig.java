package com.esprit.pfe.pfev01.XML;

import com.esprit.pfe.pfev01.Model.Records;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public XMLProcessor processor(){
        return new XMLProcessor();
    }

    @Bean
    public StaxEventItemReader<Records> reader(){
        StaxEventItemReader<Records> reader = new StaxEventItemReader<Records>();
        reader.setResource(new ClassPathResource("records.xml"));
        reader.setFragmentRootElementName("records");
        Map<String,String> aliasesMap =new HashMap<String,String>();
        aliasesMap.put("Records", "com.infotech.batch.model.Person");
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(aliasesMap);
        reader.setUnmarshaller(marshaller);
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<Records> writer(){
        JdbcBatchItemWriter<Records> writer = new JdbcBatchItemWriter<Records>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO records(numercompte,nom,prenom,debit,credit) VALUES(?,?,?,?,?)");
        writer.setItemPreparedStatementSetter(new RecordsPreparedStatementSetter());
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").<Records,Records>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
    }

    @Bean
    public Job exportPerosnJob(){
        return jobBuilderFactory.get("importRecordsJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
    }
}
