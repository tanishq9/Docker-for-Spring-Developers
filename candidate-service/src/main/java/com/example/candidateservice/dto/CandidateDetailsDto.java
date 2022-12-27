package com.example.candidateservice.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CandidateDetailsDto extends CandidateDto {
	List<JobDto> recommendedJobs;
}
