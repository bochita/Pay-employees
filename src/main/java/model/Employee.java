package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public String name;
    public List<WorkDay> workDays = new ArrayList<>();

    public Integer earns() {
        return workDays.stream().map(WorkDay::payAmount).mapToInt(Integer::intValue).sum();
    }
}