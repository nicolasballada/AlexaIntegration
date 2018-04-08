import java.util.*;
import java.io.*;

public class AlexaIntegration{
   
   private String fileName = "AlexaControl.txt";
   private File file = new File(fileName);
   
   public static void main(String[] args){
      new AlexaIntegration();   
   }
   
   public AlexaIntegration(){
   
      IndThread t1 = new IndThread(fileName, file);
         t1.start();
         try{
            t1.join();
         } catch(Exception e){}
   }
   
   class IndThread extends Thread{
      private ArrayList<String> lineArr = new ArrayList<String>();
      private String lastLine = null;
      private String fileName;
      private File file;
      private boolean notEmpty = false;
      private int ln = 1;
      
      public IndThread(String _fileName, File _file){
         fileName = _fileName;
         file = _file;
      }
      
      public void run(){
         try{                   
            Scanner sc = new Scanner(file);         
            while(sc.nextLine() != null){
               notEmpty = true;
    	        	ln++;
            }
            sc.close();
         }
         catch(FileNotFoundException e){}
         catch(NoSuchElementException nsee){}
         
         try{
            Scanner in = new Scanner(file);
            for(int i = 0; i < ln; i++){
               lineArr.add(in.nextLine());
            }            
            in.close();
         }
         catch(FileNotFoundException e){}
         catch(NoSuchElementException nsee){}
         
         try {
            if(notEmpty){
               lastLine = lineArr.get(lineArr.size()-1);
               System.out.println(lastLine);
            }
            
            String command = "cmd /c start " + lastLine;
            Process child = Runtime.getRuntime().exec(command);
            OutputStream out = child.getOutputStream();      
               out.write("cd C:/ /r/n".getBytes());
               out.flush();
               out.write("dir /r/n".getBytes());
               out.close();
         }catch(IOException e){}
                     
         try{
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");  
         }catch(Exception e){}
      }
   }
}