package br.cesjf.lppo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EditaRegistro", urlPatterns = {"/edita.html"})
public class EditaRegistro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pessoa pessoa = new Pessoa();
        Long id = Long.parseLong(request.getParameter("id"));//
        
                try {
            //Pegar os dados do banco
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM reclamacao WHERE id="+id);
            if(resultado.next()){
                pessoa = new Pessoa();
                pessoa.setId(resultado.getLong("id"));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setEmail(resultado.getString("email"));
                pessoa.setDescricao(resultado.getString("descricao"));
                pessoa.setStatus(resultado.getInt("status"));
            }
            
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("pessoa", pessoa);
        request.getRequestDispatcher("WEB-INF/edita-registro.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Pessoa contato = new Pessoa();
        contato.setStatus((int) Long.parseLong(request.getParameter("status")));
        contato.setNome(request.getParameter("nome"));
        contato.setEmail(request.getParameter("email"));
        contato.setDescricao(request.getParameter("descricao"));
   //     contato.setStatus(request.getInt("status"));

        try {
            //Pegar os dados do banco
           Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("UPDATE contato SET nome='"
                    +contato.getNome()+ "', sobrenome='"
                    +contato.getEmail()+ "', telefone='"
                    +contato.getDescricao()+ "' WHERE id="
                    +contato.getStatus());
   
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    response.sendRedirect("lista.html");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
