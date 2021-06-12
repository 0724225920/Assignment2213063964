/*
 * Students.java 
 * @author Elvis Princely Ndlangamandla
 * 12 May 2021
   213063964
 */
package za.ac.cput.assignmnentadp2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class City {
  private String CityName;
    private int population;
    private int popLength;
    public Human[] thePeople;
    private ArrayList<String> maleFirsts = new ArrayList<String>();
    private ArrayList<String> femaleFirsts = new ArrayList<String>();
    private ArrayList<String> lasts = new ArrayList<String>();

    //Constructor

    public City(String cityName){

        this.CityName = cityName;

        Random rand = new Random();
        int randomNum = rand.nextInt((8000000 - 10000) + 1) + 10000;
        this.setPopulation(randomNum);  

        thePeople = new Human[(int) population];

        MaleFirstNames();
        FemaleFirstNames();
        LastNames();

        for(int i = 0; i < thePeople.length; i++){

            String MFirstName = "";
            Random MfirstNames = new Random();
            int MfirstNameRand = MfirstNames.nextInt((maleFirsts.size() - 0) + 0);
            MFirstName = maleFirsts.get(MfirstNameRand);

            String FFirstName = "";
            Random FfirstNames = new Random();
            int FfirstNameRand = FfirstNames.nextInt((maleFirsts.size() - 0) + 0);
            FFirstName = femaleFirsts.get(FfirstNameRand);

            String LastName = "";
            Random LastNameRand = new Random();
            int LastNameRandomNumber = LastNameRand.nextInt((lasts.size() - 0) + 0);
            LastName = lasts.get(LastNameRandomNumber);

            Random AgeRand = new Random();
            int Age = AgeRand.nextInt((101 - 1) + 1);

            if(i < thePeople.length / 2){
                thePeople[i] = new Human(MFirstName, LastName, Age, "Male");
            }
            if(i == thePeople.length / 2){
                thePeople[i] = new Human(FFirstName, LastName, Age, "Female");
            }   
        }
        this.popLength = thePeople.length;
    }

    //Scan CSVs

    private void MaleFirstNames(){
        File maleFirstsCSV = new File("src/jamiesCity/male.firstnames.csv");
        try {
            Scanner maleFirstNames = new Scanner(maleFirstsCSV);
            while(maleFirstNames.hasNext()){
                String input = maleFirstNames.next();
                String values[] = input.split(",");
                maleFirsts.add(values[0]);
            }
            maleFirstNames.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Find Male First Names CSV File");
        }
    }

    private void FemaleFirstNames(){
        File maleFirstsCSV = new File("src/jamiesCity/female.firstnames.csv");
        try {
            Scanner femaleFirstNames = new Scanner(maleFirstsCSV);
            while(femaleFirstNames.hasNext()){
                String input = femaleFirstNames.next();
                String values[] = input.split(",");
                femaleFirsts.add(values[0]);
            }
            femaleFirstNames.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Find Female First Names CSV File");
        }
    }

    private void LastNames(){
        File maleFirstsCSV = new File("src/jamiesCity/CSV_Database_of_Last_Names.csv");
        try {
            Scanner lastNames = new Scanner(maleFirstsCSV);
            while(lastNames.hasNext()){
                String input = lastNames.next();
                String values[] = input.split(",");
                lasts.add(values[0]);
            }
            lastNames.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Find Last Names CSV File");
        }
    }

    //Getters and Setters

    public String getFirstNameOfPerson(int HumanCount){
        String name = thePeople[HumanCount].getFirstName();
        return name;
    }
    public String getGenderOfPerson(int HumanCount){
        String gender = thePeople[HumanCount].getGender();
        return gender;
    }
    public String getLastNameOfPerson(int HumanCount){
        String name = thePeople[HumanCount].getLastName();
        return name;
    }
    public int getAgeOfPerson(int HumanCount){
        int age = thePeople[HumanCount].getAge();
        return age;
    }

    public String getAgeOfPersonAsString(int HumanCount){
        StringBuilder age = new StringBuilder();
        age.append(thePeople[HumanCount].getAge());
        String theAge = age.toString();
        return theAge;
    }

    public int getPopLength() {
        return popLength;
    }

    public void setPopLength(int i){
        this.popLength = i;
    }

    public int getPopulation() {
        return population;
    }

    public String getPopulationAsString() {
        StringBuilder builder = new StringBuilder();
        builder.append(population);
        String pop = builder.toString();
        return pop;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    //End of Getters and Setters
}
    

   

