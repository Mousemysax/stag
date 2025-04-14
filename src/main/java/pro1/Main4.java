package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
        String json = Api.getTeachersByDepartment(department);
        TeachersList teachers = new Gson().fromJson(json, TeachersList.class);
        teachers.items.stream().filter(teacher -> teacher.email != null).sorted(Comparator.comparing(teacher -> teacher.email.length())).limit(count).forEach(teacher -> {System.out.println(teacher.email);});

    }
}