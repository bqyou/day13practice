package DietDiary.CaloREE;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import DietDiary.CaloREE.Model.IOutil;

@SpringBootApplication
public class CaloReeApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(CaloReeApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsVal = appArgs.getOptionValues("dataDir");
		if (opsVal != null) {
			IOutil.createDir(opsVal.get(0));
		} else {
			System.exit(1);
		}
		app.run(args);
	}
}
