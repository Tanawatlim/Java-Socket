import java.util.Date;

public class Person {
    private String name;
    private Date birthDate;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean isBirthDay(){
        return true;
    }

}
