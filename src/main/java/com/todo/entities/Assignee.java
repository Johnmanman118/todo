package com.todo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Assignee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private  int id;
    private String name;
    private String role;
    private int age;
    private String task; //	- has tasks todo (one assignee can have multiple todo tasks)
    private String address; //	- has Address (one for each)
}
