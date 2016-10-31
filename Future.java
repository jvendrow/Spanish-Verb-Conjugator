import java.util.HashMap;

public class Future extends Tense {

	public Future(String[] endings) {
		super(endings);
		putValues();
	}
	
	//Conjugates verbs in the Future tense and handles irregular verbs
	public void conjugate(String verb) {
		
		//Checks if a verb of any subsections of it are irregular
		if(checkForIreg(verb, IregRoots) >= 0) {
			int i = checkForIreg(verb, IregRoots);
			print(verb.substring(0, i) + IregRoots.get(verb.substring(i)), endings);
		} 
		
		else {
			print(verb, endings);
		}
	}
	
	HashMap<String, String> IregRoots = new HashMap<String, String>();
	
	//Fills a mashmap of irregular verbs in the Future tense
	public void putValues() {
		IregRoots.put("poder", "podr");
		IregRoots.put("querer", "querr");
		IregRoots.put("poner", "pondr");
		IregRoots.put("hacer", "hice");
		IregRoots.put("tener", "tendr");
		IregRoots.put("caber", "cabr");
		IregRoots.put("saber", "sabr");
		IregRoots.put("venir", "vendr");
		IregRoots.put("decir", "dir");
		IregRoots.put("haber", "habr");
	}
}
