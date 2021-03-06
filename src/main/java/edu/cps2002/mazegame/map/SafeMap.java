package edu.cps2002.mazegame.map;

public class SafeMap extends Map{

    //access modifier set to protected to be
    //accessible by MapFactory and not by Game
    protected SafeMap(){

    }

    //set water percentage between 0% and 10%
    @Override
    public boolean setWaterPercentage(double waterPercentage) {
        //if percentage is greater than 10% or less than 0%
        if(waterPercentage < 0 || waterPercentage > 10){
            return false;
        }
        else{
            this.waterPercentage = waterPercentage;
            return true;
        }
    }
}
