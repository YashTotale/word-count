import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;

public class FileProcessor {

    private WordCountList list = new WordCountList();
    private Scanner fileScanner;
    private File file;

    public static void main(String[] args) {
        new FileProcessor().start();
    }

    private void start() {
        promptAndOpenFile();

        while (this.fileScanner.hasNext()) {
            String word = this.fileScanner.next();
            word = standardize(word);
            if (!word.isEmpty()) list.add(word);
        }

        this.fileScanner.close();


        String[] resultTypes = {"Alphabetically", "By Frequency", "By Length"};

        String resultType = (String) JOptionPane.showInputDialog(null, "How do you want to display the results?", "Result Type", JOptionPane.QUESTION_MESSAGE, null, resultTypes, resultTypes[0]);

        String results = list.toString();

        if(resultType.equals(resultTypes[1]) || resultType.equals(resultTypes[2])) {
            String[] orderTypes = {"Ascending", "Descending"};

            String orderType = (String) JOptionPane.showInputDialog(null, "How do you want to sort the results?", "Sort Type", JOptionPane.QUESTION_MESSAGE, null, orderTypes, orderTypes[1]);

            boolean ascending = false;

            if(orderType.equals(orderTypes[0])) ascending = true;

            results = resultType.equals(resultTypes[1]) ? list.sortFrequency(ascending).toString() : list.sortLength(ascending).toString();
        }

        JOptionPane.showMessageDialog(null, results, "Results " + resultType, JOptionPane.INFORMATION_MESSAGE);

    }

    private void promptAndOpenFile() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                this.file = file;

                Scanner sc = new Scanner(this.file);

                this.fileScanner = sc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method lowercases the String.
     * This method cleans off any characters from the front and back of the
     * specified String.   Example:  if s = "Happy,", "happy" is returned
     *
     * @param s The String to be standardized.
     * @return The standardized String
     */
    private String standardize(String s) {
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetter(c)) {
                s = s.substring(0, i) + s.substring(i + 1);
                i--;
            }
        }

        return s;
    }


}
