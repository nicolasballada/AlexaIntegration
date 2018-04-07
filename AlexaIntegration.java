import java.util.*;
import java.io.*;

public class AlexaIntegration{

   public static void main(String[] args){
      new AlexaIntegration();   
   }
   
   public AlexaIntegration(){
      // The name of the file to open.
        String fileName = "AlexaControl.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            line = bufferedReader.readLine(); 

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
  
      try {
         // Execute command
         String command = "cmd /c start " + line;
         Process child = Runtime.getRuntime().exec(command);

         // Get output stream to write from it
         OutputStream out = child.getOutputStream();
      
         out.write("cd C:/ /r/n".getBytes());
         out.flush();
         out.write("dir /r/n".getBytes());
         out.close();
      } catch (IOException e) {
      }
   
   }



}