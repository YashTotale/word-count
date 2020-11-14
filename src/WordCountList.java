import java.util.ArrayList;
import java.util.Arrays;

public class WordCountList {
    private WordCount[] list;

    public WordCountList() {
        this.list = new WordCount[0];
    }

    public void add(WordCount w) {
        int i = 0;
        try {
            while (w.compareAlpha(this.list[i]) > 0) i++;

            WordCount curr = this.list[i];

            if (curr.compareAlpha(w) == 0) curr.increment();
            else {
                WordCount[] newList = new WordCount[this.list.length + 1];
                newList = copy(newList, 0, i);
                newList[i] = w;
                newList = copy(newList, i + 1, newList.length, -1);
                list = newList;
            }

        } catch (Exception e) {
            WordCount[] newList = new WordCount[this.list.length + 1];
            newList = copy(newList, 0, this.list.length);
            newList[this.list.length] = w;
            this.list = newList;
        }
    }

    public void add(String s) {
        add(new WordCount(s));
    }

    public void remove(int index) {
        WordCount[] newList = new WordCount[this.list.length - 1];
        newList = copy(newList, 0, index);
        newList = copy(newList, index, newList.length, 1);
        list = newList;
    }

    public void remove(WordCount w) {
        remove(indexOf(w));
    }

    public void remove(String s) {
        remove(indexOf(s));
    }

    private WordCount[] copy(WordCount[] arr, int start, int end) {
        return copy(arr, start, end, 0);
    }

    private WordCount[] copy(WordCount[] arr, int start, int end, int offset) {
        for (int x = start; x < end; x++) arr[x] = this.list[x + offset];
        return arr;
    }

    public WordCount get(int index) {
        return this.list[index];
    }

    public WordCount get(String s) {
        return this.list[indexOf(s)];
    }

    public int indexOf(String s) {
        return indexOf(new WordCount(s));
    }

    public int indexOf(WordCount w) {
        return indexOf(w, 0, this.list.length - 1);
    }

    private int indexOf(WordCount w, int min, int max) {
        int mid = (min + max) / 2;
        WordCount middle = list[mid];

        int alphaDiff = middle.compareAlpha(w);

        if (alphaDiff == 0) return mid;

        if (min == max) return -1;

        if (alphaDiff < 0) return indexOf(w, mid + 1, max);

        return indexOf(w, min, mid - 1);
    }

    public boolean contains(WordCount w) {
        return indexOf(w) != -1;
    }

    public boolean contains(String s) {
        return indexOf(s) != -1;
    }

    public String toString() {
        return Arrays.toString(this.list);
    }
}
