package com.hanse.codechallenge.repository;

import com.hanse.codechallenge.persistence.entity.PersistedMonitoringJob;
import com.hanse.codechallenge.persistence.repository.MonitoringJobRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class MonitoringJobRepositoryIntegrationTest {

    @Autowired
    private MonitoringJobRepository monitoringJobRepository;


    @BeforeEach
    @AfterEach
    public void setup(){
        monitoringJobRepository.deleteAll();
        monitoringJobRepository.flush();
    }

    @Test
    public void testFindTopByJobName() {

        PersistedMonitoringJob job1 = new PersistedMonitoringJob();
        job1.setJobName("Test Job");
        PersistedMonitoringJob job2 = new PersistedMonitoringJob();
        job2.setJobName("Test Job 2");
        monitoringJobRepository.saveAll(List.of(job1, job2));

        Optional<PersistedMonitoringJob> optionalJob = monitoringJobRepository.findTopByJobName("Test Job");

        Assertions.assertTrue(optionalJob.isPresent());
        Assertions.assertEquals("Test Job", optionalJob.get().getJobName(), "Job name should match");
    }
}
