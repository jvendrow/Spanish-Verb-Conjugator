import java.util.HashMap;

public class Present extends Tense{
	String stemChange = "";


	public Present(String[] endingsA, String[] endingsE, String[] endingsI) {
		super(endingsA, endingsE, endingsI);
		putValuesGo();
	}
	
	//Conjugates verbs in the Present tense and handles irregular verbs
	public void conjugate(String verb) {
		stemChange = stemChange(verb);
		String[] endings = endingsAEI(verb);
		//Checks of a verb is completely irregular
		if (Iregs.containsKey(verb)) {
				print("", Iregs.get(verb));
		} 
		
		//Checks of a verb or any subsections of it have a change in the First Person
		else if(checkForIreg(verb, yoChange) >= 0) {
			int i = checkForIreg(verb, yoChange);
			printYo(verb.substring(0, i), verb.substring(i), stemChange, endings);
		}
		
		//Checks of a verb ends with "cer" due to an exception
		else if(verb.substring(verb.length()-3).equals("cer") || verb.substring(verb.length()-3).equals("cir")) {
			print(verb.substring(0, verb.length()-3), new String[]{"zco", "ce", "ces", "cemos", "céis", "cen"});
		} 
		
		//Checks of a verb ends with "uir" due to an exception
		else if(verb.substring(verb.length()-3).equals("uir")) {
			print(verb.substring(0, verb.length()-2) + "y", root(verb), endings);
		} 
		
		//Checks of a verb ends with "uir" due to an exception
		else if(verb.substring(verb.length()-3).equals("uar")) {
			print(verb.substring(0, verb.length()-3) + "ú", root(verb), endings);
		}
		
		//Checks of a verb ends with "iar" due to an exception
		else if(verb.substring(verb.length()-3).equals("iar")) {
			print(verb.substring(0, verb.length()-3) + "í", root(verb), endings);
		}
		
		else {
			print(root(stemChange), root(verb), endings);
		}
	}
	
	HashMap<String, String> yoChange = new HashMap<String, String>();
	HashMap<String, String[]> Iregs = new HashMap<String, String[]>();

	
	public void putValuesGo() {
		yoChange.put("hacer", "hago");
		yoChange.put("decir", "digo");
		yoChange.put("traer", "traigo");
		yoChange.put("salir", "salgo");
		yoChange.put("tener", "tengo");
		yoChange.put("caer", "caigo");
		yoChange.put("valer", "valgo");
		yoChange.put("venir", "vengo");
		yoChange.put("saber", "sé");
		yoChange.put("poner", "pongo");
		yoChange.put("dar", "doy");
		yoChange.put("caber", "caigo");
		yoChange.put("oir", "oigo");
		yoChange.put("gir", "jo");
		
		Iregs.put("haber", new String[] {"he", "has", "ha", "hemos", "habéis", "han"});
		Iregs.put("estar", new String[] {"estoy", "estás", "está", "estamos", "estáis", "estan"});
		Iregs.put("ser", new String[] {"soy", "eres", "es", "somos", "sois", "son"});
		Iregs.put("ir", new String[] {"voy", "vas", "va", "vamos", "vías", "van"});
		Iregs.put("oir", new String[] {"oigo", "oyes", "oye", "oímos", "oís", "oyen"});
		Iregs.put("reir", new String[] {"rió", "ríes", "ríe", "reímos", "reís", "ríen"});

	}
	
	//Print method that handles stem changing verbs
	public void print(String root, String stemChange, String[] ends) {
		for(int i = 0; i < 6; i++) {
			if(i == 3 || i == 4) {
				System.out.println(Main.toBeReflexive[i] + stemChange + ends[i]);
			} else {
				System.out.println(Main.toBeReflexive[i] + root + ends[i]);
			}
		}
	}
	
	//Print method that handles both irregular verbs in the first person and stem changes
	public void printYo(String beginning, String verb, String withChange, String[] ends) {
		for(int i = 0; i < 6; i++) {
			if(i == 0) {
				System.out.println(Main.toBeReflexive[i] + beginning + yoChange.get(verb));
			} else if(i == 3 || i == 4) {
				System.out.println(Main.toBeReflexive[i] + beginning + root(verb) + ends[i]);
			} else {
				System.out.println(Main.toBeReflexive[i] + root(withChange) + ends[i]);
			}
		}
	}
	
}
