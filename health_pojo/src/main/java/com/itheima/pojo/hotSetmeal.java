package com.itheima.pojo;

import java.io.Serializable;

public class hotSetmeal implements Serializable {
    private String name;
    private int setmeal_count;
    private double proportion;

    @Override
    public String toString() {
        return "hotSetmeal{" +
                "name='" + name + '\'' +
                ", setmeal_count=" + setmeal_count +
                ", proportion=" + proportion +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSetmeal_count() {
        return setmeal_count;
    }

    public void setSetmeal_count(int setmeal_count) {
        this.setmeal_count = setmeal_count;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }
}
