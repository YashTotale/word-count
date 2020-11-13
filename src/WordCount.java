public class WordCount {
    private String word;
    private int count;

    //Constructors
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }
    public WordCount(String word) {
        this(word, 1);
    }

    //Mutators
    public void increment(int count) {
        this.count+=count;
    }
    public void increment() {
        increment(1);
    }

    //Getters
    public String getWord() {
        return this.word;
    }
    public int getCount() {
        return this.count;
    }

    //Compare
    /**
     * @param compare The WordCount to compare to
     * @return 0 if words are equal.
     *         Negative if this WordCount should precede WordCount compare.
     *         Positive if WordCount compare should precede this WordCount.
     */
    public int compareAlpha(WordCount compare) {
        return this.word.compareTo(compare.getWord());
    }

    /**
     * @param compare The WordCount to compare to
     * @return 0 if the frequencies are equal.
     *         Negative if the compare WordCount's frequency is higher.
     *         Positive if this WordCount's frequency is higher.
     */
    public int compareFrequency(WordCount compare) {
        return this.count - compare.getCount();
    }

    /**
     * @param compare The WordCount to compare to
     * @return 0 if the frequencies are equal.
     *         Negative if the compare WordCount's length is greater.
     *         Positive if this WordCount's length is higher.
     */
    public int compareLength(WordCount compare) {
        return this.word.length() - compare.getWord().length();
    }

    //Misc
    public String toString() {
        return this.word + " - " + this.count;
    }
}
