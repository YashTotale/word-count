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

    public WordCount get(int index) {
        return list.get(index);
    }

    public WordCount get(String s) {
        return list.get(indexOf(s));
    }

    public int indexOf(String s) {
        return indexOf(s, 0, list.size() - 1);
    }

    private int indexOf(String s, int min, int max) {
        int mid = (min + max) / 2;
        WordCount middle = list.get(mid);
        int alphaDiff = middle.compareAlpha(new WordCount(s));
        if (alphaDiff == 0) {
            return mid;
        }
        if (alphaDiff < 0) {
            return indexOf(s, mid + 1, max);
        }
        return indexOf(s, min, mid - 1);
    }

    public String toString() {
        return list.toString();
    }
}
