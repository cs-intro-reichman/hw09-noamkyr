import java.util.HashMap;
import java.util.Random;

public class LanguageModel {

    // The map of this model.
    // Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    
    // The window length used in this model.
    int windowLength;
    
    // The random number generator used by this model. 
	private Random randomGenerator;

    /** Constructs a language model with the given window length and a given
     *  seed value. Generating texts from this model multiple times with the 
     *  same seed value will produce the same random texts. Good for debugging. */
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }

    /** Constructs a language model with the given window length.
     * Generating texts from this model multiple times will produce
     * different random texts. Good for production. */
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }

    /** Builds a language model from the text in the given file (the corpus). */
	public void train(String fileName) {
        String window = "";
        char c;
        In in = new In(fileName);

        for (int i = 0; i < windowLength; i++) {
            window += in.readChar();
        }

        while (!in.isEmpty()){

            if (window.equals("yo")){
                int x = 3;
            }

            c = in.readChar();
            List l = CharDataMap.get(window);

            if (l == null){
                CharDataMap.put(window, new List());
            }

            l = CharDataMap.get(window);
            l.update(c);
            window = window.substring(1) + c;


        }

        for (List probs : CharDataMap.values()){
            calculateProbabilities(probs);
        }




    }

    // Computes and sets the probabilities (p and cp fields) of all the
	// characters in the given list. */
	public void calculateProbabilities(List probs) {				
		int sum = 0;
        for (int i = 0; i < probs.getSize(); i++) {
            CharData c = probs.get(i);
            sum += c.count;
        }

        double b4_sum = 0;
        for (int i = 0; i < probs.getSize(); i++) {
            CharData c = probs.get(i);
            c.p = (double)c.count / sum;
            c.cp = b4_sum + c.p;
            b4_sum += c.p;
        }

	}

    // Returns a random character from the given probabilities list.
	public char getRandomChar(List probs) {
		double r = randomGenerator.nextDouble();
        for (int i = 0; i < probs.getSize(); i++) {
            CharData c = probs.get(i);
            if (c.cp > r){
                return c.chr;
            }
        }

        return '^';
	}

    /**
	 * Generates a random text, based on the probabilities that were learned during training. 
	 * @param initialText - text to start with. If initialText's last substring of size numberOfLetters
	 * doesn't appear as a key in Map, we generate no text and return only the initial text. 
	 * @param numberOfLetters - the size of text to generate
	 * @return the generated text
	 */
	public String generate(String initialText, int textLength) {
        String result = initialText;

        if(initialText.length() < windowLength) {
            return initialText;
        }

        String window = initialText.substring(Math.max(0, initialText.length()-windowLength));
        if (CharDataMap.get(window) == null){
            return initialText;
        }
        while (result.length() <= textLength){
            List l = CharDataMap.get(window);
            char c = getRandomChar(l);
            result += c;
            window = window.substring(1) + c;
        }

        return result;
	}

    /** Returns a string representing the map of this language model. */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : CharDataMap.keySet()) {
			List keyProbs = CharDataMap.get(key);
			str.append(key + " : " + keyProbs + "\n");
		}
		return str.toString();
	}

    public static void main(String[] args) {
		// Your code goes here
    }
}
