/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsemessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ramesh
 */
public class VerifyWords {

    private boolean IsInteger(String Number) {             // Checks whether the input String is Number or Not...
        boolean Value = true;
        for (int i = 0; i < Number.length(); i++) {
            if (Number.charAt(i) > '9' || Number.charAt(i) < '0') {
                return false;
            }
        }
        return Value;
    }

    public String ReplaceIP(String Word) {                   // Checks the String that is it IP or not...

        String IPPatt = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        Pattern pattern = Pattern.compile(IPPatt);
        Matcher matcher = pattern.matcher(Word);

        while (matcher.find()) {
            Word = Word.replaceFirst(IPPatt, " ");         //Replace all the IP address by "IP
        }
        return Word;
    }

    public String ReplaceMAC(String Word) {

        String MacPatt = "(([0-9a-f][0-9a-fA-F])\\:){5}" + "([0-9a-f][0-9a-fA-F])";         //MAC pattern for Regular expression.
        Pattern pattern = Pattern.compile(MacPatt);
        Matcher matcher = pattern.matcher(Word);

        while (matcher.find()) {
            Word = Word.replaceFirst(MacPatt, " ");         //Replace all the IP address by ReMAC
        }
        return Word;
    }

    public String ReplaceDate(String Word) {
        String DatPatt = "(Jan|Feb|Mar|Apr|Jun|May|June|Oct|Nov|Dec)(\\s*|[-/])(0[1-9]|[12][0-9]|[1-9])";
        Pattern pattern = Pattern.compile(DatPatt);
        Matcher matcher = pattern.matcher(Word);

        while (matcher.find()) {
            Word = Word.replaceFirst(DatPatt, " ");         //Replace all the IP address by "ReDate
        }
        return Word;
    }

    public String ReplaceTime(String Word) {
        String TimePatt = "([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
        Pattern pattern = Pattern.compile(TimePatt);
        Matcher matcher = pattern.matcher(Word);
        while (matcher.find()) {
            Word = Word.replaceFirst(TimePatt, " ");         //Replace all the IP address by "IP
        }
        return Word;
    }

    public String ReplaceDateAndTime(String Word) {
        String DatTimePatt = "(Jan|Feb|Mar|Apr|Jun|May|June|Oct|Nov|Dec)(\\s*|[-/])(0[1-9]|[12][0-9]|[1-9])"
                + "\\s*"
                + "([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
        Pattern pattern = Pattern.compile(DatTimePatt);
        Matcher matcher = pattern.matcher(Word);

        while (matcher.find()) {
            Word = Word.replaceFirst(DatTimePatt, " ");         //Replace all the IP address by "ReDate
        }
        return Word;
    }

    public boolean IsMAC(String word) {
        return (word.matches("[A-Fa-f1-9][A-Fa-f1-9]:[A-Fa-f1-9][A-Fa-f1-9]:[A-Fa-f1-9][A-Fa-f1-9]:[A-Fa-f1-9][A-Fa-f1-9]:[A-Fa-f1-9][A-Fa-f1-9]:[A-Fa-f1-9][A-Fa-f1-9]"));
    }

    public boolean isValidDate(String inDate) {
        if (inDate == null) {
            return false;
        }
        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss");
        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }
        dateFormat.setLenient(false);

        try {
            //parse the inDate parameter
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public boolean IsTime(String Word) {

        boolean Value = true;               // Determines that String is MAC or not....

        ArrayList<String> MacArray = new ArrayList<String>();
        StringTokenizer Token = null;
        String Delim = ":";
        Token = new StringTokenizer(Word, Delim);
        String TempString;

        while (Token.hasMoreElements()) {
            TempString = Token.nextToken();
            if (TempString.length() == 2 && IsInteger(TempString)) {
                MacArray.add(TempString);
            } else {
                return false;
            }
        }
        if (MacArray.size() == 3) {
            return Value;
        } else {
            return false;
        }
    }
}
