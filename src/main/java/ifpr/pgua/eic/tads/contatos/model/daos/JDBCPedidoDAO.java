package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.Pedido;

public class JDBCPedidoDAO implements PedidoDAO {
    private FabricaConexoes fabricaConexao;

    public JDBCPedidoDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Resultado<Pedido> criar(Pedido pedido) {
        try (Connection con = fabricaConexao.getConnection();) {

            PreparedStatement pstm = con.prepareStatement("INSERT INTO oo_pedidos(nome, valor, observacao) VALUES (?,?,?)");

            pstm.setString(1, pedido.getNome());
            pstm.setString(2, pedido.getValor());
            pstm.setString(3, pedido.getObservacao());
            pstm.setInt(4, pedido.getCategoria().getId());

            pstm.executeUpdate();

            return Resultado.sucesso("Pedido cadastrado!", pedido);
        } catch (SQLException e) {
            return Resultado.erro("Problema ao conectar " + e.getMessage());
        }
    }

    @Override
    public Resultado<List<Pedido>> listar() {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try (Connection con = fabricaConexao.getConnection();) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_pedidos");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String valor = rs.getString("valor");
                String observacao = rs.getString("observacao");

                Pedido pedido = new Pedido(id, nome, valor, observacao);

                pedidos.add(pedido);
            }
            con.close();
            return Resultado.sucesso("Pedidos carregados", pedidos);
        } catch (SQLException e) {
            return Resultado.erro("Problemas ao fazer seleção!! " + e.getMessage());
        }

    }
    
}
