import java.util.Scanner;

public class Main {
	
	//Contains the original verb used only in special cases
	static String original;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter verb:");
		String verb = scanner.nextLine();
		verb = verb.toLowerCase();
		scanner.close();
		//Checks for reflexive verbs
		if(verb.substring(verb.length()-2).equals("se")) {
			isReflexive = true;
			toBeReflexive = reflexive;
			verb = Tense.root(verb);
		} 
		else {
			isReflexive = false;
		}
		original = verb;
		
		if(Tense.end(verb) == 'í') {
			verb = Tense.root(verb) + "ir";
		}
		
		//Prints out the conjugations of all the tenses of a verb
		
		System.out.println("Present:");
		Present Present = new Present(presentAr, presentEr, presentIr);
		Present.conjugate(verb);
		System.out.println();
		
		System.out.println("Preterite:");
		Preterite Preterite = new Preterite(preteriteAr, preteriteEr, preteriteIr);
		Preterite.conjugate(verb);
		System.out.println();
		
		System.out.println("Imperfect:");
		Imperfect Imperfect = new Imperfect(imperfectAr, imperfectErIr, imperfectErIr);
		Imperfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Future:");
		Future Future = new Future(future);
		Future.conjugate(verb);
		System.out.println();
		
		System.out.println("Conditional:");
		Conditional Conditional = new Conditional(conditional);
		Conditional.conjugate(verb);
		System.out.println();
		
		System.out.println("Present Perfect:");
		Perfect presentPerfect = new Perfect("Present");
		presentPerfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Pluscuan Perfect:");
		Perfect imperfectPerfect = new Perfect("Imperfect");
		imperfectPerfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Future Perfect:");
		Perfect futurePerfect = new Perfect("Future");
		futurePerfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Conditional Perfect:");
		Perfect conditionalPerfect = new Perfect("Conditional");
		conditionalPerfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Subjunctive Perfect:");
		Perfect SubjunctivePerfect = new Perfect("Subjunctive");
		SubjunctivePerfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Imperfect Subjunctive Perfect:");
		Perfect ImperfectSubjunctivePerfect = new Perfect("ImperfectSubjunctive");
		ImperfectSubjunctivePerfect.conjugate(verb);
		System.out.println();
		
		System.out.println("Present Progressive:");
		Progressive presentProgressive = new Progressive("Present");
		presentProgressive.conjugate(verb);
		System.out.println();
		
		System.out.println("Past Progressive:");
		Progressive pastProgressive = new Progressive("Past");
		pastProgressive.conjugate(verb);
		System.out.println();
		
		System.out.println("Present Subjunctive:");
		PresentSubjunctive PresentSubjunctive = new PresentSubjunctive(subjunctiveAr, subjunctiveErIr, subjunctiveErIr);
		PresentSubjunctive.conjugate(verb);
		System.out.println();
		
		System.out.println("Imperfect Subjunctive:");
		ImperfectSubjunctive ImperfectSubjunctive = new ImperfectSubjunctive(imperfectSubjunctiveAr, imperfectSubjunctiveErIr, imperfectSubjunctiveErIr);
		ImperfectSubjunctive.conjugate(verb);
		System.out.println();
		
		System.out.println("Positive Commands:");
		Commands CommandsPositive = new Commands(subjunctiveAr, subjunctiveErIr, subjunctiveErIr, "Positive");
		CommandsPositive.conjugate(verb);
		System.out.println();
		
		System.out.println("Negative Commands:");
		Commands CommandsNegative = new Commands(subjunctiveAr, subjunctiveErIr, subjunctiveErIr, "Negative");
		CommandsNegative.conjugate(verb);
		System.out.println();
	}
	
	
	//Spanish verb endings
	
	static boolean isReflexive;
	static String[] pronoun = {"yo", "tú", "él/ella/ud.", "nosotros", "vosotros", "ellos/ellas/uds."};
	static String[] reflexive = {"me ", "te ", "se ", "nos ", "os ", "se "};
	static String[] toBeReflexive = {"", "", "", "", "", ""};
	
	static String[] presentAr = {"o", "as", "a", "amos", "áis", "an"};
	static String[] presentEr = {"o", "es", "e", "emos", "éis", "en"};
	static String[] presentIr = {"o", "es", "e", "imos", "ís", "en"};
	
	static String[] preteriteAr = {"é", "aste", "ó", "amos", "asteis", "aron"};
	static String[] preteriteEr = {"í", "iste", "ió", "imos", "isteis", "ieron"};
	static String[] preteriteIr = {"í", "iste", "ió", "imos", "isteis", "ieron"};
	
	static String[] imperfectAr = {"aba", "abas", "aba", "ábamos", "abais", "aban"};
	static String[] imperfectErIr = {"ía", "ías", "ía", "íamos", "íais", "ían"};
	
	static String[] future = {"é", "ás", "á", "emos", "éis", "án"};
	
	static String[] conditional = {"ía", "ías", "ía", "íamos", "íais", "ían"};
	
	static String[] subjunctiveAr = {"e", "es", "e", "emos", "éis", "en"};
	static String[] subjunctiveErIr = {"a", "as", "a", "amos", "áis", "an"};
	
	static String[] imperfectSubjunctiveAr = {"ara", "aras", "ara", "áramos", "arais", "aran"};
	static String[] imperfectSubjunctiveErIr = {"iera", "ieras", "iera", "iéramos", "ierais", "ieran"};
	
}
	