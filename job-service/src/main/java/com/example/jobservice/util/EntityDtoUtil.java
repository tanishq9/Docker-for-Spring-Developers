package com.example.jobservice.util;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.entity.Job;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
	public static JobDto toDto(Job job) {
		JobDto jobDto = new JobDto();
		BeanUtils.copyProperties(job, jobDto);
		return jobDto;
	}

	public static Job toEntity(JobDto jobDto) {
		Job job = new Job();
		BeanUtils.copyProperties(jobDto, job);
		return job;
	}
}
