import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Employee {
    private FIO fio;
    private String position;
    private String email;
    private String phoneNumber;
    private BigDecimal salary;
    private byte age;

    private static String emailPattern = "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,6}$";
    private static String phonePattern = "^8[0-9]{10}$";

    public Employee(){}

    public Employee(FIO fio, String position, String email, String phoneNumber, BigDecimal salary, byte age){
        this.fio = fio;
        this.position = position;

        if (isValid(emailPattern, email)){
            this.email = email;
        }
        else {
            this.email = "Error.";
        }

        if (isValid(phonePattern, phoneNumber)){
            this.phoneNumber = phoneNumber;
        }
        else {
            this.phoneNumber = "Error.";
        }

        this.salary = salary;

        if (age > 0){
            this.age = age;
        }
        else {
            this.age = -1;
        }

    }

    private boolean isValid(String patternString, String text){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public void display(){
        System.out.printf("FIO: %s %s %s \n", fio.getName(), fio.getSurname(), fio.getPatronymic());
        System.out.printf("Position: %s\n", position);
        System.out.printf("Email: %s \n", email);
        System.out.printf("Phone: %s \n", phoneNumber);
        System.out.printf("Salary: %.2f \n", salary);
        System.out.printf("Age: %d \n", age);
        System.out.println();
    }

    public byte getAge(){
        return age;
    }
}
