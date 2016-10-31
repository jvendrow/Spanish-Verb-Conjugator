import java.util.Arrays;
import java.util.HashMap;

public class Commands extends PresentSubjunctive{
	String type;
	public Commands(String[] endingsA, String[] endingsE, String[] endingsI, String type) {
		super(endingsA, endingsE, endingsI);
		this.type = type;
		fillMapCommands();
	}
	
	//Conjugates verbs in the Command tenses and handles irregular verbs
	public void conjugate(String verb) {
		stemChange = stemChange(verb);
		String[] endings = endingsAEI(verb);
		
		//Checks of the verb is positive, reflexive, and irregular to handle reflexive irregulars
		if(type.equals("Positive") && posIregsReflexive.containsKey(verb) && !Main.toBeReflexive[0].equals("")) {
			printIregs("", posIregsReflexive.get(verb), false);
		} 
		
		////Checks of the verb is irregular and the Commands should be Positive
		else if(type.equals("Positive") && posIregs.containsKey(verb)) {
			printIregs("", posIregs.get(verb), true);
		} 
		
		//Checks of the verb is irregular and the Commands should be Negative
		else if(type.equals("Negative") && PresentSubjunctive.Iregs.containsKey(verb)) {
			printIregs("No ", Arrays.copyOfRange(PresentSubjunctive.Iregs.get(verb), 1, 6), true);
		}
		
		//Checks of a verb or any subsections of it have change matching that of the first person in the Present Tense
		else if(checkForIreg(verb, yoChange) >= 0) {
			int i = checkForIreg(verb, yoChange);
			printYo(verb.substring(0, i), verb.substring(i), yoChange.get(verb.substring(i)), endings);
		} 
		
		//Checks if the verb ends with "cer" or "cir" due to certain exceptions
		else if(verb.substring(verb.length()-3).equals("cer") || verb.substring(verb.length()-3).equals("cir")) {
			print(verb.substring(0, verb.length()-3) + "zc", verb.substring(0, verb.length()-3) + "zc", verb, verb.substring(0, verb.length()-3) + "zc", endings);
		} 
		//Checks if the verb ends with "uir" due to certain exceptions
		else if(verb.substring(verb.length()-3).equals("uir")) {
			print(verb.substring(0, verb.length()-2) + "y", verb, verb, verb.substring(0, verb.length()-2) + "y", endings);
		} 
		
		//Checks if the verb ends with "uar" due to certain exceptions
		else if(verb.substring(verb.length()-3).equals("uar")) {
			print(verb.substring(0, verb.length()-3) + "ú", verb, verb, root(verb), endings);
		}
		
		//Checks if the verb ends with "iar" due to certain exceptions
				else if(verb.substring(verb.length()-3).equals("iar")) {
					print(verb.substring(0, verb.length()-3) + "í", verb, verb, root(verb), endings);
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
			
			print(root(carGarZar(stemChange)), root(carGarZar(verb)), verb, root(carGarZar(withSmallChange+ending(verb))), endings);
		}
	}
	
	//Separates between positive and negative commands
	public void print(String stemChange, String verb, String originalVerb, String gerund, String[] ends) {
		if(type.equals("Positive")) {
			printPositive(stemChange, verb, originalVerb, gerund, ends);
		} else {
			printNegative(stemChange, verb, gerund, ends);
		}
	}
	//Separates between positive and negative commands for words with a change matching that of the Present tense first person
	public void printYo(String beginning, String verb, String stemChange, String[] ends) {
		if(type.equals("Positive")) {
			printYoPositive(beginning, verb, stemChange, ends);
		} else {
			printYoNegative(beginning, stemChange, ends);
		}
	}
	
	//Prints positive commands with reflexive pronouns at the end of the verb and handles various slight changes
	public void printPositive(String stemChange, String verb, String originalVerb, String withSmallChange, String[] ends) {

		for(int i = 1; i < 6; i++) {
			if(i == 1) {
				//Checks if the ending of the verb is uir due to an exception
				
				if(originalVerb.substring(originalVerb.length()-3).equals("uir")) {
					System.out.println(Main.toBeReflexive[i] + root(stemChange(originalVerb)) + "y" + Main.presentEr[2]);
				} 
				//Checks if the ending of the verb is uar due to an exception
				else if(originalVerb.substring(originalVerb.length()-3).equals("uar")) {
					System.out.println(Main.toBeReflexive[i] + stemChange(originalVerb).substring(0, originalVerb.length()-3) + "ú" + Main.presentAr[2]);
				}
				//Checks if the ending of the verb is iar due to an exception
				else if(originalVerb.substring(originalVerb.length()-3).equals("iar")) {
					System.out.println(Main.toBeReflexive[i] + stemChange(originalVerb).substring(0, originalVerb.length()-3) + "í" + Main.presentAr[2]);
				}
				else if(end(originalVerb) == 'a') {
					System.out.println(endReflexive(root(stemChange(originalVerb))) + Main.presentAr[2] + Main.toBeReflexive[i]);
				} 
				else {
					System.out.println(endReflexive(root(stemChange(originalVerb))) + Main.presentEr[2] + Main.toBeReflexive[i]);
				}
			} 
			else if(i == 3) {
				//Handles exception for reflexive verbs for nosotros (we)  
				if(!Main.toBeReflexive[0].equals("")) {
					if(ends[0].equals("e")) {
						System.out.println(withSmallChange + "émonos");
					} 
					else {
						System.out.println(withSmallChange + "ámonos");
					}
				}
				else {
					System.out.println(withSmallChange + ends[i]);
				}
			} 
			else if(i == 4) {
				if(!Main.toBeReflexive[0].equals("")) {
					System.out.println(Main.original.substring(0, Main.original.length()-2) + "íos");
				} 
				else {
					System.out.println(Main.toBeReflexive[i] + endReflexive(root(originalVerb)) + end(originalVerb) + "d");
				}
			} 
			else {
				System.out.println(endReflexive(stemChange) + ends[i] + Main.toBeReflexive[i]);
			}
		}
	}
	
