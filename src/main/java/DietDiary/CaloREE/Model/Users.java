package DietDiary.CaloREE.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

@Component("users")
public class Users {

    public void saveUser(User user, Model model,
            ApplicationArguments appArgs, String defaultDataDir) throws IOException {
        String dataFilename = user.getId();
        PrintWriter printwriter = null;
        FileWriter fileWriter = null;
        user.calcMR();
        try {
            fileWriter = new FileWriter(getDataDir(appArgs, defaultDataDir) + "/"
                    + dataFilename);
            printwriter = new PrintWriter(fileWriter);
            printwriter.println(user.getUsername());
            printwriter.println(user.getGender());
            printwriter.println(user.getAge());
            printwriter.println(user.getHeight());
            printwriter.println(user.getWeight());
            printwriter.println(user.getLifestyle());
            printwriter.println(user.getBaseMR());
            printwriter.println(user.getActiveMR());
            printwriter.println(user.getMcSpicy());
            printwriter.close();
        } catch (IOException e) {
            System.out.println("IO ex");
        }
        model.addAttribute("user",
                new User(user.getId(), user.getUsername(), user.getGender(), user.getAge(), user.getHeight(),
                        user.getWeight(), user.getLifestyle(), user.getBaseMR(), user.getActiveMR(),
                        user.getMcSpicy()));
    }

    private String getDataDir(ApplicationArguments appArgs,
            String defaultDataDir) {
        String dataDirResult = null;
        List<String> optValues = null;
        Set<String> opsNames = appArgs.getOptionNames();
        String[] optNamesArr = opsNames.toArray(new String[opsNames.size()]);
        if (optNamesArr.length > 0) {
            optValues = appArgs.getOptionValues(optNamesArr[0]);
            dataDirResult = optValues.get(0);
        } else {
            dataDirResult = defaultDataDir;
        }
        return dataDirResult;
    }

    public void getUserbyId(Model model, String id,
            ApplicationArguments appArgs, String defaultDataDir) {
        User u = new User();
        File file = new File(getDataDir(appArgs, defaultDataDir) + "/" + id);
        Path filePath = file.toPath();
        Charset charset = Charset.forName("UTF-8");
        try {
            List<String> stringListDat = Files.readAllLines(filePath, charset);
            u.setId(id);
            u.setUsername(stringListDat.get(0));
            u.setGender(stringListDat.get(1));
            u.setAge(Integer.parseInt(stringListDat.get(2)));
            u.setHeight(Float.parseFloat(stringListDat.get(3)));
            u.setWeight(Float.parseFloat(stringListDat.get(4)));
            u.setLifestyle(Integer.parseInt(stringListDat.get(5)));
            u.setBaseMR(Integer.parseInt(stringListDat.get(6)));
            u.setActiveMR(Integer.parseInt(stringListDat.get(7)));
            u.setMcSpicy(Double.parseDouble(stringListDat.get(8)));
        } catch (IOException e) {
            System.out.println("IO EX");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact info not found");
        }

        model.addAttribute("user", u);
    }

}
