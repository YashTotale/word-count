public class WordCountTester {
    public static void main(String[] args) {
    	new WordCountTester().start();
    }

    private void start() {
        testWordCountClass();
        //testWordCountListClass();
    }

    /**
     * The method below is for testing the different methods that a WordCount needs
     * to satisfy. You can add in some more print statements and more testing code
     * as you see fit. This should help guide you in writing the WordCount class.
     */
    private void testWordCountClass() {
        try {
            System.out.println("Testing WordCount");
            WordCount wc1 = new WordCount("hello");
            WordCount wc2 = new WordCount("hi", 2);
            // adds 1 to the count so it now represents "hello" with a count of 2
            wc1.increment();
            wc1.increment(3); // adds 3 to the count, so it is now 5
            wc2.increment();// adds 1 to wc2 count so it is now 3

            if(wc1.getCount() != 5) {
                throw new Exception("Increment or getCount doesn't work");
            }

            int x = wc1.getCount();// x should be 5
            String s = wc1.getWord();// s should be "hello"
            System.out.println(wc1);// Should print: hello - 5
            System.out.println(wc2);// Should print: hi - 3
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
//    	System.out.println("Testing WordCountList");
//        // Now I can add, get, indexOf among other methods.
//        WordCountList wcl = new WordCountList();
//        wcl.add("hola");
//        wcl.add(new WordCount("bonjour"));
//        wcl.add(new WordCount("ni hao", 3));
//        wcl.get(2).increment();
//        wcl.add(new WordCount("hola"));
//        wcl.add("bonjour");
//        wcl.get(wcl.indexOf("hola")).increment();
//        System.out.println(wcl);

    }
}
