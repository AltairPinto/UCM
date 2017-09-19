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
public class Operations {
    
    private int[] reg = new int[2];
    private int operator;
    private int address;
    
    public void ADD(int address, int reg_1, int reg_2){
        reg[0] = reg_1 + reg_2;
        setOperator(Integer.parseInt(""+(int)reg[0]));
        setAddress(address);
    }
    
    public void SUB(int address, int reg_1, int reg_2){
        reg[0] = reg_1 - reg_2;
        setOperator(Integer.parseInt(""+(int)reg[0]));
        setAddress(address);
    }
    
    public void MUL(int address, int reg_1, int reg_2){
        reg[0] = reg_1 * reg_2;
        setOperator(Integer.parseInt(""+(int)reg[0]));
        setAddress(address);
    }
    
    public void DIV(int address, int reg_1, int reg_2){
        reg[0] = reg_1 / reg_2;
        setOperator(Integer.parseInt(""+(int)reg[0]));
        setAddress(address);
    }
    
     public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
    
}
