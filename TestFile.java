import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class TestFile
{
    public static void main(String[] Args) throws IOException
    {
        String searchStart = "  \"result\": {\n    \"item\": \"";
        String searchEnd = "\"";
        
        String testStringOld = "  \"result\": {\n    \"item\": \"minecraft:acacia_boat\"\n  }\n}";
        String testString = "{\n  \"type\": \"minecraft:crafting_shapeless\",\n  \"group\": \"planks\",\n  \"ingredients\": [\n    {\n      \"tag\": \"minecraft:acacia_logs\"\n    }\n  ],\n  \"result\": {\n    \"item\": \"minecraft:acacia_planks\",\n    \"count\": 4\n  }\n}";
        replaceSelected(testString, "minecraft:dirt");
        //System.out.println(testString.indexOf(searchStart) + 1);
        //System.out.println(testString.indexOf(searchEnd, testString.indexOf(searchStart) + 1));
    }
    
    public static void replaceSelected(String inputStr, String replaceWith) throws IOException {
        try {
            // input the file content to the StringBuffer "input"
            //BufferedReader file = new BufferedReader(new FileReader(fileName));
            //StringBuffer inputBuffer = new StringBuffer();
            //String line;
            
            //while ((line = file.readLine()) != null) {
            //    inputBuffer.append(line);
            //    inputBuffer.append('\n');
            //}
            //file.close();
            //String inputStr = inputBuffer.toString();
            
            System.out.println(inputStr); // display the original file for debugging
            
            // logic to replace lines in the string (could use regex here to be generic)
            String searchStart = "  \"result\": {\n    \"item\": \"";
            String searchEnd = "\"";
            inputStr =  
                inputStr.replace(
                inputStr.substring(
                    inputStr.indexOf(searchStart) + 27,
                    inputStr.indexOf(searchEnd, inputStr.indexOf(searchStart) + 27 + 1)),
                replaceWith)
                ;
            /*
            if (type.equals("0")) {
                inputStr = inputStr.replace(replaceWith + "1", replaceWith + "0"); 
            } else if (type.equals("1")) {
                inputStr = inputStr.replace(replaceWith + "0", replaceWith + "1");
            }
            */
            
            // display the new file for debugging
            System.out.println("----------------------------------\n" + inputStr);
            
            // write the new string with the replaced line OVER the same file
            //FileOutputStream fileOut = new FileOutputStream(fileName);
            //fileOut.write(inputStr.getBytes());
            //fileOut.close();
            
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
