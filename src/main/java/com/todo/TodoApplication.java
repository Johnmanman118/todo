package com.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
//		int[] unfilteredArrays = {7,58,23,19,-20, 35, 73, 98, -100};
//		List<Integer> filteredList = Arrays.stream(unfilteredArrays)
//				.filter(i->i%2==0)
//				.map(a->a*a)
//				.boxed()
//				.sorted().collect(Collectors.toList());
//		System.out.println("result should be "+ filteredList);
		SpringApplication.run(TodoApplication.class, args);
	}

}
