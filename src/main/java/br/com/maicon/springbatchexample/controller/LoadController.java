package br.com.maicon.springbatchexample.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/load")
@AllArgsConstructor
public class LoadController {

    private static final Logger logger = LoggerFactory.getLogger(LoadController.class);

    private final JobLauncher jobLauncher;
    private final Job job;

    @GetMapping
    public ResponseEntity<BatchStatus> load() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException,
            JobRestartException,
            JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> parameters = new HashMap<>();

        parameters.put("time", new JobParameter(System.currentTimeMillis()));

        JobExecution jobExecution = jobLauncher.run(job, new JobParameters(parameters));

        logger.info("JobExecution: " + jobExecution.getStatus());
        logger.info("Batch is running...");

        while (jobExecution.isRunning()) {
            logger.info("...");
        }

        return new ResponseEntity<>(jobExecution.getStatus(), HttpStatus.OK);
    }
}
