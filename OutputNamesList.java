
/**
 * Reads in names and then outputs names to new file.
 *
 * @author Gazel + Online Sources
 * @version 5/13/19
 */
import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class OutputNamesList
{
    public OutputNamesList(){}

    public static void main(String[] args) throws IOException
    {
        String token = "";
        String prevToken = "";
        String searchPhrase = "  \"result\": {";
        File recipesFolder;
            
        if(args.length == 0){
            recipesFolder = new File("E:\\Minecraft - Anything and Everything\\Testing\\Recipe Randomization\\New Randomized\\data\\minecraft\\recipes");
        }
        else{
            recipesFolder = new File(args[0]);
        }
        File[] recipes = recipesFolder.listFiles();
        PrintWriter outFile = new PrintWriter(new File("RecipeList.txt"));

        for(File item : recipes)
        {
            Scanner inFile = new Scanner(item);
            while(inFile.hasNextLine())
            {
                token = inFile.nextLine();
                if(token.equals(searchPhrase))
                {
                    token = inFile.nextLine();
                    //edit token to only be wanted text
                    token = token.trim().substring(9, token.trim().indexOf("\"", 9));
                    if(!prevToken.equals(token)){
                        outFile.println(token); //Write to File with line break
                        //System.out.println(token); //For Debug
                    }
                    //System.out.println(prevToken + ".equals(" + token + ") is " + prevToken.equals(token) + ".");
                    prevToken = token;
                    break;
                }
            }
        }
        
        outFile.close();
        System.exit(0);
        
        //Random rNum = new Random();
        /*    
        for (int i = 0; i < recipeNames.length; i++)
        {
            int randomPos = rNum.nextInt(recipeNames.length);
            File temp = recipeNames[i];
            recipeNames[i] = recipeNames[randomPos];
            recipeNames[randomPos] = temp;
        }
        */
        /*
        for (int i = 0; i < recipeNames.length-1; i++)
        {
            if(i == recipeNames.length-1)
                recipeNames[i].renameTo(new File(recipeNames[0].getName()));
            else
            System.out.println(recipeNames[i].renameTo(recipeNames[i+1]));
            //System.out.println(recipeNames[i].renameTo(new File(recipeNames[i+1].getName())));
        }
        */
        /*
        System.out.println(recipeNames[0].renameTo(recipeNames[1]));
        for(File item : recipeNames)
            System.out.println(item.getName());
        */

    }
}
