package egov.sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

public class TestFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// String filePath = "classpath:/src/main/resources/data/req_spec.dat";
		String filePath = "data/req_spec.dat";
		String result = "";

//		URL url = ClassLoader.getSystemClassLoader().getResource(filePath);
//		String path = url.getFile();

		try {
            File resource = new ClassPathResource(filePath).getFile();
            result = new String(Files.readAllBytes(resource.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		/*InputStream stream = TestFile.class.getResourceAsStream(filePath);
		System.out.println(stream != null);
		stream = TestFile.class.getClassLoader().getResourceAsStream(filePath);
		System.out.println(stream != null);*/

		/*
		 * try { File resource = new ClassPathResource(filePath).getFile();
		 * result = new String(Files.readAllBytes(resource.toPath())); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		System.out.println("result = " + result);
		
		
		/*TestFile test = new TestFile();
		ClassLoader classLoader = test.getClass().getClassLoader();
		

		URL resource = classLoader.getResource(filePath);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        }*/
        
	}

}
