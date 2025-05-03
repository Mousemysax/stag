package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.DeadlineList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main7 {

    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }
    public static String specializationDeadlines(int year){
        String json = Api.getSpecializations(year);
        DeadlineList deadlines = new Gson().fromJson(json,DeadlineList.class);

        return deadlines.items.stream().map(d -> d.dValue).map(dv -> dv.value).distinct().sorted(Comparator.comparing(Main7::stringToSort)).collect(Collectors.joining(","));
        //return LocalDate.parse("04.5.2025",dtf).toString();
    }

    public static long stringToSort(String date){
        long result = 0;
        String data[] = date.split("\\.");
        result += Integer.parseInt(data[0]) +Integer.parseInt(data[1])*1000 + Integer.parseInt(data[2])*10000000;
        return result;
    }

}
