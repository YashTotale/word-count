public class WordCountTester {
    public static void main(String[] args) {
        new WordCountTester().start();
    }

    private void start() {
        testWordCountClass();
        testWordCountListClass();
    }

    private void testWordCountClass() {
        try {
            System.out.println("\n-----\nTesting WordCount\n");

            String word1 = "hello";
            String word2 = "hi";

            int count2 = 2;

            WordCount wc1 = new WordCount(word1);
            WordCount wc2 = new WordCount(word2, count2);

            int increment1 = 3;

            wc1.increment(increment1);
            wc2.increment();

            int expectedCount1 = 1 + increment1; // <- should be 4
            int expectedCount2 = count2 + 1; // <- should be 3

            //Increment + getCount tester
            if (wc1.getCount() != expectedCount1 || wc2.getCount() != expectedCount2) {
                throw new Exception("Increment or getCount doesn't work");
            }
            System.out.println("Increment and getCount work");

            //getWord tester
            if (!wc1.getWord().equals(word1)) {
                throw new Exception("getWord doesn't work");
            }
            System.out.println("getWord works");

            //toString tester
            if (!wc1.toString().equals(word1 + " - " + expectedCount1)) {
                throw new Exception("toString doesn't work");
            }
            System.out.println("toString works");

            //Compare Alpha tester
            if (wc1.compareAlpha(wc1) != 0) {
                throw new Exception("Comparing the same Word Count alphabetically doesn't work");
            }
            if (wc1.compareAlpha(wc2) > 0) {
                throw new Exception("Comparing different Word Counts alphabetically doesn't work");
            }
            System.out.println("compareAlpha works");

            //Compare Frequency tester
            if (wc1.compareFrequency(wc1) != 0) {
                throw new Exception("Comparing the same Word Count's frequency doesn't work");
            }
            if (wc1.compareFrequency(wc2) < 0) {
                throw new Exception("Comparing different Word Counts' frequency doesn't work");
            }
            System.out.println("compareFrequency works");

            //Compare Length tester
            if (wc1.compareLength(wc1) != 0) {
                throw new Exception("Comparing the same Word Count's length doesn't work");
            }
            if (wc1.compareLength(wc2) < 0) {
                throw new Exception("Comparing different Word Counts' length doesn't work");
            }
            System.out.println("compareLength works");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to help you test your WordCountList class which maintains an
     * ArrayList of WordCount objects. It needs to be able to satisfy the methods
     * below. When it is asked to add, it needs to maintain alpha order.
     */
    private void testWordCountListClass() {
        try {
            System.out.println("\n-----\nTesting WordCountList\n");
            WordCountList wcl = new WordCountList();

            //Add + toString tester
            wcl.add("hola");

            if (!wcl.toString().equals("[hola - 1]")) {
                throw new Exception("Add doesn't work for new string addition");
            }

            wcl.add(new WordCount("bonjour"));

            if (!wcl.toString().equals("[bonjour - 1, hola - 1]")) {
                throw new Exception("Add doesn't work for new WordCount w/out count addition");
            }

            wcl.add(new WordCount("ni hao", 3));

            if (!wcl.toString().equals("[bonjour - 1, hola - 1, ni hao - 3]")) {
                throw new Exception("Add doesn't work for new WordCount w/ count addition");
            }

            wcl.add(new WordCount("hola"));

            if (!wcl.toString().equals("[bonjour - 1, hola - 2, ni hao - 3]")) {
                throw new Exception("Add doesn't work for increment WordCount addition");
            }

            wcl.add("bonjour");

            if (!wcl.toString().equals("[bonjour - 2, hola - 2, ni hao - 3]")) {
                throw new Exception("Add doesn't work for increment string addition");
            }
            System.out.println("add works");

            //Map tester
            WordCountList newList = wcl.map((w, i) -> {
                w.increment();
                return w;
            });

            if(!newList.toString().equals("[bonjour - 3, hola - 3, ni hao - 4]")) {
                throw new Exception("Map doesn't work");
            }
            System.out.println("map works");

            //Sort Frequency tester

            WordCountList ascendingFreq = wcl.sortFrequency(true);

            if(!ascendingFreq.toString().equals("[bonjour - 2, hola - 2, ni hao - 3]")) {
                throw new Exception("sortFrequency ascending doesn't work");
            }

            WordCountList descendingFreq = wcl.sortFrequency(false);

            if(!descendingFreq.toString().equals("[ni hao - 3, bonjour - 2, hola - 2]")) {
                throw new Exception("sortFrequency descending doesn't work");
            }
            System.out.println("sortFrequency works");

            //Sort Length tester
            WordCountList ascendingLen = wcl.sortLength(true);

            if(!ascendingLen.toString().equals("[hola - 2, ni hao - 3, bonjour - 2]")) {
                throw new Exception("sortLength ascending doesn't work");
            }

            WordCountList descendingLen = wcl.sortLength(false);

            if(!descendingLen.toString().equals("[bonjour - 2, ni hao - 3, hola - 2]")) {
                throw new Exception("sortLength descending doesn't work");
            }
            System.out.println("sortLength works");

            //IndexOf tester
            if (wcl.indexOf("bonjour") != 0) {
                throw new Exception("indexOf w/ strings doesn't work");
            }

            if (wcl.indexOf(new WordCount("hola")) != 1) {
                throw new Exception("indexOf w/ WordCount doesn't work");
            }
            System.out.println("indexOf works");

            //Get tester
            if(!wcl.get(0).toString().equals("bonjour - 2")) {
                throw new Exception("get w/ index doesn't work");
            }
            if(!wcl.get("bonjour").toString().equals("bonjour - 2")) {
                throw new Exception("get w/ string doesn't work");
            }
            System.out.println("get works");

            //Contains tester
            if(!wcl.contains("hola")) {
                throw new Exception("contains w/ string doesn't work");
            }

            if(!wcl.contains(new WordCount("hola"))) {
                throw new Exception("contains w/ WordCount doesn't work");
            }
            System.out.println("contains works");

            //Remove tester
            wcl.remove(0);
            if(!wcl.toString().equals("[hola - 2, ni hao - 3]")) {
                throw new Exception("remove w/ int doesn't work");
            }

            wcl.remove(new WordCount("ni hao"));
            if(!wcl.toString().equals("[hola - 2]")) {
                throw new Exception("remove w/ WordCount doesn't work");
            }

            wcl.remove("hola");
            if(!wcl.toString().equals("[]")) {
                throw new Exception("remove w/ string doesn't work");
            }
            System.out.println("remove works");

            //Size tester
            if(wcl.size() != 0) {
                throw new Exception("size doesn't work");
            }
            System.out.println("size works");

            System.out.println(wcl + "\n-----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
