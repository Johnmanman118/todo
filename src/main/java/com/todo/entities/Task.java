package com.todo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String taskName;
    private String startDate;
    private String deadLine;
    private String location;
    private double budget;
    private String assignee; //	- has assignee (one task can have multiple assigni's)
//    @Version
//    private Integer version;
    private String status;
    private String blocker;
}
