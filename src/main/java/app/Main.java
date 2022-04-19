package app;

import model.Employee;
import reader.Reader;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {


        Reader reader = new Reader();
        List<Employee> employees = new ArrayList<>();

        reader.openFile();
        employees = reader.readEmployees();
        reader.closeFile();
        employees.forEach(emp -> {System.out.printf("the Employee %s, earns %d dollars %n",emp.name,emp.earns());});
    }

    public static Boolean isMorning(LocalTime time){
        return time.isAfter(LocalTime.of(0,0)) && time.isBefore(LocalTime.of(9,1));
    }
    public static   Boolean isDay(LocalTime time) {
        return time.isAfter(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(18, 1));
    }
    public static Boolean isNight(LocalTime time) {
        return time.isAfter(LocalTime.of(18, 0)) || LocalTime.MIDNIGHT.equals(time);
    }
}

