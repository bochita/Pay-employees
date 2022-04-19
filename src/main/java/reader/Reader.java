package reader;

import model.WeekDay;
import model.Employee;
import model.WorkDay;

import java.io.File;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    private Scanner scanner;

    public void openFile() {

        try {
            scanner = new Scanner(new File("src/main/resources/employees.txt"));
            scanner.useDelimiter("\n");
        } catch (Exception exception) {
            System.out.println("file not found");
        }
    }

    public List<Employee> readEmployees() {
        List<Employee> emps = new ArrayList<>();

        while (scanner.hasNext()) {
            Employee emp = new Employee();
            String line = scanner.next();
            int iend = line.indexOf("=");
            if (iend != -1) {
                emp.name = line.substring(0, iend);
                String workdaysRaw = line.substring(iend + 1);
                List<String> wDayList = Stream.of(workdaysRaw.split(",")).map(String::trim).collect(Collectors.toList());
                wDayList.forEach(wd -> {
                    WorkDay workDay = new WorkDay();
                    workDay.defineDay(WeekDay.valueOf(wd.substring(0, 2)));
                    workDay.hourFrom = LocalTime.of(Integer.parseInt(wd.substring(2, 4)), Integer.parseInt(wd.substring(5, 7)));
                    workDay.hourTo = LocalTime.of(Integer.parseInt(wd.substring(8, 10)), Integer.parseInt(wd.substring(11, 13)));
                    emp.workDays.add(workDay);
                });
                emps.add(emp);

            } else {
                throw new RuntimeException("Invalid document");
            }

        }
        return emps;
    }

    public void closeFile() {
        scanner.close();
    }

}