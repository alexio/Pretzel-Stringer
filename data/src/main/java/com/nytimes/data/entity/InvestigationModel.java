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
            add(new InvestigationModel("Fire in the East Village", "Details"));
            add(new InvestigationModel("Parade in Central Park", "Parade Details"));
        }};
    }
}
