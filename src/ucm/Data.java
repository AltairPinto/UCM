/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author altai
 */
public class Data {
    
    String AbsolutePath = new File("").getAbsolutePath() + "/src/folder/";  // Get path of file

    public void fileOpen(String fileName, HashMap memory, ArrayList empty) throws FileNotFoundException{
        Scanner scanner = new Scanner(new FileReader(AbsolutePath + fileName));    // Read file
        
        while (scanner.hasNextLine()) {     // Get values of file and put in Hashmemory
            String[] lines = scanner.nextLine().split(" ");     // Split values based in spaces in line
            if (lines[1].equals("null")){
                lines[1] = "0";     // Memory slot empty set value 0
                empty.add(Integer.parseInt(lines[0]));      // Save empty position
            }
            memory.put(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));    // Cast to int this values
        }
    }
    
    public void fileRecord(String fileName, HashMap memory) throws IOException{
        File file = new File(AbsolutePath + fileName);
        
        if(!file.exists()){
            file.createNewFile();
         }
        /*ObjectOutputStream obj;
        try (OutputStream out = new FileOutputStream(file)) {
            obj = new ObjectOutputStream(out);
            obj.writeObject(memory);
        }
        obj.close();*/
        FileWriter result = new FileWriter(file, false);
        for(int i=0; i<memory.size();i++){
            if(memory.get(i) == "0")    result.write(i + " null\n");
            else    result.write(i + " " + memory.get(i)+"\n");
        }
        result.close();
    }
}
