package edu.cps2002.mazegame.map;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestMapSingleton {


    @Test
    public void getInstanceTest(){
        Map singleton = MapSingleton.getInstance();

     //   assertThat(StoreMap.getInstance(), singleton);
    }

}
