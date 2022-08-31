package egov.sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

public class FileUtil {

    public static String readTextFile(String path) {

        System.out.println("===>>> path = "+path);
        String result = "";
        /*try {
            File resource = new ClassPathResource(path).getFile();
            result = new String(Files.readAllBytes(resource.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
		//URL fileUrl = FileUtil.getClass().getResource(path);
		

        return result;
    }

}
