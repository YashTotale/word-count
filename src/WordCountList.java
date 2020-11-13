import java.util.ArrayList;

public class WordCountList {
    private ArrayList<WordCount> list;

    public WordCountList() {
        list = new ArrayList<>();
    }

    public void add(WordCount w) {
        int i = 0;
        try {
            while (w.compareAlpha(list.get(i)) > 0) {
                i++;
            }

            WordCount curr = list.get(i);
            if (curr.compareAlpha(w) == 0) {
                curr.increment();
            } else {
                list.add(i, w);
            }

        } catch (Exception e) {
            list.add(w);
        }
    }
    public void add(String s) {
        add(new WordCount(s));
    }

    public String toString() {
        return list.toString();
    }
}
