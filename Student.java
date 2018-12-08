import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.StringTokenizer; 
public class Student{
    private String name;
    private int id;
    private String school;

    /** Default constructor */
    public Student(){

    }

    /** Student- the parameterized constructor for the student class 
     * @param name- The name of the student
     * @param id- The student ID number
     * @param school- The student's current school that they are attending */
    public Student(String name, int id, String school) {
        this.name = name;
        this.id = id;
        this.school = school;
    }

    /** getName- returns the students current name
     * @return the students name */
    public String getName() {
        return this.name;
    }

    /** setName- updates the name field of the Student
     * @param name- the updated name of the student  */
    public void setName(String name) {
        this.name = name;
    }
    
    /** getId- returns the Students currentId
     * @return the ID of the student */ 
    public int getId() {
        return this.id;
    }

    /** setId- updates the ID field of the Student
     * @param id- the id of the Student */
    public void setId(int id){
        this.id = id;
    }

    /** getSchool- returns the school the Student is currently in
     * @return the school the Student is in */
    public String getSchool() {
        return this.school;
    }

    /** setSchool- sets the school of the Student
     * @param school- the new school of the Student */
    public void setSchool(String school) {
        this.school = school;
    }
    
    @Override
    /** returns a string with the fields of the student */
    public String toString() {
        return "Name: " + this.name + " id:" + this.id + " School: " + this.school;
    }


    /** parseFile parses the csv input file and populates an arrayList of student objects built from the CSV data
     * @param reader- the file that we are reading from 
     * @return an ArrayList of students built from CSV data  */
    public static ArrayList<Student> parseFile(BufferedReader reader)throws IOException{
    
        String currentLine = reader.readLine();
        ArrayList<Student> listOfStudents = new ArrayList<Student>();

        while(currentLine != null){
            StringTokenizer tok = new StringTokenizer(currentLine, ",");
            Student currentStudent = new Student();
            currentStudent.setId(Integer.parseInt(tok.nextToken()));
            currentStudent.setName(tok.nextToken());
            currentStudent.setSchool(tok.nextToken());
            listOfStudents.add(currentStudent);
            currentLine = reader.readLine();
        }
        return listOfStudents;
    }

    /** createAndPopulateFile - creates a txt file for each school named after the school
     * @param listOfStudents- the previously made list of student objects 
     * @param school- the current school we are creating a file for
     */
    public static void createAndPopulateFile(ArrayList<Student> listOfStudets, String school){
        String fileName = school + ".txt";
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(fileName);
        }catch(FileNotFoundException e){
            System.out.println("ERROR");
        }
   
        for(Student currentStudent : listOfStudets){
            if(currentStudent.getSchool().equalsIgnoreCase(school)){
                pw.print(currentStudent.getId() + " ");
                pw.print(currentStudent.getName() + " ");
                pw.print(currentStudent.getSchool() + "\n" );
            }
        }
        pw.close();
    }

    public static void main(String[] args)throws IOException{
        final String INPUT_FILE_NAME = args[0];
        FileReader inFile = null;
        BufferedReader reader = null;

        try{
            inFile = new FileReader(INPUT_FILE_NAME);
            reader = new BufferedReader(inFile);
        } catch(FileNotFoundException e){
            System.out.println("Invalid file/ file is not in the correct folder");
            return; // exit the app.
        }
        
        ArrayList<Student> listOfStudents = parseFile(reader);
        ArrayList<String> listOfSchools = new ArrayList<String>();
        for(int i = 0; i < listOfStudents.size(); i++){
            if(!listOfSchools.contains(listOfStudents.get(i).getSchool()))
                listOfSchools.add(listOfStudents.get(i).getSchool());
        }
        System.out.println(listOfSchools);
        for(String currentSchool : listOfSchools){
            createAndPopulateFile(listOfStudents, currentSchool);
        }

    }

}   