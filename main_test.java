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

        LanguageModel lm = new LanguageModel(2,0);

        lm.train("shake.txt");

        for (String str : lm.CharDataMap.keySet()) {

            System.out.println(str);
            System.out.println(lm.CharDataMap.get(str));
        }



    }

}
