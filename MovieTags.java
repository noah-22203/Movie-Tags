/**
 * Assignment 01 - Movie Tags
 *
 * @author Noah Steaderman
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;

public class MovieTags {

    public static ArrayList<Tag> ReadFile(String MyFile) {
        Path p2 = Path.of(MyFile); /* newer way */
        ArrayList<Tag> tags = new ArrayList<>();
        /* using a try-with-resource clause to prevent resource leak*/
        try(BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)){
            String currentLine = null;
            System.out.println("Reading data file .....");
            //while there is content on the current line
            while((currentLine = reader.readLine()) != null){
                String[] values = currentLine.split(",");
                boolean added = false;
                for (Tag tag : tags) { //checks to see if tag is contained in arraylist already
                    if (tag.getTag().equals(values[2].trim())) {
                        added = true;
                        tag.increment();
                        break;
                    }
                }
                if (!added) {
                    tags.add(new Tag(values[2].trim()));
                }

            }
        }catch(IOException ex){
            ex.printStackTrace(); //handle an exception here
        }
        return tags;
    }

    public static void SortFile(ArrayList<Tag> arr) {
        if (arr == null || arr.size() <= 1) { // base case
            return;
        }
        int mid = arr.size() / 2;
        ArrayList<Tag> left = new ArrayList<>(arr.subList(0, mid)); // splits into two arrays
        ArrayList<Tag> right = new ArrayList<>(arr.subList(mid, arr.size()));
        SortFile(left); // recursive call
        SortFile(right);
        int li = 0, ri = 0, tar = 0;
        while (li < left.size() && ri < right.size()) {
            if (left.get(li).compareTo(right.get(ri)) < 0) { //if left smaller than right
                arr.set(tar++, left.get(li++));
            } else if (left.get(li).compareTo(right.get(ri)) == 0) { //if equal, sort alphabetically
                if (left.get(li).getTag().compareTo(right.get(ri).getTag()) < 0) {
                    arr.set(tar++, left.get(li++));
                } else {
                    arr.set(tar++, right.get(ri++));
                }
            } else {
                arr.set(tar++, right.get(ri++));
            }
        }
        while (li < left.size()) {
            arr.set(tar++, left.get(li++));
        }
        while (ri < right.size()) {
            arr.set(tar++, right.get(ri++));
        }
    }

    public static void UserIn(ArrayList<Tag> arr) {
        Scanner sc = new Scanner(System.in);
        String in = "";
        System.out.println("Search by Tag or Tag Count? (Enter T or C... or EXIT to exit):");
        in = sc.nextLine();
        while (!in.equals("EXIT")) {
            if (in.equals("T")) {
                int count = 0;
                System.out.println("Tag to search for:");
                String tFind = sc.nextLine();
                for (int i = 0; i < arr.size()-1; i++) {
                    if(tFind.equals(arr.get(i).getTag())) {
                        System.out.println("Tag \"" + tFind + "\" occurred " + arr.get(i).getCount() + " times.");
                        count = 1;
                    }
                }
                if (count == 0) {
                    System.out.println("Tag \"" + tFind + "\" does not exist.");
                    UserIn(arr);
                }
                UserIn(arr);
            } else if (in.equals("C")) {
                int count2 = 0;
                System.out.println("Count to search for:");
                Integer iFind = 0;
                try {
                    iFind = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Is that even a number? C'mon, man!");
                    UserIn(arr);
                }
                System.out.println("Tags with " + iFind + " occurrences:");
                for (int i = 0; i < arr.size()-1; i++) {
                    if(iFind.equals(arr.get(i).getCount())) {
                        System.out.println("* " + arr.get(i).getTag());
                        count2 = 1;
                    }
                }
                if (count2 == 0) {
                    System.out.println("None :(");
                    UserIn(arr);
                }
                UserIn(arr);
            } else {
                System.out.println("invalid response!!!");
                UserIn(arr);
            }
        }
        System.out.println("Bye!");
        exit(0);
    }
    public static void main(String[] args) {
        String MyFile = args[0];
        ArrayList<Tag> csv = new ArrayList<>();;
        csv = (ArrayList<Tag>) ReadFile(MyFile);
        SortFile(csv);
        System.out.println("==========================================");
        System.out.println("*** Highest 3 movies by count ***");
        System.out.println(csv.get(csv.size()-1).getCount() + ": " + csv.get(csv.size()-1).getTag());
        System.out.println(csv.get(csv.size()-2).getCount() + ": " + csv.get(csv.size()-2).getTag());
        System.out.println(csv.get(csv.size()-3).getCount() + ": " + csv.get(csv.size()-3).getTag());
        System.out.println("*** Lowest 3 movies by count ***");
        System.out.println(csv.get(0).getCount() + ": " + csv.get(0).getTag());
        System.out.println(csv.get(1).getCount() + ": " + csv.get(1).getTag());
        System.out.println(csv.get(2).getCount() + ": " + csv.get(2).getTag());
        System.out.println("==========================================");
        UserIn(csv);
    }
}
