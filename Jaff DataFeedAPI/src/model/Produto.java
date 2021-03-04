/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jhoel
 */
public class Produto {
    private String simbolo;
    private double ult;
    private double porcent;
    private double neg;
    private double pex;
    private String dtVc;
    private double qtdC;
    private double qtdV;
    private double venda;
    private char tpOpcao;
    private String opRef;

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getUlt() {
        return ult;
    }

    public void setUlt(double ult) {
        this.ult = ult;
    }

    public double getPorcent() {
        return porcent;
    }

    public void setPorcent(double porcent) {
        this.porcent = porcent;
    }

    public double getNeg() {
        return neg;
    }

    public void setNeg(double neg) {
        this.neg = neg;
    }

    public double getPex() {
        return pex;
    }

    public void setPex(double pex) {
        this.pex = pex;
    }

    public String getDtVc() {
        return dtVc;
    }

    public void setDtVc(String dtVc) {
        this.dtVc = dtVc;
    }

    public double getQtdC() {
        return qtdC;
    }

    public void setQtdC(double qtdC) {
        this.qtdC = qtdC;
    }

    public double getQtdV() {
        return qtdV;
    }

    public void setQtdV(double qtdV) {
        this.qtdV = qtdV;
    }

    public double getVenda() {
        return venda;
    }

    public void setVenda(double venda) {
        this.venda = venda;
    }

    public char getTpOpcao() {
        return tpOpcao;
    }

    public void setTpOpcao(char tpOpcao) {
        this.tpOpcao = tpOpcao;
    }

    public String getOpRef() {
        return opRef;
    }

    public void setOpRef(String opRef) {
        this.opRef = opRef;
    }
    
}
