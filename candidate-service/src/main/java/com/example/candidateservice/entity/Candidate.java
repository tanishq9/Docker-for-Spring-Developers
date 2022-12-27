package com.example.candidateservice.entity;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidate_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
	@Id
	String id;
	String name;
	Set<String> skills;
}
