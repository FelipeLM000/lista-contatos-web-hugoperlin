package ifpr.pgua.eic.tads.contatos.model;

public class Pedido {
    private int id;
    private String nome;
    private float valor;
    private String observacao;
    private Categoria categoria;

    public Pedido(int id, String nome, float valor, String observacao) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Pedido(String nome, float valor, String observacao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.observacao = observacao;
        this.categoria = categoria;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return "Pedido [id = " + id + ", nome = " + nome + ", valor = " + valor + ", observação = " + observacao + "]";
    }
}
