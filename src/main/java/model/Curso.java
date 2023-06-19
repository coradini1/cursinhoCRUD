package model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String nome;
    private String descricao;
    private int professorId;
    private List<Material> materiais;

    public Curso() {
        materiais = new ArrayList<>();
    }

    public Curso(String nome, String descricao, int professorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.professorId = professorId;
        materiais = new ArrayList<>();
    }

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

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    public void adicionarMaterial(Material material) {
        materiais.add(material);
    }

    public void removerMaterial(Material material) {
        materiais.remove(material);
    }
}
