package com.example.jobservice.entity;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "job_collection") // MongoDB document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
	@Id
	private String id;
	private String description;
	private String company;
	private Set<String> skills;
	private Integer salary;
	private boolean isRemote;
}
