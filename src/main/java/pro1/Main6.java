package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;
import java.util.HashMap;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID


        String json = Api.getActionsByDepartment(department,year);
        String json2 = Api.getTeachersByDepartment(department);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);
        TeachersList teachers = new Gson().fromJson(json2, TeachersList.class);
        HashMap<Long,Integer> hashMap= new HashMap<>();
        actions.items.stream().forEach(a -> {
            int current = hashMap.getOrDefault(a.teacherId,0);
            hashMap.put(a.teacherId,current + a.personsCount);
        });

        return hashMap.entrySet().stream().max(Comparator.comparing(h-> h.getValue())).get().getKey();
    }
}