	//Prints negative commands and handles various slight changes
	public void printNegative(String stemChange, String verb, String withSmallChange, String[] ends) {
		for(int i = 0; i < 6; i++) {
			if(i == 0) {
			} else if(i == 1) {
				System.out.println("No " + Main.toBeReflexive[i] + stemChange + ends[1]);
			} else if(i == 3 || i == 4) {
				System.out.println("No " + Main.toBeReflexive[i] + withSmallChange + ends[i]);
			} else {
				System.out.println("No " + Main.toBeReflexive[i] + stemChange + ends[i]);
			}
		}
	}
	
	//Prints positive commands with a change matching the first person of the Present tense and handles various slight changes
	public void printYoPositive(String beginning, String verb, String stemChange, String[] ends) {
		for(int i = 1; i < 6; i++) {
			if(i == 1) {
				//Checks of the verb is irregular in the first person
				if(yoIreg.containsKey(verb)) {
					System.out.println(endReflexive(beginning) + yoIreg.get(verb) + Main.toBeReflexive[i]);
				} 
				else {
					if(end(verb) == 'a') {
						System.out.println(endReflexive(beginning + root(stemChange(verb))) + Main.presentAr[2] + Main.toBeReflexive[i]);
					} else {
						System.out.println(endReflexive(beginning + root(stemChange(verb))) + Main.presentEr[2] + Main.toBeReflexive[i]);
					}
				}
			} 
			
			//Handles exception for reflexive verbs for nosotros (we)  
			else if(i == 3 && !Main.toBeReflexive[0].equals("")) {
				if(ends[0].equals("e")) {
					System.out.println(beginning + stemChange.substring(0, stemChange.length()-1) + "émonos");
				}
				else {
					System.out.println(beginning + stemChange.substring(0, stemChange.length()-1) + "ámonos");
				}
			}
			else if(i == 4) {
				if(!Main.toBeReflexive[0].equals("")) {
					System.out.println(Main.original.substring(0, Main.original.length()-2) + "íos");
				}
				else {
					System.out.println(Main.toBeReflexive[i] + Main.original.substring(0, Main.original.length()-1) + "d");
				}
			} else {
				System.out.println(endReflexive(beginning + stemChange.substring(0, stemChange.length()-1)) + ends[i] + Main.toBeReflexive[i]);
			}
		}
	}
	
	//Prints negative commands with a change matching the first person of the Present tense and handles various slight changes
	public void printYoNegative(String beginning, String stemChange, String[] ends) {
		for(int i = 1; i < 6; i++) {
			if(i == 1) {
				System.out.println("No " + Main.toBeReflexive[i] + beginning + stemChange.substring(0, stemChange.length()-1) + ends[i]);
			} 
			else {
				System.out.println("No " + Main.toBeReflexive[i] + beginning + stemChange.substring(0, stemChange.length()-1) + ends[i]);
			}
		}
	}
	
	//Prints irregular verbs
	public void printIregs(String No, String[] verbs, boolean putReflexive) {
		for(int i = 1; i < 6; i++) {
			if(putReflexive) {
				System.out.println(No + Main.toBeReflexive[i] + verbs[i-1]);
			}
			else {
			System.out.println(verbs[i-1]);
			}
		}
	}
	
	public HashMap<String, String[]> posIregs = new HashMap<String, String[]>();
	public HashMap<String, String[]> posIregsReflexive = new HashMap<String, String[]>();

	public HashMap<String, String> yoIreg = new HashMap<String, String>();
		
	
	//Fills the HashMaps for irregularities in the Commands
	public void fillMapCommands() {
		posIregs.put("ser", new String[]{"sé", "sea", "seamos", "sed", "sean"});
		posIregs.put("estar", new String[]{"está", "esté", "estemos", "estad", "estén"});
		posIregs.put("ir", new String[]{"ve", "vaya", "vamos", "id", "vayan"});
		posIregs.put("dar", new String[]{"da", "dé", "demos", "dad", "den"});
		posIregs.put("saber", new String[]{"sabe", "sepa", "sepamos", "sabed", "sepan"});
		posIregs.put("haber" , new String[]{"has", "haya", "hayamos", "habed", "hayan"});
		posIregs.put("reir" , new String[]{"ríe", "ría", "riamos", "reíd", "rían"});
		
		posIregsReflexive.put("dar", new String[]{"date", "dése", "démonos", "daos", "dense"});
		posIregsReflexive.put("ir" , new String[]{"vete", "váyase", "vayámonos", "idos", "váyanse"});
		posIregsReflexive.put("reir" , new String[]{"ríete", "ríase", "riámonos", "reíos", "ríanse"});
		


		
		yoIreg.put("tener", "ten");
		yoIreg.put("venir", "ven");
		yoIreg.put("poner", "pon");
		yoIreg.put("decir", "di");
		yoIreg.put("salir", "sal");
		yoIreg.put("hacer", "haz");
		
		//Not an actual yo irregular, but has the same effect
		yoIreg.put("oir", "oye");
	}
}
