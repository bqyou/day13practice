package DietDiary.CaloREE.Model;

import java.text.DecimalFormat;
import java.util.Random;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {

    private String id;

    @NotBlank(message = "You don't have a name?")
    private String username;

    @NotNull(message = "There are only 2 genders in the world, choose 1")
    private String gender; // use radio

    @NotNull(message = "Please enter your age for more accurate estimation")
    @Min(value = 18, message = "Please only start dieting after you are 18 years old")
    @Max(value = 70, message = "Please consult a doctor, you are too old for this")
    private Integer age;

    @NotNull(message = "Please enter your height for more accurate estimation")
    @Min(value = (long) 1.4, message = "No midgets allowed")
    @Max(value = (long) 2.4, message = "You sure you that tall?")
    private float height;

    @NotNull(message = "Please enter your weight for more accurate estimation")
    @Min(value = (long) 30, message = "You shouldn't be using this if you are this light")
    @Max(value = (long) 500, message = "bro you are hopeless no cap")
    private float weight;

    @NotNull(message = "You should be able to describe your lifestyle")
    private Integer lifestyle; // use radio

    private Integer activeMR;
    private Integer baseMR;
    private double mcSpicy;

    public Integer getBaseMR() {
        return baseMR;
    }

    public void setBaseMR(Integer baseMR) {
        this.baseMR = baseMR;
    }

    public User() {
        this.id = generateId(5);
    }

    public User(
            String username,
            String gender,
            Integer age,
            float height,
            float weight,
            Integer lifestyle) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
        this.id = generateId(5);
    }

    public User(
            String id,
            String username,
            String gender,
            Integer age,
            float height,
            float weight,
            Integer lifestyle,
            Integer baseMR,
            Integer activeMR,
            double mcSpicy) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
        this.id = id;
        this.baseMR = baseMR;
        this.activeMR = activeMR;
        this.mcSpicy = mcSpicy;
    }

    private synchronized String generateId(int numOfChar) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numOfChar) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numOfChar);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void calcMR() {
        Boolean isMale = (gender.equals("male")) ? true : false;
        this.baseMR = isMale ? (int) (66.47 + (13.75 * weight) + (5.003 * height * 100) - (6.755 * age))
                : (int) (655.1 + (9.563 * weight) + (1.850 * height * 100) - (4.676 * age));
        switch (lifestyle) {
            case 1:
                this.activeMR = (int) (this.baseMR * 1.2); // sedentary (no exercise)
                break;
            case 2:
                this.activeMR = (int) (this.baseMR * 1.375); // lightly active (1-3days/week)
                break;
            case 3:
                this.activeMR = (int) (this.baseMR * 1.55); // moderately active (3-5days/week)
                break;
            case 4:
                this.activeMR = (int) (this.baseMR * 1.725); // active (everyday)
                break;
            case 5:
                this.activeMR = (int) (this.baseMR * 1.9); // very active (hard exercise everyday)
                break;
        }
        this.mcSpicy = (double) this.activeMR / 522;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Integer getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Integer lifestyle) {
        this.lifestyle = lifestyle;
    }

    public Integer getActiveMR() {
        return activeMR;
    }

    public void setActiveMR(Integer activeMR) {
        this.activeMR = activeMR;
    }

    public double getMcSpicy() {
        DecimalFormat df = new DecimalFormat("##.#");
        mcSpicy = Double.parseDouble(df.format(mcSpicy));
        return mcSpicy;
    }

    public void setMcSpicy(double mcSpicy) {
        this.mcSpicy = mcSpicy;
    }

}
