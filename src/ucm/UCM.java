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
public class UCM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        HashMap<Integer, Integer> memory = new HashMap<>();    // Memory Position => Value
        ArrayList<Integer> empty = new ArrayList<>();     // List of empty memory spaces
        
        Data data = new Data();     // Object to get file data
        data.fileOpen("memData.txt", memory, empty);   // Get values of memData.txt
        
        System.out.println("Memory HashMap: " + memory);
        System.out.println("Empty memory slots: " + empty);

        // Recorders to save values of operations
        int result_ADD;
        int result_SUB;
        int result_MUL;
        int result_DIV;

        Operations operations = new Operations();   // Operations object to use methods

        result_ADD = operations.ADD(memory.get(0), memory.get(1));    // ADD using values of positions 0 and 1
        result_SUB = operations.SUB(memory.get(2), memory.get(3));    // SUB using values of positions 2 and 3
        result_MUL = operations.MUL(memory.get(4), memory.get(5));    // MUL using values of positions 4 and 5
        result_DIV = operations.DIV(memory.get(6), memory.get(7));    // DIV using values of positions 6 and 7

        System.out.println("add $result_ADD, $s0, $s1 : " + result_ADD
                        + "\nsub $result_SUB, $s2, $s3 : " + result_SUB
                        + "\nmul $result_MUL, $s4, $s5 : " + result_MUL
                        + "\ndiv $result_DIV, $s6, $s7 : " + result_DIV);
        
        // Set values in other positions of Memory
        memory.put(empty.get(5),result_ADD);
        memory.put(empty.get(7),result_SUB);
        memory.put(empty.get(9),result_MUL);
        memory.put(empty.get(11),result_DIV);

        System.out.println(memory);
        
        // Save results in other file for tests
        data.fileRecord("memResult.txt", memory);
    }
}
