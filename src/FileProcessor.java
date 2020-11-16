import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;

public class FileProcessor {

    WordCountList list = new WordCountList();
    Scanner fileScanner;

    public static void main(String[] args) {
        new FileProcessor().start();
    }

    private void start() {
        promptAndOpenFile();

        while (this.fileScanner.hasNext()) {
            String word = this.fileScanner.next();
            word = standardize(word);
            list.add(word);
        }

        this.fileScanner.close();

        String[] resultTypes = {"Alphabetically", "By Frequency", "By Length"};

        String resultType = (String)JOptionPane.showInputDialog(null, "Pick a result type", "Result Type", JOptionPane.QUESTION_MESSAGE, null, resultTypes, resultTypes[0]);

        System.out.println(resultType);

        JOptionPane.showMessageDialog(null, list.toString(), "Results " + resultType, JOptionPane.INFORMATION_MESSAGE);

        //After the file is done being processed, the results
        // will be displayed.  This will include displaying the words alphabetically,
        // or by frequency or by length (depending on the user's choice).

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

                Scanner sc = new Scanner(file);

                this.fileScanner = sc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

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
            if (!Character.isLetterOrDigit(c)) {
                s = s.substring(0, i) + s.substring(i + 1);
                i--;
            }
        }

        return s;
    }


}
