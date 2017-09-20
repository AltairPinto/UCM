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

    private int reg;
    private int operator;
    private int address;
    private int PC;

    public void ADD(int address, int reg_1, int reg_2) {
        reg = reg_1 + reg_2;
        setOperator(reg);
        setAddress(address);
    }

    public void SUB(int address, int reg_1, int reg_2) {
        reg = reg_1 - reg_2;
        setOperator(reg);
        setAddress(address);
    }

    public void MUL(int address, int reg_1, int reg_2) {
        reg = reg_1 * reg_2;
        setOperator(reg);
        setAddress(address);
    }

    public void DIV(int address, int reg_1, int reg_2) {
        reg = reg_1 / reg_2;
        setOperator(reg);
        setAddress(address);
    }

    public void JMP(int address) {
        setPC(address);
    }
    
    public void JMPM(int address, int reg_1, int reg_2) {
        setPC(reg_1 > reg_2 ? address : 0);
    }
    
    public void JMPL(int address, int reg_1, int reg_2) {
        setPC(reg_1 < reg_2 ? address : 0);
    }
    
    public void JMPZ(int address, int reg_1) {
        setPC(reg_1 == 0 ? 0 : address);
    }
    
    void MOV(int address, int reg_1) {
        reg = reg_1;
        setOperator(reg);
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

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

}
