package com.example.candidateservice.dto;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobDto {
	private String id;
	private String description;
	private String company;
	private Set<String> skills;
	private Integer salary;
	private boolean isRemote;
}

