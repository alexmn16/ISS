package domain;


import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

public class Bug extends Entity<Integer>{
    private String name;
    private String description;
    private String bugStatus;

    public Bug(String name, String description, String bugStatus) {
        this.name = name;
        this.description = description;
        this.bugStatus = bugStatus;
    }

    public Bug() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", bugStatus='" + bugStatus + '\'' +
                '}';
    }
}
