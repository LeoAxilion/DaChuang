package com.example.xinyuxin.Entity;

import com.example.xinyuxin.R;

public class MessageInfo {
    public int img = R.drawable.courselogo1;
    private  Integer id ;
    private String tittle ;
    private String  abstracts ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
}
