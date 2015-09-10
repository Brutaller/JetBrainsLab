package interview.SecondTask;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.*;

/**
 * Created by Azat Zaripov on 09.09.2015.
 */
public class StudentSorterProgram implements StudentSorter {
    @Override
    public void process(InputStream input, OutputStream output) {
        Scanner sc = new Scanner(input);
        PrintWriter pw = new PrintWriter(output);
        ArrayList<StudentsGroup> students = new ArrayList<StudentsGroup>();     //a list of all groups with students
        while (sc.hasNext()){
            String[] student = sc.nextLine().split(":");    //student[0]-student name, student[1]-his group
            for (StudentsGroup stud : students) {
                if (stud.getGroup().equals(student[1])){    //if the group has added to the list
                    stud.add(student[0]);                   //student added to this group
                    student = null;
                    break;
                }
            }
            if (student != null){
                ArrayList<String> list = new ArrayList<String>();   //or add a new group
                list.add(student[0]);
                students.add(new StudentsGroup(student[1], list));
            }
        }
        Collections.sort(students, new Comparator<StudentsGroup>() {
            @Override
            public int compare(StudentsGroup o1, StudentsGroup o2) {
                return o2.getSize() - o1.getSize();     //Sorting by the number of students in group
            }
        });
        for (StudentsGroup students1 : students) {
            students1.sort();       //Sorting groups in alphabetical order
        }

        for (StudentsGroup students1 : students) {
            students1.print(pw);
        }
        pw.close();
        sc.close();
    }

    public class StudentsGroup {
        private String group;
        private ArrayList<String> students = new ArrayList<String>();

        public StudentsGroup(String group, ArrayList<String> students) {
            this.group = group;
            this.students = students;
        }

        public void add(String student){
            students.add(student);
        }

        public String getGroup() {
            return group;
        }

        public int getSize(){
            return students.size();
        }

        public void sort(){
            Collections.sort(students);
        }

        public void print (PrintWriter pw){
            StringBuilder builder = new StringBuilder();
            builder.append(group + ":");
            for (String st : students) {
                builder.append(" "+st+',');
            }
            builder.deleteCharAt(builder.length()-1);
            builder.append("\n");
            pw.write(new String(builder));
            pw.flush();
        }
    }
}
