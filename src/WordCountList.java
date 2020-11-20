import java.util.ArrayList;
import java.util.Arrays;

public class WordCountList {
    private WordCount[] list;

    public WordCountList() {
        this.list = new WordCount[0];
    }
    public WordCountList(WordCount[] arr) {
        this.list = arr;
    }

    //Add
    /**
     * Adds the WordCount alphabetically if it is not already present.
     * If the WordCount is present, its count is incremented by 1.
     *
     * @param w The WordCount to add
     */
    public void add(WordCount w) {
        int i = 0;
        try {
            while (w.compareAlpha(this.list[i]) > 0) i++;

            WordCount curr = this.list[i];

            //If WordCount already exists, increment
            if (curr.compareAlpha(w) == 0) curr.increment();
            else {
                //If it doesn't exist, add it where it fits alphabetically
                WordCount[] newList = new WordCount[this.list.length + 1];
                newList = copy(newList, 0, i);
                newList[i] = w;
                newList = copy(newList, i + 1, newList.length, -1);
                list = newList;
            }

        } catch (Exception e) {
            //If WordCount is alphabetically last, add it to the end
            WordCount[] newList = new WordCount[this.list.length + 1];
            newList = copy(newList, 0, this.list.length);
            newList[this.list.length] = w;
            this.list = newList;
        }
    }

    /**
     * Adds the WordCount representation of the string alphabetically if it is not already present.
     * If the WordCount is present, its count is incremented by 1.
     *
     * @param s The string to add
     */
    public void add(String s) {
        add(new WordCount(s));
    }

    //Remove
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

    //Copy
    private WordCount[] copy() {
        int n = this.list.length;

        WordCount[] copied = new WordCount[n];

        for(int i = 0; i < n; i++) {
            WordCount pos = this.list[i];

            WordCount copy = new WordCount(pos.getWord(), pos.getCount());

            copied[i] = copy;
        }

        return copied;
    }

    private WordCount[] copy(WordCount[] arr, int start, int end) {
        return copy(arr, start, end, 0);
    }

    private WordCount[] copy(WordCount[] arr, int start, int end, int offset) {
        for (int x = start; x < end; x++) arr[x] = this.list[x + offset];
        return arr;
    }

    //Get
    public WordCount get(int index) {
        return this.list[index];
    }

    public WordCount get(String s) {
        return this.list[indexOf(s)];
    }

    //IndexOf
    public int indexOf(String s) {
        return indexOf(new WordCount(s));
    }

    public int indexOf(WordCount w) {
        return indexOf(w, 0, this.list.length - 1);
    }

    private int indexOf(WordCount w, int min, int max) {
        int mid = (min + max) / 2;
        WordCount middle = this.list[mid];

        int alphaDiff = middle.compareAlpha(w);

        if (alphaDiff == 0) return mid;

        if (min == max) return -1;

        if (alphaDiff < 0) return indexOf(w, mid + 1, max);

        return indexOf(w, min, mid - 1);
    }

    //Contains
    public boolean contains(WordCount w) {
        return indexOf(w) != -1;
    }

    public boolean contains(String s) {
        return indexOf(s) != -1;
    }

    //Size
    public int size() {
        return this.list.length;
    }

    //Sort
    public WordCountList sortFrequency(boolean ascending) {
        int n = this.list.length;

        WordCount[] newArr = copy();

        for (int i = 1; i < n; i++) {
            WordCount w = newArr[i];

            int count = w.getCount();

            int j = i - 1;

            while (j >= 0 && (ascending ? newArr[j].getCount() > count : newArr[j].getCount() < count)) {
                newArr[j + 1] = newArr[j];
                j = j - 1;
            }
            newArr[j + 1] = w;
        }
        
        return new WordCountList(newArr);
    }

    public WordCountList sortLength(boolean ascending) {
        int n = this.list.length;

        WordCount[] newArr = copy();

        for (int i = 1; i < n; i++) {
            WordCount w = newArr[i];

            int length = w.getWord().length();

            int j = i - 1;

            while (j >= 0 && (ascending ? newArr[j].getWord().length() > length : newArr[j].getWord().length() < length)) {
                newArr[j + 1] = newArr[j];
                j = j - 1;
            }
            newArr[j + 1] = w;
        }

        return new WordCountList(newArr);
    }

    interface MapFunction {
        WordCount run(WordCount w, int index);
    }

    //Map
    public WordCountList map(MapFunction func) {
        WordCount[] mappedArr = copy();

        for(int i = 0; i < mappedArr.length; i++) {
            mappedArr[i] = func.run(mappedArr[i], i);
        }


        return new WordCountList(mappedArr);
    }

    /**
     * @return The string representation of the WordCountList ("[word - count, word - count]")
     */
    public String toString() {
        return Arrays.toString(this.list);
    }
}
