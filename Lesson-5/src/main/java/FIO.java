public class FIO {
    private String name;
    private String surname;
    private String patronymic;

    public FIO(){}

    public FIO(String name, String surname, String patronymic){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getPatronymic(){
        return patronymic;
    }
}
