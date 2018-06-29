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
public class Motorista extends Funcionario {
    private String cnh;

    public Motorista(int id, String nome, String cpf, String turno, String cnh) {
        super(id, nome, cpf, turno);
        this.cnh = cnh;
    }
    
    public String getCnh(){
        return cnh;
    }
    
    public void setCnh(String cnh){
        this.cnh = cnh;
    }

}
