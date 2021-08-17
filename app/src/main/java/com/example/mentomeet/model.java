package com.example.mentomeet;

public class model {
    String  SNAME,MENAME,FNAME,MNAME,SCONTACT,SEMAIL,PCONTACT,PEMAIL,SROLL;

    model()
    {

    }

    public model(String SNAME, String MENAME, String FNAME, String MNAME, String SCONTACT, String SEMAIL, String PCONTACT, String PEMAIL, String SROLL) {
        this.SNAME = SNAME;
        this.MENAME = MENAME;
        this.FNAME = FNAME;
        this.MNAME = MNAME;
        this.SCONTACT = SCONTACT;
        this.SEMAIL = SEMAIL;
        this.PCONTACT = PCONTACT;
        this.PEMAIL = PEMAIL;
        this.SROLL = SROLL;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getMENAME() {
        return MENAME;
    }

    public void setMENAME(String MENAME) {
        this.MENAME = MENAME;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getMNAME() {
        return MNAME;
    }

    public void setMNAME(String MNAME) {
        this.MNAME = MNAME;
    }

    public String getSCONTACT() {
        return SCONTACT;
    }

    public void setSCONTACT(String SCONTACT) {
        this.SCONTACT = SCONTACT;
    }

    public String getSEMAIL() {
        return SEMAIL;
    }

    public void setSEMAIL(String SEMAIL) {
        this.SEMAIL = SEMAIL;
    }

    public String getPCONTACT() {
        return PCONTACT;
    }

    public void setPCONTACT(String PCONTACT) {
        this.PCONTACT = PCONTACT;
    }

    public String getPEMAIL() {
        return PEMAIL;
    }

    public void setPEMAIL(String PEMAIL) {
        this.PEMAIL = PEMAIL;
    }

    public String getSROLL() {
        return SROLL;
    }

    public void setSROLL(String SROLL) {
        this.SROLL = SROLL;
    }
}
