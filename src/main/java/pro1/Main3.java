package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;
import java.util.HashMap;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {
        // TODO 3.2:
        //  - Stáhni seznam učitelů na katedře
        //  - Stáhni seznam akcí na katedře
        //  - Najdi učitele s nejvyšším "score" a vrať jeho e-mail
        String json = Api.getActionsByDepartment(department,year);
        String json2 = Api.getTeachersByDepartment(department);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);
        TeachersList teachers = new Gson().fromJson(json2, TeachersList.class);
        HashMap<Long,Integer> hashMap= new HashMap<>();
        actions.items.stream().forEach(a -> {
            int current = hashMap.getOrDefault(a.teacherId,0);
            hashMap.put(a.teacherId,current + a.personsCount);
        });
        long bestTeacherId = hashMap.entrySet().stream().max(Comparator.comparing(h-> h.getValue())).get().getKey();
        return teachers.items.stream().filter(t->t.id==bestTeacherId).map(teacher -> teacher.email).toString();
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        return departmentSchedule.items.stream().filter(action -> action.teacherId == teacherId).mapToLong(a -> a.personsCount).sum(); // TODO 3.1: Doplň pomocnou metodu - součet všech přihlášených studentů na akcích daného učitele
    }
}