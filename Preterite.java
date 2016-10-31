import java.util.HashMap;

public class Preterite extends Tense{
	private String stemChange;


	public Preterite(String[] endingsA, String[] endingsE, String[] endingsI) {
		super(endingsA, endingsE, endingsI);
		putValues();
	}
	
	//Conjugates verbs in the Preterite tense and handles irregular verbs 
	public void conjugate(String verb) {
		String[] endings = endingsAEI(verb);
		
		//Handles certain irregulars that do not follow the same rules are the other irregular verbs
		if(Iregs.containsKey(verb)) {
			print("", Iregs.get(verb));
		} 
		
		//Checks if a verb or any subsections of it are irregular
		else if(checkForIreg(verb, IregRoots) >= 0) {
			int i = checkForIreg(verb, IregRoots);
			
			//Checks if the irregular verb is decir or trear, which have a slightly different ending
			if(verb.substring(i).equals("decir") || (verb.substring(i).equals("traer"))) {
				print(verb.substring(0, i) + IregRoots.get(verb.substring(i)), Iregs.get("DecirTraer"));
			} 
			
			else {
			print(verb.substring(0, i) + IregRoots.get(verb.substring(i)), Iregs.get("Ireg"));
			}
		} 
		
		//Checks if the verb ends with "ucir" due to certain exceptions
		else if(verb.length() > 3 && verb.substring(verb.length()-4).equals("ucir")) {
			print(verb.substring(0, verb.length()-3) + "j", Iregs.get("Ireg"));
		} 
		
		//Checks for verbs that end in ir and are irregular for e to i and e to ie stem changes
		else if(end(verb) == 'i' && (contains(verb, eToI) || contains(verb, eToIe))) {
			for(int i = verb.length()-3; i >= 0; i--) {
				if(verb.charAt(i) == 'e') {
					stemChange = changeValue(verb, "i", i);
				}
			}
			print(verb, stemChange, root(verb), endings);
		} 
		
		//Checks for verbs that end in ir and are irregular for o to ue stem changes
		else if(end(verb) == 'i' && (contains(verb, oToUe))) {
			for(int i = verb.length()-3; i >= 0; i--) {
				if(verb.charAt(i) == 'o') {
					stemChange = changeValue(verb, "u", i);
				}
			}
			print(verb, stemChange, root(verb), endings);
		} 
		
		//Handles verbs with have a vowel as the third-to-last character (except u)
		else if("aeio".contains(verb.substring(verb.length()-3, verb.length()-2)) && end(verb) != 'a'){
			print(root(verb), Iregs.get("addY"));
		} 
		
		//Checks if the verb ends with "uir" due to certain exceptions
		else if("u".contains(verb.substring(verb.length()-3, verb.length()-2)) && end(verb) != 'a'){
					print(root(verb), Iregs.get("uir"));	
		} 
		
		else {
			print(verb, verb, root(carGarZar(verb)), endings);
		}
	}
	
	static HashMap<String, String> IregRoots = new HashMap<String, String>();
	static HashMap<String, String[]> Iregs = new HashMap<String, String[]>();

	
	public static void putValues() {
		IregRoots.put("poder", "pud");
		IregRoots.put("querer", "quis");
		IregRoots.put("poner", "pus");
		IregRoots.put("hacer", "hic");
		IregRoots.put("tener", "tuv");
		IregRoots.put("ander", "anduv");
		IregRoots.put("saber", "sup");
		IregRoots.put("venir", "vin");
		IregRoots.put("decir", "dij");
		IregRoots.put("traer", "traj");
		IregRoots.put("haber", "hub");
		IregRoots.put("caber", "cup");
		
		Iregs.put("Ireg", new String[] {"e", "iste", "o", "imos", "isteis", "ieron"});
		Iregs.put("ir", new String[] {"fui", "fuiste", "fue", "fuimos", "fuisteis", "fueron"});
		Iregs.put("ser", new String[] {"fui", "fuiste", "fue", "fuimos", "fuisteis", "fueron"});
		Iregs.put("dar", new String[] {"di", "diste", "dio", "dimos", "disteis", "dieron"});
		Iregs.put("ver", new String[] {"vi", "viste", "vio", "vimos", "visteis", "vieron"});
		Iregs.put("reir", new String[] {"reí", "reíste", "roí", "reímos", "reísties", "rieron"});
		Iregs.put("DecirTraer", new String[]{"e", "iste", "o", "imos", "isteis", "eron"});		
		Iregs.put("addY", new String[] {"í", "íste", "yó", "ímos", "ísteis", "yeron"});
		Iregs.put("uir", new String[] {"í", "iste", "yó", "ímos", "isteis", "yeron"});	

	}
	
	//Print method that handles verbs with changes in the first person and slight stem changes in the third person
	public void print(String verb, String withChange, String yoChange, String[] ends) {
		for(int i = 0; i < 6; i++) {
			if(i == 0) {
				System.out.println(Main.toBeReflexive[i] + yoChange + ends[i]);
			} else if(i == 2 || i == 5) {
				System.out.println(Main.toBeReflexive[i] + root(withChange) + ends[i]);
			} else {
				System.out.println(Main.toBeReflexive[i] + root(verb) + ends[i]);
			}
		}
	}

}
