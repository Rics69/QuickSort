import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileCreater {
    public static void main(String[] args) {
        try{
            File file = new File("Test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < 70; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < (int)(Math.random()* 10000)+100; j++) {
                    list.add(Double.valueOf(Math.random()*10000).intValue());
                }
                for (int j = 0; j < list.size(); j++) {
                    pw.print(list.get(j));
                    pw.print(" ");
                }
                pw.println();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}