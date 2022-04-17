package com.elallouch.quizgame;

public class Questions {
    public String url;
    public String qst;
    public String op1;
    public String op2;
    public String ans;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setQst(String qst) {
        this.qst = qst;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getUrl() {
        return url;
    }

    public String getQst() {
        return qst;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getAns() {
        return ans;
    }
}
