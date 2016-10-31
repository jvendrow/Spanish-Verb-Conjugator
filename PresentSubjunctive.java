import java.util.HashMap;

public class PresentSubjunctive extends Present{
	
	public PresentSubjunctive(String[] endingsA, String[] endingsE, String[] endingsI) {
		super(endingsA, endingsE, endingsI);
		fillMap();
	}
	
	//Conjugates verbs in the Present Subjunctive tense and handles irregular verbs
	public void conjugate(String verb) {
		
		stemChange = stemChange(verb);
		String[] endings = endingsAEI(verb);
		
		//Checks of the verb is irregular
		if(Iregs.containsKey(verb)) {
			print("", Iregs.get(verb));
		}
		
		//Checks of a verb or any subsections of it have change matching that of the first person in the Present Tense
		else if (checkForIreg(verb, yoChange) >= 0) {		
			int i = checkForIreg(verb, yoChange);
			printYo(verb.substring(0, i), yoChange.get(verb.substring(i)), endings);	
		} 
		
		//Checks if the verb ends with "cer" or "cir" due to certain exceptions
		else if(verb.substring(verb.length()-3).equals("cer") || verb.substring(verb.length()-3).equals("cir")) {
			print(verb.substring(0, verb.length()-3) + "zc", endings);
		} 
		
		//Checks if the verb ends with "uir" due to certain exceptions
		else if(verb.substring(verb.length()-3).equals("uir")) {
			print(verb.substring(0, verb.length()-2) + "y", endings);
		} 
		
		//Checks if the verb ends with "uar" due to certain exceptions
		else if(verb.substring(verb.length()-3).equals("uar")) {
			print(verb.substring(0, verb.length()-3) + "ú", root(verb), endings);
		}
		
		//Checks of a verb ends with "iar" due to an exception
			else if(verb.substring(verb.length()-3).equals("iar")) {
				print(verb.substring(0, verb.length()-3) + "í", root(verb), endings);
			}
		
		else {
			
			//Uses the verb's gerund to handle certain stem changes
			String gerund = Progressive.gerund(verb);
			String withSmallChange = gerund.substring(0, gerund.length()-4);
			
			//Changes the amount removed from the verb bases on it's endings
			//Due to the difference in length between "ando" and "iendo"
			if(gerund.charAt(gerund.length()-4) != 'a') {
				withSmallChange = gerund.substring(0, gerund.length()-5);
			}	
			
			print(root(carGarZar(stemChange)), root(carGarZar(withSmallChange+ending(verb))), endings);
		}
	}
	
	//Prints verbs with a change matching the first person of the Present tense and handles various slight changes
	public void printYo(String beginning, String verb, String[] ends) {
		for(int i = 0; i < 6; i++) {
			System.out.println(beginning + verb.substring(0, verb.length()-1) + ends[i]);
		}
	}
	
	//Prints verbs in the Present Subjunctive tense while handling different stem changes in certain point-of-views
	public void print(String verb, String withSmallChange, String[] ends) {
		for(int i = 0; i < 6; i++) {
			if(i == 3 || i == 4) {
				System.out.println(Main.toBeReflexive[i] + withSmallChange + ends[i]);
			} else {
				System.out.println(Main.toBeReflexive[i] + verb + ends[i]);
			}
		}
	}
	
	static HashMap<String, String[]> Iregs = new HashMap<String, String[]>();
	
	//Fills a HashMap of irregular verbs in the Present Subjunctive tense
	public void fillMap() {
		Iregs.put("ser ", new String[]{"sea", "seas", "sea", "seamos", "seáis", "sean"});
		Iregs.put("estar" , new String[]{"esté", "estés", "esté", "estemos", "estéis", "estén"});
		Iregs.put("ir" , new String[]{"vaya", "vayas", "vaya", "vamos", "vayáis", "vayan"});
		Iregs.put("dar" , new String[]{"dé", "das", "dé", "demos", "deis", "den"});
		Iregs.put("saber" , new String[]{"sepa", "sepas", "sepa", "sepamos", "sepáis", "sepan"});
		Iregs.put("ir" , new String[]{"vaya", "vayas", "vaya", "vayamos", "vayáis", "vayan"});
		Iregs.put("haber" , new String[]{"haya", "hayas", "haya", "hayamos", "hayáis", "hayan"});
		Iregs.put("reir" , new String[]{"ría", "rías", "ría", "riamos", "riáis", "rían"});


	}
}
