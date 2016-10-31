import java.util.HashMap;

public class Perfect{
	String type;
	String[] present = {"he", "has", "ha", "hemos", "habéis",  "han"};
	String[] imperfect = {"había", "habías", "había", "habíamos", "habíais", "habían"};
	String[] future = {"habré", "habrás", "habrá", "habremos", "habréis", "habrán"};
	String[] conditional = {"habría", "habrías", "habría", "habríamos", "habríais", "habrían"};
	String[] subjunctive = {"haya", "hayas", "haya", "hayamos", "hayáis", "hayan"};
	String[] pastSubjunctive = {"hubiera", "hubieras", "hubiera", "hubiéramos", "hubierais", "hubieran"};	
	public Perfect(String type) {
		this.type = type;
		fillParticiples();
	}
	
	//Conjugates verbs in the Perfect tenses and handles irregular verbs
	public void conjugate(String verb) {
		//Identifies the specific tense of the verb based on a parameter type
		if(type.equals("Present")) {
			print(present, verb);
		} 
		else if(type.equals("Imperfect")) {
			print(imperfect, verb);
		} 
		else if(type.equals("Future")) {
			print(future, verb);
		} 
		else if(type.equals("Subjunctive")){
			print(subjunctive, verb);
		} 
		else{
			print(pastSubjunctive, verb);
		}
	}
	
	//Prints verbs in the Perfect tenses
	public void print(String[] haber, String verb) {
		for(int i = 0; i < 6; i++) {
			System.out.println(Main.toBeReflexive[i] + haber[i] + " " + pastParticiple(verb));
		}
	}
	
	//Finds the verb's past participle
	public String pastParticiple(String verb) {
		if(Tense.checkForIreg(verb, participleIregs) >= 0) {
			int i = Tense.checkForIreg(verb, participleIregs);
			return verb.substring(0, i) + participleIregs.get(verb.substring(i));
		}else if(verb.charAt(verb.length()-2) == 'i' || verb.charAt(verb.length()-2) == 'e'|| verb.charAt(verb.length()-2) == 'í') {
        	if (verb.length() > 2) {
	            char i = verb.charAt(verb.length() - 3);
	            if(i == 'a' || i == 'e' || i == 'o' || Tense.end(verb) == 'í') {
	                return verb.substring(0, verb.length() - 2) + "ído";
	            } else {
	            	return verb.substring(0, verb.length() - 2) + "ido";
	            }
        	}else {
                return verb.substring(0, verb.length() - 2) + "ido";
            }
        } else {
            return verb.substring(0, verb.length() - 2) + "ado";
        }
    }
	
public HashMap<String, String> participleIregs = new HashMap<String, String>();
	
	//Fills the HashMap of irregular past participles
	public void fillParticiples() {
		participleIregs.put("abrir", "abierto");
		participleIregs.put("cubrir", "cubierto");
		participleIregs.put("decir", "dicho");
		participleIregs.put("escribir", "escrito");
		participleIregs.put("freír", "frito");
		participleIregs.put("hacer", "hecho");
		participleIregs.put("morir", "muerto");
		participleIregs.put("poner", "puesto");
		participleIregs.put("resolver", "resuelto");
		participleIregs.put("romper", "roto");
		participleIregs.put("ver", "visto");
		participleIregs.put("volver", "vuelto");
		participleIregs.put("solver", "suelto");
	}
}
