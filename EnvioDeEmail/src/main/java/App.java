import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<String> to = new HashSet<String>();
		to.add("brizadeemail@gmail.com");
		
		String from = "brizadeemail@gmail.com";
		String password = "quake3arena";
		Set<String> cc = new HashSet<String>();
		String subject = "teste";
		String text = "Email de teste.";
		String html = "";
		Set<String> paths = new HashSet<String>();
		
		
		Email.send(to, from, password, cc, subject, text, html, paths);

	}

}
