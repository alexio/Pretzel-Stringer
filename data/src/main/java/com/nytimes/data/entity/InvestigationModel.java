package com.nytimes.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexio on 8/9/15.
 */
public class InvestigationModel implements Serializable{

    //Update val if class is changed
    private static final long serialVersionUID = 0L;

    private String title;
    private String description;

    public InvestigationModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<InvestigationModel> getDummyData() {
        return new ArrayList<InvestigationModel>() {{
            add(new InvestigationModel("Brooklyn Bridge Fire",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
        }};
    }
}
