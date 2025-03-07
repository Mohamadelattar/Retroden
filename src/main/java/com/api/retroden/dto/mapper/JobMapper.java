package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.JobRequest;
import com.api.retroden.dto.response.JobResponse;
import com.api.retroden.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class JobMapper {
    public Job toJob(JobRequest jobRequest) {
        return Job.builder()
                .idJob(jobRequest.id())
                .title(jobRequest.title())
                .description(jobRequest.title())
                .build();
    }

    public JobResponse toJobResponse(Job job) {
        return JobResponse.builder()
                .title(job.getTitle())
                .description(job.getDescription())
                .build();
    }
}
