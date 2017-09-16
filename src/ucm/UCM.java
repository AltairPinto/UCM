/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author altai
 */
public class UCM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

        String AbsolutePath = new File("").getAbsolutePath() + "/src/folder/";  // Get path of file
        Scanner scanner = new Scanner(new FileReader(AbsolutePath + "memData.txt"));    // Open file

        HashMap<Integer, Integer> map = new HashMap<>();    // Memory Position => Value

        while (scanner.hasNextLine()) {     // Get values of file and put in Hashmap
            String[] lines = scanner.nextLine().split(" ");
            map.put(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
        }

        System.out.println(map);
    }
}
