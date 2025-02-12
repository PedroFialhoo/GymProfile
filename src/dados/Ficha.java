/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author Pedro
 */
public class Ficha {
    
    private String nome_ficha, exercicios, peso, series;

    public Ficha(String nome_ficha, String exercicios, String peso, String series) {
        this.nome_ficha = nome_ficha;
        this.exercicios = exercicios;
        this.peso = peso;
        this.series = series;
    }

    public String getNome_ficha() {
        return nome_ficha;
    }

    public void setNome_ficha(String nome_ficha) {
        this.nome_ficha = nome_ficha;
    }

    public String getExercicios() {
        return exercicios;
    }

    public void setExercicios(String exercicios) {
        this.exercicios = exercicios;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    
    
    
}
