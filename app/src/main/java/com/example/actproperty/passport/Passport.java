package com.example.actproperty.passport;

import java.io.Serializable;

public class Passport implements Serializable {
    private int id;
    private String user, password, fullname, mobile;
    private int admin,property, noc, accountant, bdg, hcm_bch, hcm_btn, hcm_cci, hcm_q12, hcm_hmn, hcm_gvp, dni, lan, bte, tvh, kgg;

    public Passport(int id, String user, String password, String fullname, String mobile, int admin,int property, int noc, int accountant,
                    int bdg, int hcm_bch, int hcm_btn, int hcm_cci, int hcm_q12, int hcm_hmn,
                    int hcm_gvp, int dni, int lan, int bte, int tvh, int kgg) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.fullname = fullname;
        this.mobile = mobile;
        this.admin = admin;
        this.property = property;
        this.noc = noc;
        this.accountant = accountant;
        this.bdg = bdg;
        this.hcm_bch = hcm_bch;
        this.hcm_btn = hcm_btn;
        this.hcm_cci = hcm_cci;
        this.hcm_q12 = hcm_q12;
        this.hcm_hmn = hcm_hmn;
        this.hcm_gvp = hcm_gvp;
        this.dni = dni;
        this.lan = lan;
        this.bte = bte;
        this.tvh = tvh;
        this.kgg = kgg;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getNoc() {
        return noc;
    }

    public void setNoc(int noc) {
        this.noc = noc;
    }

    public int getAccountant() {
        return accountant;
    }

    public void setAccountant(int accountant) {
        this.accountant = accountant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getBdg() {
        return bdg;
    }

    public void setBdg(int bdg) {
        this.bdg = bdg;
    }

    public int getHcm_bch() {
        return hcm_bch;
    }

    public void setHcm_bch(int hcm_bch) {
        this.hcm_bch = hcm_bch;
    }

    public int getHcm_btn() {
        return hcm_btn;
    }

    public void setHcm_btn(int hcm_btn) {
        this.hcm_btn = hcm_btn;
    }

    public int getHcm_cci() {
        return hcm_cci;
    }

    public void setHcm_cci(int hcm_cci) {
        this.hcm_cci = hcm_cci;
    }

    public int getHcm_q12() {
        return hcm_q12;
    }

    public void setHcm_q12(int hcm_q12) {
        this.hcm_q12 = hcm_q12;
    }

    public int getHcm_hmn() {
        return hcm_hmn;
    }

    public void setHcm_hmn(int hcm_hmn) {
        this.hcm_hmn = hcm_hmn;
    }

    public int getHcm_gvp() {
        return hcm_gvp;
    }

    public void setHcm_gvp(int hcm_gvp) {
        this.hcm_gvp = hcm_gvp;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getLan() {
        return lan;
    }

    public void setLan(int lan) {
        this.lan = lan;
    }

    public int getBte() {
        return bte;
    }

    public void setBte(int bte) {
        this.bte = bte;
    }

    public int getTvh() {
        return tvh;
    }

    public void setTvh(int tvh) {
        this.tvh = tvh;
    }

    public int getKgg() {
        return kgg;
    }

    public void setKgg(int kgg) {
        this.kgg = kgg;
    }
}
