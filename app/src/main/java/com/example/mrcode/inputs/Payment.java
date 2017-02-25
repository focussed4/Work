package com.example.mrcode.inputs;

/**
 * Created by MrCode on 24/02/2017.
 */

public class Payment {
    private int id;
    private String name;
    private int amount;

    // class used to get the Payment either outgoing in income
    public Payment(){

    }

    public Payment(int id, String name, int amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getAmount(){
        return this.amount;
    }

}
