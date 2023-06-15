package model;

public class Curso {
    private int id;
    private String nome;
    private String descricao;
    private int professorId;

    // Constructors
    public Curso() {
    }

    public Curso(String nome, String descricao, int professorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.professorId = professorId;
    }

    public Curso(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
