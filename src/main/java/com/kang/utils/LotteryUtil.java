package com.kang.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryUtil {

    private int chance1;
    private int chance2;
    private int chance3;
    private int chance4;
    private int chance5;

    private List<Double> map;

    public LotteryUtil(int chance1, int chance2, int chance3, int chance4, int chance5){
        this.chance1=chance1;
        this.chance2=chance2;
        this.chance3=chance3;
        this.chance4=chance4;
        this.chance5=chance5;
        map=new ArrayList<>(5);
        int sum=chance1+chance2+chance3+chance4+chance5;
        double tempSum=0d;
        tempSum+=this.chance1*1.0/sum;
        map.add(tempSum);
        tempSum+=this.chance2*1.0/sum;
        map.add(tempSum);
        tempSum+=this.chance3*1.0/sum;
        map.add(tempSum);
        tempSum+=this.chance4*1.0/sum;
        map.add(tempSum);
        tempSum+=this.chance5*1.0/sum;
        map.add(tempSum);
    }

    public int generate(){
        List<Double> tmp=new ArrayList<>(6);
        tmp.addAll(this.map);
        double nextDouble = Math.random();
        tmp.add(nextDouble);
        Collections.sort(tmp);
        return tmp.indexOf(nextDouble);
    }
}
