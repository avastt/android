package br.edu.utfpr.personaltrainer;

public class Aluno {

    private String nome;
    private String email;
    private String academia;
    private String genero;

    public Aluno(String nome, String email, String academia, String genero) {
        this.nome = nome;
        this.email = email;
        this.academia = academia;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return getNome() + " - " + getEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
