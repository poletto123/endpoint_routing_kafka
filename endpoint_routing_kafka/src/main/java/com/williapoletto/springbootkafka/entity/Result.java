package com.williapoletto.springbootkafka.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {

	private List<String> results = new ArrayList<>();
	
	public void addResult(String result) {
		results.add(result);
	}
	
	public void removeResult(String result) {
		results.remove(result);
	}
	
}
