package com.example.jobservice.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class JobDto {
	private String id;
	private String description;
	private String company;
	private Set<String> skills;
	private Integer salary;
	private boolean isRemote;
}
