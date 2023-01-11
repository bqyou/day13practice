package DietDiary.CaloREE.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Food {

    @NotEmpty(message = "Your food has no name?")
    private String name;

    @NotNull(message = "Please select a type")
    private String type;

    @NotNull(message = "What are you eating? Air?")
    private String size;

    private Integer weight;

    private Integer cal;

    public String getName() {
        return name;
    }

    public Integer getCal() {
        return cal;
    }

    public void setCal(Integer cal) {
        this.cal = cal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
        switch (size) {
            case "Smol":
                this.weight = 100;
                break;
            case "Med":
                this.weight = 200;
                break;
            case "HUGE":
                this.weight = 300;
                break;
        }
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
