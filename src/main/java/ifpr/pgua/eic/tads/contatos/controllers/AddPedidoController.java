package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Categoria;
import ifpr.pgua.eic.tads.contatos.model.Pedido;
import ifpr.pgua.eic.tads.contatos.model.repositories.CategoriaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.PedidoRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddPedidoController {
    
    private PedidoRepository repositorio;
    private CategoriaRepository repositorioCategoria;

    public AddPedidoController(PedidoRepository repositorio, CategoriaRepository repositorioCategoria){
        this.repositorio = repositorio;
        this.repositorioCategoria = repositorioCategoria;
    }

    public Handler get = (Context ctx)->{
        Map<String, Object> model = new HashMap<>();

        Resultado<List<Categoria>> resultado = repositorioCategoria.listarTodos();
        
        if(resultado.foiSucesso()){
            model.put("categorias", resultado.comoSucesso().getObj());

        }

        ctx.render("templates/addPedido.peb",model);
    };

    public Handler post = (Context ctx)->{
        String nome = ctx.formParam("nome");
        String valor = ctx.formParam("valor");
        String observacao = ctx.formParam("observacao");

        String categoriaId = ctx.formParam("categoria");

        Resultado<Categoria> resultadoCategoria = repositorioCategoria.getById(Integer.valueOf(categoriaId));

        Categoria categoria = resultadoCategoria.comoSucesso().getObj();
        Resultado<Pedido> resultado = repositorio.cadastrar(nome, valor, observacao, categoria);
        
        Map<String, Object> model = new HashMap<>();
        model.put("resultado", resultado);
        if(resultado.foiErro()){
            model.put("nome", nome);
            model.put("valor", get);
            model.put("observacao", observacao);
            model.put("categoriaId", Integer.valueOf(categoriaId));
            Resultado<List<Categoria>> ret = repositorioCategoria.listarTodos();
            model.put("categorias", ret.comoSucesso().getObj());
        }

        ctx.render("templates/addPedido.peb",model);
    };
}
