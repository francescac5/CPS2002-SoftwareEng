package edu.cps2002.mazegame.map;

public class HazardousMap extends Map{

    //access modifier set to protected to be
    //accessible by MapFactory and not by Game
    protected HazardousMap(){

    }

    //set water percentage between 25% and 35%
    @Override
    public boolean setWaterPercentage(double waterPercentage) {
        //if percentage is not between 25% and 35%
        if(waterPercentage < 25 || waterPercentage > 35){
            return false;
        }
        else{
            this.waterPercentage = waterPercentage;
            return true;
        }
    }
}
