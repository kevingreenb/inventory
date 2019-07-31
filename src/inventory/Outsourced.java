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
public class Outsourced extends Part {
    
    private String companyName;
            
    public Outsourced(String name, int partID, double price, int inStock, int min, int max, String companyName) {
        super(name, partID, price, inStock, min, max);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }   
}
