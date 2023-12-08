package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Pedido;

public interface PedidoRepository {
    Resultado<Pedido> cadastrar(String nome, String valor, String observacao, Categoria categoria);
    Resultado<List<Pedido>> listarTodos();
}
