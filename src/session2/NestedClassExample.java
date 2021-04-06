package session2;

public class NestedClassExample {
    private final Name name;
    private String firstName;
    private String lastName;

    public NestedClassExample(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        name = new Name();
    }

    public String getFullName(){

        return name.formatName();
    }

    public void printFullName(){
        System.out.println("name.formatName() = " + name.formatName());
    }

    class Name{

        public String formatName(){

            return String.format("Name: %s , Last Name: %s", firstName, lastName);
        }
    }


    public static void main(String[] args) {
        NestedClassExample nestedClassExample = new NestedClassExample("Ahmad", "Mosa");
        String fullName = nestedClassExample.getFullName();
        System.out.println("fullName = " + fullName);
        nestedClassExample.printFullName();
    }
}
