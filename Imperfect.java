import java.util.HashMap;

public class Imperfect extends Tense{
	
	public Imperfect(String[] endingsA, String[] endingsE, String[] endingsI) {
		super(endingsA, endingsE, endingsI);
		putValues();
	}
		
	//Conjugates verbs in the Imperfect tense and handles irregular verbs
	public void conjugate(String verb) {
		String[] endings = endingsAEI(verb);
		
		//Checks if the verb is one of the three irregulars
		if(IregRoots.containsKey(verb)) {
			print("", IregRoots.get(verb));
		} 
		
		else {
			print(root(verb), endings);
		}
	}
	
	
	HashMap<String, String[]> IregRoots = new HashMap<String, String[]>();

	//Fills a HashMap of irregular verbs in the Imperfect Tense
	public void putValues() {
		IregRoots.put("ver", new String[] {"veía", "veías", "veía", "veíamos", "veíais", "veían"});
		IregRoots.put("ser", new String[] {"era", "eras", "era", "éramos", "erais", "eran"});
		IregRoots.put("ir", new String[] {"iba", "ibas", "iba", "íbamos", "ibais", "iban"});
	}
}
