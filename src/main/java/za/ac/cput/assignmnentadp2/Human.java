/*
 * Students.java 
 * @author Elvis Princely Ndlangamandla
 * 12 May 2021
   213063964
 */
package za.ac.cput.assignmnentadp2;


public class Human {
    private String firstName = "firstName";
    private String lastName = "lastName";
    private int Age = 99;
    private String gender = null;

    public int getAge(){
        return Age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age){
        Age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Human(String firstName, String lastName, int Age, String gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.Age = Age;
        this.gender = gender;
    }

}
    


    

