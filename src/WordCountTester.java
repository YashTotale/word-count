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
            System.out.println("\nTesting WordCount");

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
            if(wc1.getCount() != expectedCount1 || wc2.getCount() != expectedCount2) {
                throw new Exception("Increment or getCount doesn't work");
            }
            System.out.println("Increment and getCount work");

            //getWord tester
            if(!wc1.getWord().equals(word1)) {
                throw new Exception("getWord doesn't work");
            }
            System.out.println("getWord works");

            //toString tester
            if(!wc1.toString().equals(word1 + " - " + expectedCount1)) {
                throw new Exception("toString doesn't work");
            }
            System.out.println("toString works");

            //Compare Alpha tester
            if(wc1.compareAlpha(wc1) != 0) {
                throw new Exception("Comparing the same Word Count alphabetically doesn't work");
            }
            if(wc1.compareAlpha(wc2) > 0) {
                throw new Exception("Comparing different Word Counts alphabetically doesn't work");
            }
            System.out.println("compareAlpha works");

            //Compare Frequency tester
            if(wc1.compareFrequency(wc1) != 0) {
                throw new Exception("Comparing the same Word Count's frequency doesn't work");
            }
            if(wc1.compareFrequency(wc2) < 0) {
                throw new Exception("Comparing different Word Counts' frequency doesn't work");
            }
            System.out.println("compareFrequency works");

            //Compare Length tester
            if(wc1.compareLength(wc1) != 0) {
                throw new Exception("Comparing the same Word Count's length doesn't work");
            }
            if(wc1.compareLength(wc2) < 0) {
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
    	System.out.println("\nTesting WordCountList");
//        // Now I can add, get, indexOf among other methods.
        WordCountList wcl = new WordCountList();
        wcl.add("hola");
        wcl.add(new WordCount("bonjour"));
        wcl.add(new WordCount("ni hao", 3));
//        wcl.get(2).increment();
        wcl.add(new WordCount("hola"));
        wcl.add("bonjour");
//        wcl.get(wcl.indexOf("hola")).increment();
        System.out.println(wcl);

    }
}
