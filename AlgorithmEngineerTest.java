import java.sql.SQLOutput;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class tests the functionality of the Algorithm Engineer made methods in the class HashtableMap by itself
 * and with the group members code, and the FrontendDeveloper made methods
 *
 * @author Haniel Aryan Alva
 */
public class AlgorithmEngineerTest {

    /**
     * tests the functionality of the ISBNValidator() method with a valid ISBN13 number
     *
     * @return returns true if the ISBNValidator() works as expected
     */
    public static boolean test1() {

        ISBNValidator tester = new ISBNValidator();
        String testStr = "9780306406157";
        if (!(tester.validate(testStr))){
            return false;
        }
        return true;
    }

    /**
     * tests the functionality of the ISBNValidator() method with a null object
     *
     * @return returns true if the ISBNValidator() catches the null object
     */
    public static boolean test2() {

        ISBNValidator tester = new ISBNValidator();
        String testStr = null;
        if (tester.validate(testStr)){
            return false;
        }
        return true;
    }

    /**
     * tests the functionality of the ISBNValidator() method with an invalid ISBN13 number
     *
     * @return returns true if the ISBNValidator() catches the invalid number
     */
    public static boolean test3() {

        ISBNValidator tester = new ISBNValidator();
        String testStr1 = "9780306406159";
        String testStr2 = "978030c40615f";
        if ((tester.validate(testStr1)) || (tester.validate(testStr2))){
            return false;
        }
        return true;
    }

    /**
     * tests the functionality of the iterator() method with strings as the key and values
     *
     * @return returns true it has iterated through the hashmap successfully
     */
    public static boolean test4() {

        IterableHashtableMap<String, String> tempMap = new IterableHashtableMap<String, String>();
        tempMap.put("aaa", "1");
        tempMap.put("bbb", "2");
        tempMap.put("ccc", "3");
        String checkStr = "";
        for (String s: tempMap){
            checkStr += s;
        }
        if (!(checkStr.equals("123"))){
            return false;
        }
        return true;
    }

    /**
     * tests the functionality of the iterator() method with integers as the key and values
     *
     * @return returns true it has iterated through the hashmap successfully
     */
    public static boolean test5() {

        IterableHashtableMap<Integer, Integer> tempMap = new IterableHashtableMap<Integer, Integer>();
        tempMap.put(1, 44);
        tempMap.put(2, 55);
        tempMap.put(3, 66);
        Integer checkSum = 0;
        for (Integer i: tempMap){
            checkSum += i;
        }
        if (checkSum != 165){
            return false;
        }
        return true;
    }

    /**
     * tests the functionality of the iterator() method with null
     *
     * @return returns true it has iterated through the hashmap successfully
     */
    public static boolean test6() {

        IterableHashtableMap<Integer, String> tempMap = new IterableHashtableMap<Integer, String>();

        try{
            String checkStr = "";
            for (String s: tempMap){
                checkStr += s;
            }
        }catch(NullPointerException n){
            return false;
        }

        tempMap.put(null, "44");
        try{
            String checkStr = "";
            for (String s: tempMap){
                checkStr += s;
            }
        }catch(NullPointerException n){
            return false;
        }

        tempMap.put(1, null);
        try{
            String checkStr = "";
            for (String s: tempMap){
                checkStr += s;
            }
        }catch(NullPointerException n){
            return false;
        }

        return true;
    }

    /**
     * tests the functionality of the iterator() method with integer keys and string values after integration
     *
     * @return returns true it has iterated through the hashmap successfully
     */
    public static boolean test7() {

        IterableHashtableMap<Integer, String> tempMap = new IterableHashtableMap<Integer, String>();
        tempMap.put(1, "44");
        tempMap.put(2, "55");
        tempMap.put(3, "66");
        String checkStr = "";
        for (String s: tempMap){
            checkStr += s;
        }

        if (!(checkStr.equals("445566"))){
            return false;
        }
        return true;
    }

    /**
     * tests the functionality of the ISBNValidator() method with an invalid ISBN13 number after integration
     *
     * @return returns true if the ISBNValidator() works as expected
     */
    public static boolean test8() {

        ISBNValidator tester = new ISBNValidator();
        String testStr = "9780306406159";
        if (tester.validate(testStr)){
            return false;
        }
        return true;
    }

    public static String testPrintHelper(boolean pass){
        if (pass){
            return "passed";
        }else {
            return "failed";
        }
    }

    /**
     * Tests the functionality of look up ISBN feature created by the frontend developer with a valid ISBN
     *
     * @return true if the output matches expected output
     * @throws FileNotFoundException if books.csv isn't found
     */
    public static boolean test9() throws FileNotFoundException {
        TextUITester tester = new TextUITester("1\n9780060934910\n4");
        BookLoader bookLoader = new BookLoader();
        List<IBook> bookList = bookLoader.loadBooks("books.csv");
        BookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) backend.addBook(book);
        ISBNValidator isbnValidator = new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        BookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);

        frontend.runCommandLoop();

        String output = tester.checkOutput();

        if(output.contains("Kitchen Confidential: Adventures in the Culinary Underbelly")){
            System.out.println("passed");
            return true;
        }
        else{
            System.out.println("failed");
            return false;
        }
    }

    /**
     * Tests the functionality of search by title created by the frontend developer with a user input
     *
     * @return true if the output matches expected output
     * @throws FileNotFoundException if books.csv isn't found
     */
    public static boolean test10() throws FileNotFoundException {
        TextUITester tester = new TextUITester("2\nHarry\n4");
        BookLoader bookLoader = new BookLoader();
        List<IBook> bookList = bookLoader.loadBooks("books.csv");
        BookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) backend.addBook(book);
        ISBNValidator isbnValidator = new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        BookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);

        frontend.runCommandLoop();

        String output = tester.checkOutput();

        if(output.contains("Harry Potter")){
            System.out.println("passed");
            return true;
        }
        else{
            System.out.println("failed");
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.print("AlgorithmEngineer Individual Test 1: ");
            System.out.println(testPrintHelper(test1()));
            System.out.print("AlgorithmEngineer Individual Test 2: ");
            System.out.println(testPrintHelper(test2()));
            System.out.print("AlgorithmEngineer Individual Test 3: ");
            System.out.println(testPrintHelper(test3()));
            System.out.print("AlgorithmEngineer Individual Test 4: ");
            System.out.println(testPrintHelper(test4()));
            System.out.print("AlgorithmEngineer Individual Test 5: ");
            System.out.println(testPrintHelper(test5()));
            System.out.print("AlgorithmEngineer Individual Test 6: ");
            System.out.println(testPrintHelper(test6()));
            System.out.print("AlgorithmEngineer Integration Test 1: ");
            System.out.println(testPrintHelper(test7()));
            System.out.print("AlgorithmEngineer Integration Test 2: ");
            System.out.println(testPrintHelper(test8()));
            System.out.print("AlgorithmEngineer Partner FrontendDeveloper Test 1: ");
            System.out.println(testPrintHelper(test9()));
            System.out.print("AlgorithmEngineer Partner FrontendDeveloper Test 2: ");
            System.out.println(testPrintHelper(test10()));
        } catch (Exception e){
            System.out.println("Error. Exception thrown.");
        }
    }

}
