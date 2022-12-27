package com.example.candidateservice.util;

import com.example.candidateservice.dto.CandidateDetailsDto;
import com.example.candidateservice.dto.CandidateDto;
import com.example.candidateservice.entity.Candidate;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
	public static CandidateDto toDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		BeanUtils.copyProperties(candidate, candidateDto);
		return candidateDto;
	}

	public static CandidateDetailsDto toCandidateDetailsDto(Candidate candidate) {
		CandidateDetailsDto candidateDetailsDto = new CandidateDetailsDto();
		BeanUtils.copyProperties(candidate, candidateDetailsDto);
		return candidateDetailsDto;
	}

	public static Candidate toEntity(CandidateDto candidateDto) {
		Candidate candidate = new Candidate();
		BeanUtils.copyProperties(candidateDto, candidate);
		return candidate;
	}
}
