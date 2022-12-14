
/**
 * Take txt file input of names and replaces names of files with those from a list of random order.
 *
 * @author Gazel
 * @version 5/15/19
 */
import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class Randomize
{
    public Randomize(){}
    
    public static void main(String[] args) throws IOException
    {
        //Put File Names into String array such as "oak_planks.json"
        File recipesFolder = new File("E:\\Minecraft - Anything and Everything\\Testing\\Recipe Randomization\\New Randomized\\data\\minecraft\\recipes");
        File[] recipes = recipesFolder.listFiles();
        String[] recipeFileNames = new String[recipes.length];
        for(int i = 0; i < recipes.length; i++)
            recipeFileNames[i] = recipes[i].getName();
        
        //Preparing of RecipeList.txt file    
        File listFile = new File("RecipeList.txt");
        Scanner inFile = new Scanner(listFile);
        
        //Counting # of lines in file
        int lineCount = 0;
        while (inFile.hasNextLine())
        {
            lineCount++;
            inFile.nextLine();
        }
        
        //Setting up array of size = to # of lines and resetting line # on file scanner
        String[] items = new String[lineCount];
        inFile = new Scanner(listFile);
        
        //Porting item names from RecipeList.txt into "items" String array
        for(int i = 0; i < items.length && inFile.hasNextLine(); i++)
        {
            items[i] = inFile.nextLine();
        }
        inFile.close();
        
        //Setup for loop and random number generation
        int counter = 0;
        int successNum = 0;
        Random rNumGen = new Random();
        
        //Randomly swapping current index from one iteration of the array with that of a random index in the array
        //Randomizing order of "recipeFileNames" String array with one iteration through array
        for(int i = 0; i < recipeFileNames.length; i++)
        {
            String temp = recipeFileNames[i]; //Saving current index's String to temporary variable
            int rNum = rNumGen.nextInt(recipeFileNames.length); //Generating random integer to be used as an index
            recipeFileNames[i] = recipeFileNames[rNum]; //Setting current index's String to that of the random index's String
            recipeFileNames[rNum] = temp; //Setting the random index's String to that of the original current index's String
        }
        
        int iC = 0; // iC stands for Iteration Counter
        //Iterating through all names from RecipeList (list of items names that can be crafted in some way)
        for(; iC < items.length; iC++)
        {
            //int rNum = rNumGen.nextInt(recipeFileNames.length);
            successNum = replaceSelected("E:\\Minecraft - Anything and Everything\\Testing\\Recipe Randomization\\New Randomized\\data\\minecraft\\recipes\\" + recipeFileNames[iC], items[iC]);
            counter++;
            if(successNum < 0)
                System.out.println("Skipped \"" + recipeFileNames[iC] + "\" at counter = " + counter + ".");
        }
        
        //Iterating through the rest of the files and giving them a random item for their recipe to create
        for(; iC < recipeFileNames.length; iC++)
        {
            int rNum = rNumGen.nextInt(items.length);
            successNum = replaceSelected("E:\\Minecraft - Anything and Everything\\Testing\\Recipe Randomization\\New Randomized\\data\\minecraft\\recipes\\" + recipeFileNames[iC], items[rNum]);
            counter++;
            if(successNum < 0)
                System.out.println("Skipped \"" + recipeFileNames[iC] + "\" at counter = " + counter + ".");
        }
        
        System.exit(0);
    }
    
    public static int replaceSelected(String fileName, String replaceWith) throws IOException {
        try {
            int successNum = 1;
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(fileName));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            
            //System.out.println(inputStr); // display the original file for debugging
            
            // logic to replace lines in the string (could use regex here to be generic)
            String searchStart = "  \"result\": {\n    \"item\": \""; //length = 27
            String searchEnd = "\""; //length = 1
            
            String searchStartAlt = "  \"result\": \""; //length = 13
            if(inputStr.indexOf(searchStart) > -1){
                inputStr = 
                    inputStr.replace(
                    inputStr.substring(
                        inputStr.indexOf(searchStart) + 27,
                        inputStr.indexOf(searchEnd, inputStr.indexOf(searchStart) + 27 + 1)),
                        replaceWith)
                        ;
            }
            else if(inputStr.indexOf(searchStartAlt) > -1)
            {
                inputStr = 
                    inputStr.replace(
                    inputStr.substring(
                        inputStr.indexOf(searchStartAlt) + 13,
                        inputStr.indexOf(searchEnd, inputStr.indexOf(searchStartAlt) + 13 + 1)),
                        replaceWith)
                        ;
            }
            else
                successNum = -1;
            
            // display the new file for debugging
            //System.out.println("----------------------------------\n" + inputStr);
            
            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            
            return successNum;
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
        return -1;
    }
}
