import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Lesson5 {
    public static void main(String[] args){
        Employee[] employees = generateEmployees();
        System.out.println("All employees: ");
        printEmployees(employees);
        System.out.println();
        System.out.println("Employees older 40: ");
        printEmployeesOlderForty(employees);
        System.out.println();
    }

    public static Employee[] generateEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        int randomAge;

        for (int i = 0; i < 4; i++){
            employees.add(generateEmployee(i +  1));
        }

        employees.add(new Employee(new FIO("Name5", "Surname5", "Patronymic5"),
                "position5", "employee_emailgeekbrains.ru", "892700",
                new BigDecimal(5 * 10000.00), randomAge()));

        return employees.toArray(new Employee[employees.size()]);
    }

    public static Employee generateEmployee(int i){
        return new Employee(new FIO("Name" + i, "Surname" + i, "Patronymic" + i),
                "position" + i, "employee"+i+"_email@geekbrains.ru", "8927000000" + i,
                new BigDecimal(i * 10000.00), randomAge());
    }

    public static void printEmployees(Employee[] employees){
        for (Employee e: employees) {
            e.display();
        }
    }

    public static void printEmployeesOlderForty(Employee[] employees){
        for (Employee e: employees) {
            if (e.getAge() > 40){
                e.display();
            }
        }
    }

    public static byte randomAge(){
        return (byte)ThreadLocalRandom.current().nextInt(16, 66);
    }
}
