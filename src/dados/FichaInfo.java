package dados;

public class FichaInfo {
    private String nomeEcontrado;
    private String exercicios;
    private String peso;
    private String series;
    private int id_ficha;

    public FichaInfo(String nomeEcontrado, String exercicios, String peso, String series) {
        this.nomeEcontrado = nomeEcontrado;
        this.exercicios = exercicios;
        this.peso = peso;
        this.series = series;
    }

    public FichaInfo(int id_ficha) {
        this.id_ficha = id_ficha;
    }

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }
    

    @Override
    public String toString() {
        return  "Nome Ficha" + nomeEcontrado +", Exercícios: " + exercicios + ", Peso: " + peso + ", Séries: " + series;
    }

    public String getNomeEcontrado() {
        return nomeEcontrado;
    }

    public void setNomeEcontrado(String nomeEcontrado) {
        this.nomeEcontrado = nomeEcontrado;
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
