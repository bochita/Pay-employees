class reader:
used to read employee.txt file.
checks the file exists when it opens, and checks the format of the file is correct, (name,dayhourfom-hourTo,...)
loads the employee into an instance so the rest of the program can work with it.

class employee:
contains the name and a list containing the workDays, which contains the day, start and finnish time.
the method earns tells us how much the employee earns fot the worked days.

class workday:
contains a lot of information, the time limits so we can consult later more clearly avoiding the use of magic numbers.
the tariffs, which can be easily modified, or packed inside another class in case we have to apply different tariffs for
different seniorities.
it also contains the start and finish times of the work day

enum day:
the days are an enumerated value because the days of the week are static and aren't going to be changed in the future,
I used only SA and SU but added the rest so its complete.


to run the program correctly, the file with the employee's information should be put in the resources folder. 
