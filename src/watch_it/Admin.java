package watch_it;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admin {
    //gonna fix the try/catch/finally later --------
    //there's more work after the User class ------- maybe ?

    private String username;
    private int password;
    //counters gonna be pairs ,key for the enum class value for repentance -- javafx???
    public static int basicPlanCounter;
    public static int standardPlanCounter;
    public static int premiumPlanCounter;
    private static short planIndex;

    public static ArrayList<String> readFile(File file) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            String line = "";
            BufferedReader b = new BufferedReader(new FileReader(file));
            while ((line = b.readLine()) != null) {
                arrayList.add(line);
            }
            b.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
    public static void writeOnFile(ArrayList<String> arrayList, File file){
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(file));
            for(String index:arrayList){
                b.write(index);
                b.newLine();
            }
            b.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //if the record exists in database >> gonna return the whole record[don't forget to split it]
    //otherwise gonna return null [make sure to check before dealing with return val].

    //returned string isn't splitted
    public static String existsInFile(ArrayList<String> arrayList, String username){
        boolean exist = false;
        String[] eachLine;
        for (String index: arrayList){
            eachLine = index.split(",");
            if(eachLine[0].equals(username)){
                return index;
            }
        }
        return null;
    }

    //record to be deleted is not splitted
    public static void deleteOneRecord(ArrayList<String> arrayList, String record){
        arrayList.remove(record);
    }
    public static void changeCell(ArrayList<String> arrayList, String username, String oldCell, String newCell){
        String recuiredLine = existsInFile(arrayList, username);
        String []row = recuiredLine.split(",");
        for (int index=0; index<row.length; index++){
            if(row[index].equals(username))
                row[index] = newCell;
        }
        arrayList.set(arrayList.indexOf(recuiredLine),String.join(",",row));
    }
    //enum ----------------
    public static String getSpecificCell(ArrayList<String> arrayList, String username, int index){
        String wantedRecord = existsInFile(arrayList, username);
        String []row = wantedRecord.split(",");
        return row[index];
    }
    //didnt test it yet
    public static Subscription.Plans getMostSubscripedPlan(ArrayList<String> arrayList){
        Subscription.Plans plan = null;
        for (String index:arrayList){
            String []line = index.split(",");
            HashMap<Integer,Subscription.Plans> map = new HashMap<>();
            if(line[planIndex].equals("basic"))
                basicPlanCounter++;
            else if (line[planIndex].equals("standard"))
                standardPlanCounter++;
            else
                premiumPlanCounter++;
            int max = Math.max(standardPlanCounter,Math.max(basicPlanCounter,premiumPlanCounter));

            map.put(basicPlanCounter,Subscription.Plans.BASIC);
            map.put(standardPlanCounter,Subscription.Plans.STANDARD);
            map.put(premiumPlanCounter,Subscription.Plans.PREMIUM);

            plan = map.get(max);
        }
        return plan;
    }
}
