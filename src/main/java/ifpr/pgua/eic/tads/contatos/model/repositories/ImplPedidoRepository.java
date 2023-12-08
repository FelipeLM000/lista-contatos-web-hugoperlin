package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Pedido;
import ifpr.pgua.eic.tads.contatos.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.PedidoDAO;

public class ImplPedidoRepository implements PedidoRepository{
    private PedidoDAO dao;
    private CategoriaDAO categoriaDao;

    public ImplPedidoRepository(PedidoDAO dao, CategoriaDAO categoriaDao) {
        this.dao = dao;
        this.categoriaDao = categoriaDao;
    }

    @Override
    public Resultado<Pedido> cadastrar(String nome, String valor, String observacao, Categoria categoria) {
        if(nome.isBlank() || nome.isEmpty()) {
            return Resultado.erro("Nome inválido!");
        }

        Pedido pedido = new Pedido(nome, valor, observacao, categoria);
        return dao.criar(pedido);
    }

    @Override
    public Resultado<List<Pedido>> listarTodos() {
        Resultado<List<Pedido>> resultado = dao.listar();

        if(resultado.foiSucesso()){
            List<Pedido> lista = resultado.comoSucesso().getObj();
            for(Pedido p:lista){
                Resultado<Categoria> res2 = categoriaDao.buscarCategoriaPedido(p);
                if(res2.foiErro()){
                    return res2.comoErro();
                }else{
                    p.setCategoria(res2.comoSucesso().getObj());
                }
            }
        }
        return resultado;
    }
}
