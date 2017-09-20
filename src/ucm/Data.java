/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altai
 */
public class Data {

    String AbsolutePath = new File("").getAbsolutePath() + "/src/folder/";  // Get path of file

    public void fileRead(String fileName, HashMap memory, ArrayList empty) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(AbsolutePath + fileName));    // Read file

        while (scanner.hasNextLine()) {     // Get values of file and put in Hashmemory
            String[] lines = scanner.nextLine().split(" ");     // Split values based in spaces in line
            if (lines[1].equals("null")) {
                lines[1] = "0";     // Memory slot empty set value 0
                empty.add(Integer.parseInt(lines[0]));      // Save empty position
            }
            memory.put(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));    // Cast to int this values
        }
    }

    public void fileRecord(String fileName, HashMap memory) throws IOException {
        File file = new File(AbsolutePath + fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter result = new FileWriter(file, false);

        memory.forEach(
                (key, value) -> {
                    try {
                        if (value.equals(0)) {
                            result.write(key + " null\n");
                        } else {
                            result.write(key + " " + value + "\n");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        result.close();
    }

    public void readCommands(String fileName, HashMap memory, ArrayList commands) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(AbsolutePath + fileName));    // Read file
        // Get operations and seachs methods
        Operations operations = new Operations();
        Search search = new Search();

        int MAR = 0;    // Memory Access Register - Specifies address for read or write OP
        int MBR = 0;    // Holds data to write or last data read
        int PC = 0;     // Holds address of next instruction to be fethed
        int cycles = 0; // Cycles of program

        String IR;          // Holds last instruction fetched
        String state = "";  // State of pipeline

        String[] INST;  // Values and registers of operation

        // Parts of instruction
        String INST_1 = null;
        String INST_2 = null;
        String INST_3 = null;

        // Address and Operators
        int address_1 = 0, address_2 = 0, address_3 = 0;
        int operator_1 = 0, operator_2 = 0, operator_dest = 0;

        // Fetch
        while (scanner.hasNextLine()) {     // Get values of file and put in ArrayList
            state = "FETCH";
            System.out.println("\nState: " + state);

            String[] line = scanner.nextLine().split(" ");     // Split command based in spaces in line
            IR = line[0];                // Get command in first position of line => Set last operation in IR      
            INST = line[1].split(",");   // Break the rest of String in 3 parts
            System.out.println("IR: " + IR);
            
            state = "DECODE";
            System.out.println("\nState: " + state);
            // Instructions after split
            try {
                INST_1 = INST[0];
                INST_2 = INST[1];
                INST_3 = INST[2];
                
                System.out.println("\nRegisters and values"
                        + "\nInstruction Register: " + IR
                        + "\nDestiny: " + INST_1
                        + "\nOperator_1: " + INST_2
                        + "\nOperator_2: " + INST_3);

                    search.getMemoryInfos(INST_1, memory);        // Set infos of IR
                    address_1 = search.getAddress();
                    if (address_1 == 0){
                        operator_dest = search.getOperator();
                        System.out.println("\nOperation Position: " + operator_dest);
                    }
                    else                
                        System.out.println("Address: " + address_1);

                    search.getMemoryInfos(INST_2, memory);        // Set infos of IR
                    address_2 = search.getAddress();
                    operator_1 = search.getOperator();
                    System.out.println("Operator_1: " + operator_1);

                    search.getMemoryInfos(INST_3, memory);        // Set infos of IR
                    address_3 = search.getAddress();
                    operator_2 = search.getOperator();
                    System.out.println("Operator_2: " + operator_2);
                    
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Non-Arithmetic Operation");
                if (IR.equals("JMP")) {
                    INST_3 = "NULL";
                    INST_2 = "NULL";

                    System.out.println("\nRegisters and values"
                            + "\nInstruction Register: " + IR
                            + "\nDestiny: " + INST_1);

                    search.getMemoryInfos(INST_1, memory);        // Set infos of IR
                    address_1 = search.getAddress();
                    operator_dest = search.getOperator();
                }
                else if(IR.equals("JMPZ") || IR.equals("MOV")){
                   INST_3 = "NULL"; 
                   System.out.println("\nRegisters and values"
                            + "\nInstruction Register: " + IR
                            + "\nDestiny: " + INST_1
                            + "\nOperator_1: " + INST_2);

                    search.getMemoryInfos(INST_1, memory);        // Set infos of IR
                    address_1 = search.getAddress();
                    operator_dest = search.getOperator();
                    
                    search.getMemoryInfos(INST_2, memory);        // Set infos of IR
                    address_2 = search.getAddress();
                    operator_1 = search.getOperator();
                }
            }
            PC++;               // Increment Program Counter to next instruction
            cycles++;           // Increment Cycles

            switch (IR) {
                case "ADD":
                    operations.ADD(address_1, operator_1, operator_2);
                    state = "EXECUTE";
                    cycles++;
                    break;
                case "SUB":
                    operations.SUB(address_1, operator_1, operator_2);
                    state = "EXECUTE";
                    cycles++;
                    break;
                case "MUL":
                    operations.MUL(address_1, operator_1, operator_2);
                    state = "EXECUTE";
                    cycles++;
                    break;
                case "DIV":
                    operations.DIV(address_1, operator_1, operator_2);
                    state = "EXECUTE";
                    cycles++;
                    break;
                case "JMP":
                    if(address_1 == 0)
                        operations.JMP(operator_dest);
                    else
                        operations.JMP(address_1);    
                    PC = operations.getPC();
                    System.out.println("\nResult of JMP: " + PC);
                    state = "FETCH";
                    cycles++;
                    break;
                case "JMPM":
                    if(address_1 == 0)
                        operations.JMPM(operator_dest, operator_1, operator_2);
                    else
                        operations.JMPM(address_1, operator_1, operator_2);
                    PC = operations.getPC();
                    System.out.println("\nResult of JMPM: " + PC);
                    state = "FETCH";
                    cycles++;
                    break;
                case "JMPL":
                    if(address_1 == 0)
                        operations.JMPL(operator_dest, operator_1, operator_2);
                    else
                        operations.JMPL(address_1, operator_1, operator_2);
                    PC = operations.getPC();
                    System.out.println("\nResult of JMPL: " + PC);
                    state = "FETCH";
                    cycles++;
                    break;
                case "JMPZ":
                    if(address_1 == 0)
                        operations.JMPZ(operator_dest, operator_1);
                    else
                        operations.JMPZ(address_1, operator_1);
                    PC = operations.getPC();
                    System.out.println("\nResult of JMPZ: " + PC);
                    state = "FETCH";
                    cycles++;
                    break;
                // Extras
                case "MOV":
                    operations.MOV(address_1, operator_1);
                    System.out.println("R"+operations.getAddress() + " new value: "+operations.getOperator());
                    state = "EXECUTE";
                    cycles++;
                default:
                    break;
            }

            if (state.equals("EXECUTE")) {
                if(IR.equals("MOV"))
                    System.out.println("\nState: " + state
                        + "\nOperation: " + IR
                        + " " + INST_1 + ", " + INST_2
                        + "\nPC: " + PC);
                else
                System.out.println("\nState: " + state
                        + "\nOperation: " + IR
                        + " " + INST_1 + ", " + INST_2 + ", " + INST_3
                        + "\nPC: " + PC);

                MAR = operations.getAddress();      // Set memory address
                MBR = operations.getOperator();     // Set result of operation
                memory.put(MAR, MBR);               // Put in HashMap of memory

                System.out.println("MAR: " + MAR
                        + "\nMBR: " + MBR);

                cycles++;
            }
        }
        state = "END";
        System.out.println("\nState: " + state
                + "\nCycles: " + cycles);
    }
}
