/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucm;

import java.util.HashMap;

/**
 *
 * @author altai
 */
public class Search {

    private int address;
    private int operator;

    /*public void getDestinyInfos(String INST, HashMap memory) {
        // Break the String to find address and value
        char[] op = INST.toCharArray();

        if (op[0] == 'R') {          // Recorder detected
            switch (op.length) {
                case 2:
                    System.out.println(op[1]);
                    this.setAddress(Integer.parseInt("" + op[1]));  // Set in address the key of memory position
                    break;
                case 3:
                    this.setAddress(Integer.parseInt((int) op[1] + "" + (int) op[2]));  // Set in address the key of memory position
                    break;
            }
        } else {        // Value detected
            this.setOperator(Integer.parseInt(INST));
        }
    }*/

    public void getMemoryInfos(String INST, HashMap memory) {
        // Break the String to find address and value
        char[] op = INST.toCharArray();

        switch (op.length) {
            case 1:
                this.setAddress(0);
                this.setOperator(Integer.parseInt(INST));
                break;
            case 2:
                switch (op[0]) {
                    case 'R':
                        this.setAddress(Integer.parseInt("" + op[1]));  // Set in address the key of memory position
                        this.setOperator(Integer.parseInt("" + memory.get(address)));   // Set key value in operator
                        break;
                    case '<':
                        this.setAddress(Integer.parseInt("" + op[1]));  // Set in address the key of memory position
                        this.setOperator(Integer.parseInt("" + memory.get(address)));   // Set key value in operator
                        break;
                    case 'x':
                        this.setAddress(0);
                        this.setOperator(0);
                        break;
                    default:
                        this.setAddress(0);
                        this.setOperator(Integer.parseInt(INST));
                        break;
                }
                break;
            case 3:
                switch (op[0]) {
                    case 'R':
                        this.setAddress(Integer.parseInt((int) op[1] + "" + (int) op[2]));  // Set in address the key of memory position
                        this.setOperator(Integer.parseInt("" + memory.get(address)));   // Set key value in operator
                        break;
                    case '<':
                        this.setAddress(Integer.parseInt((int) op[1] + "" + (int) op[2]));  // Set in address the key of memory position
                        this.setOperator(Integer.parseInt("" + memory.get(address)));   // Set key value in operator
                        break;
                    case 'x':
                        this.setAddress(0);
                        this.setOperator(0);
                        break;
                    default:
                        this.setAddress(0);
                        this.setOperator(Integer.parseInt(INST));
                        break;
                }
                break;
            default:
                break;
        }

    }

    public int getValue(HashMap memory, int address, int value) {

        return value;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

}
