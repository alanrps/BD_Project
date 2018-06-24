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
public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String turno;

    public Funcionario(int id, String nome, String cpf, String turno) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.turno = turno;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCpf(){
        return cpf;
    }
    
    public void setCpfe(String cpf){
        this.cpf = cpf;
    }
    
    public String getTurno(){
        return turno;
    }
    
    public void setTurno(String turno){
        this.turno = turno;
    }
    
}
