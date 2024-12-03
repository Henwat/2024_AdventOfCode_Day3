import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

public class Day3Solver {

    private String memoryString;

    Day3Solver(){

    }
    
    public static void main(String[] args) throws Exception {
       Day3Solver day3Solver = new Day3Solver();    
       day3Solver.setMemoryString("resources/input.txt"); 
       
       String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
       System.out.println(day3Solver.getProduct(regex, true));
       
    }

    public void setMemoryString (String fileName) {
        try {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        this.memoryString = "";
        String line;
        
        while((line = bufferedReader.readLine()) != null) {
            this.memoryString += line;
        }

        bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMemoryString(){
        return this.memoryString;
    }

    public int getProduct(String regexPattern, boolean enabled){
        if (enabled == true){
            regexPattern += "|do\\(\\)|don't\\(\\)";
        }
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(this.memoryString);
        ArrayList<String> hits = new ArrayList<>();
      
        while(matcher.find()){
            hits.add(matcher.group());

        }
        
        Pattern newPattern = Pattern.compile("\\d{1,3}");
        Matcher newMatcher;
        int total = 0;

        ArrayList<Integer> factors = new ArrayList<>();
        String prev = "";
        
        for (String hit : hits){
            if(hit.equals("do()")){
                prev = "do()";
                continue; 
            } else if(hit.equals("don't()")){
                prev = "don't()";
                continue;
            }
            if(prev.equals("don't()")){
                continue;
            }

            

            factors.clear();
            newMatcher = newPattern.matcher(hit); 
        
            while(newMatcher.find()){
                factors.add(Integer.parseInt(newMatcher.group()));
            }
            
            if (factors.size() > 0){
                total += factors.get(0)*factors.get(1); 
            }
        }
      
      return total;
  }
}
