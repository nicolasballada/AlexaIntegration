import java.util.*;
import java.io.*;

public class AlexaIntegration{

   public static void main(String[] args){
      new AlexaIntegration();   
   }
   
   public AlexaIntegration(){
      String fileName = "AlexaControl.txt";
      String line = null;
      File file = new File(fileName);
      
      try{         
         Scanner sc = new Scanner(file);         
         line = sc.nextLine();
         if(line.equals("")){
            line = sc.nextLine();
         }
         System.out.println(line);
         sc.close();
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
        
      try {
         String command = "cmd /c start " + line;
         Process child = Runtime.getRuntime().exec(command);
         OutputStream out = child.getOutputStream();      
            out.write("cd C:/ /r/n".getBytes());
            out.flush();
            out.write("dir /r/n".getBytes());
            out.close();
      }catch(IOException e){}
      
      try{
         Runtime.getRuntime().exec("taskkill /f /im cmd.exe");  
         if(file.delete()){
            System.out.println("File deleted successfully");
         }
         else{
            System.out.println("Failed to delete the file");
         }
            
      }catch(Exception e){
         e.printStackTrace();  
      }
   }
}