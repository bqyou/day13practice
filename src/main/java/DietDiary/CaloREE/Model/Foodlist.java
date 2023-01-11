package DietDiary.CaloREE.Model;

import java.util.LinkedList;
import java.util.List;

public class Foodlist {
    private Integer amr;

    private List<Food> foodList = new LinkedList<Food>();

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Foodlist(Integer amr) {
        this.amr = amr;
    }

    public Integer getAmr() {
        return amr;
    }

    public void setAmr(Integer amr) {
        this.amr = amr;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void calcRemainAmr(Food food) {
        switch (food.getType()) {
            case "Protein":
                this.amr = this.amr - food.getWeight() * 2;
                food.setCal(food.getWeight() * 2);
                break;
            case "Fats":
                this.amr = this.amr - food.getWeight() * 5;
                food.setCal(food.getWeight() * 5);
                break;
            case "Carbo":
                this.amr = this.amr - food.getWeight() * 2;
                food.setCal(food.getWeight() * 2);
                break;
        }
    }
}
