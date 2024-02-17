public class main_test {

    public static void main(String[] args) {
        List l  = new List();
        String s = "committee_";

        for (int i = 0; i < s.length(); i++) {
            l.update(s.charAt(i));
        }

        System.out.println(l.toString());
        //l.remove('i');
        System.out.println(l.toString());

        LanguageModel lm = new LanguageModel(0,0);

        lm.calculateProbabilities(l);
        System.out.println(l.toString());
        System.out.println(lm.getRandomChar(l));
    }

}
