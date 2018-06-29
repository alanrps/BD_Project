/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lucas
 */
public class Ponto {
    private int id;
    private Rua rua;
    private Bairro bairro;

    public int getId() {
        return id;
    }

    public Rua getRua() {
        return rua;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRua(Rua rua) {
        this.rua = rua;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    } 
    
}
