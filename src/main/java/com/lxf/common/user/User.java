package com.lxf.common.user;

import java.io.Serializable;

/**
 * @author LiXiaoFeng
 * @date 2022年02月19日 15:40
 */
public class User implements Serializable {

    private int puk;

    private String name;

    private int age;

    private String idCard;

    private String fromDatabase;

    public int getPuk() {
        return puk;
    }

    public void setPuk(int puk) {
        this.puk = puk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFromDatabase() {
        return fromDatabase;
    }

    public void setFromDatabase(String fromDatabase) {
        this.fromDatabase = fromDatabase;
    }

    @Override
    public String toString() {
        return "User{" +
                "puk=" + puk +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", idCard='" + idCard + '\'' +
                ", fromDatabase='" + fromDatabase + '\'' +
                '}';
    }
}
