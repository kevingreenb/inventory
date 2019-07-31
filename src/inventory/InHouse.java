/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author kevingreen
 */
public class InHouse extends Part {
    
    private int machineID;

    public InHouse(String name, int partID, double price, int inStock, int min, int max, int machineID) {
        super(name, partID, price, inStock, min, max);
        this.machineID = machineID;
    }
    public void setMachineID(int machineID){
        this.machineID = machineID;
    }    
    public int getMachineID(){
        return this.machineID;
    }
}
