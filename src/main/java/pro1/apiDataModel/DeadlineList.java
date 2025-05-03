package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeadlineList {
    @SerializedName("prijimaciObor")
    public List<Deadline> items;
}
