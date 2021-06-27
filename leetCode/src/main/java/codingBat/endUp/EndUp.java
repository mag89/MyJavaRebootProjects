package codingBat.endUp;


/*
Given a string, return a new string where the last 3 chars are now in upper case. If the string has less than 3 chars,
uppercase whatever is there. Note that str.toUpperCase() returns the uppercase version of a string.

endUp("Hello") → "HeLLO"
endUp("hi there") → "hi thERE"
endUp("hi") → "HI"
 */
public class EndUp {
    public String endUp(String str) {

        if (str == null || str.equals("")) {
            return "";
        }

        int l = str.length();

        if (l <= 3) {
            return str.toUpperCase();
        }

        int pivotIndex = l - 3;

        return str.substring(0, pivotIndex) + str.substring(pivotIndex).toUpperCase();
    }
}