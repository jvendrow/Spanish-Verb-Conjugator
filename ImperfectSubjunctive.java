
public class ImperfectSubjunctive extends Tense{
		
	private String[] IrSer = {"fuera", "fueras", "fuera", "fuéramos", "fuerais", "fueran"};
	private String[] era = {"era", "eras", "era", "éramos", "erais", "eran"};
	public ImperfectSubjunctive(String[] endingsA, String[] endingsE, String[] endingsI) {
		super(endingsA, endingsE, endingsI);
		Preterite.putValues();
	}
	
	//Conjugates verbs in the Imperfect Subjunctive tense and handles irregular verbs
	public void conjugate(String verb) {
		
		String[] endings = endingsAEI(verb);
		
		//Checks for ir and ser, the most notable irregulars
		if(verb.equals("ir") || verb.equals("ser")) {
			print("", IrSer);
		
		}
		//Handles dar, which uses a different ending due to its irregularity in the Preterite
		else if(verb.equals("dar")) {
			print(root(verb), endingsE);
		}
		
		else if(verb.equals("reir")) {
			print("r", endingsE);
		}			
		
		//Checks if a verb or any subsections of it are irregular 
		else if(checkForIreg(verb, Preterite.IregRoots) >= 0) {
			int i = checkForIreg(verb, Preterite.IregRoots);
			
			//Checks if the verb is decir or trear, which have a slightly different ending
			if(verb.substring(i).equalsIgnoreCase("decir") || verb.substring(i).equalsIgnoreCase("decir")) {
				print(verb.substring(0, i) + Preterite.IregRoots.get(verb.substring(i)), era);
			}
			
			print(verb.substring(0, i) + Preterite.IregRoots.get(verb.substring(i)), endings);
		} 
		
		//Checks for verbs that end in ir and are irregular for e to i or e to ie stem changes
		else if(end(verb) == 'i' && (contains(verb, eToI) || contains(verb, eToIe))) {
			for(int i = verb.length()-3; i >= 0; i--) {
				if(verb.charAt(i) == 'e') {
					print(root(changeValue(verb, "i", i)), endings);
				}
			} 
		} 
		
		//Checks for verbs that end in ir and are irregular for o to ue stem changes
		else if(end(verb) == 'i' && (contains(verb, oToUe))) {
			for(int i = verb.length()-3; i >= 0; i--) {
				if(verb.charAt(i) == 'o') {
					print(root(changeValue(verb, "u", i)), endings);
				}
			}
		} 
		
		//Handles verbs with have a vowel as the third-to-last character (except u)
		else if("aeio".contains(verb.substring(verb.length()-3, verb.length()-2)) && end(verb) != 'a'){
			print(root(verb) + "y", era);
		}
		
		//Checks if the verb ends with "uir" due to certain exceptions
		else if(verb.substring(verb.length()-3, verb.length()-1).equals("ui")) {
			print(verb.substring(0, verb.length()-2) + "y", era);
		} 
		
		//Checks if the verb ends with "ucir" due to certain exceptions
		else if(verb.length() > 3 && verb.substring(verb.length()-4).equals("ucir")) {
			print(verb.substring(0, verb.length()-3) + "j", era);
		} 
		
		else {
			print(root(verb), endings);
		}
	}
}
