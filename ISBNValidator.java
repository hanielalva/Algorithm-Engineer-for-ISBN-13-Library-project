import java.sql.SQLOutput;

/**
 * This class is used to validate whether a number is a valid ISBN 13 number
 *
 * @author Haniel Aryan Alva
 */
public class ISBNValidator implements IISBNValidator{

    /**
     * Checks is the given ISBN number is a valid ISBN13 number.
     *
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */
    @Override
    public boolean validate(String isbn13) {

        if ((isbn13 == null) || (isbn13.length() != 13)){
            return false;
        }

        int[] isbnArr = new int[isbn13.length()];

        try {
            int sum = 0;

            for (int i = 0; i < isbn13.length(); i++) {
                if (i == 12){
                    isbnArr[i] = Integer.parseInt(isbn13.substring(i));
                    break;
                }
                isbnArr[i] = Integer.parseInt(isbn13.substring(i, i + 1));
                if (i % 2 == 0){
                    sum += isbnArr[i];
                }else{
                    sum += isbnArr[i]*3;
                }
            }

            int result = 10 - (sum % 10);
            if (result == 10){
                result = 0;
            }

            if (result != isbnArr[12]){
                return false;
            }

            sum += result;
            if (sum % 10 != 0){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }

        return true;
    }


}
