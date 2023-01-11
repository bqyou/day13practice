package DietDiary.CaloREE.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import DietDiary.CaloREE.Model.Food;
import DietDiary.CaloREE.Model.Foodlist;
import DietDiary.CaloREE.Model.User;
import DietDiary.CaloREE.Model.Users;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/home")
public class CaloriesController {

    @Autowired
    Users users;

    @Autowired
    ApplicationArguments appArgs;

    private final String DEFAULT_DATADIR = "C:/Users/bingq/src/day13practice/data";

    private Integer iniAmr;
    private Integer amr;
    private Foodlist foodlist = new Foodlist(amr);

    @GetMapping
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping
    public String processForm(@Valid User user, BindingResult bResult, Model model) throws IOException {
        if (bResult.hasErrors()) {
            return "home";
        }
        users.saveUser(user, model, appArgs, DEFAULT_DATADIR);
        iniAmr = user.getActiveMR();
        amr = user.getActiveMR();
        return "result";
    }

    @GetMapping(path = "/{id}")
    public String getResultById(Model model, @PathVariable String id) {
        users.getUserbyId(model, id, appArgs, DEFAULT_DATADIR);
        User tmp = (User) model.getAttribute("user");
        iniAmr = tmp.getActiveMR();
        amr = tmp.getActiveMR();
        return "result";
    }

    @GetMapping(path = "/logfood")
    public String logFood(Model model) {
        foodlist.setAmr(amr);
        model.addAttribute("foodlist", foodlist);
        Food food = new Food();
        model.addAttribute("food", food);
        return "log";
    }

    @PostMapping(path = "/log")
    public String log(@Valid @ModelAttribute("food") Food food, BindingResult bResult, Model model) {
        if (bResult.hasErrors()) {
            model.addAttribute("foodlist", foodlist);
            return "log";
        }
        foodlist.addFood(food);
        foodlist.calcRemainAmr(food);
        model.addAttribute("foodlist", foodlist);
        model.addAttribute("food", new Food());
        return "log";
    }

    @PostMapping(path = "/clear")
    public String clear(Model model) {
        System.out.println(iniAmr);
        foodlist.getFoodList().clear();
        foodlist.setAmr(iniAmr);
        model.addAttribute("foodlist", foodlist);
        Food food = new Food();
        model.addAttribute("food", food);
        return "log";
    }
}
