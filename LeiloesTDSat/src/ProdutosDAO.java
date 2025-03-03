/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
 
        conn = new conectaDAO().connectDB();
        PreparedStatement st;
        try {
            
            st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            st.setString(1,produto.getNome());
            st.setInt(2,produto.getValor());
            st.setString(3,produto.getStatus());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conn = new conectaDAO().connectDB();
        try {
            String sql = "SELECT * FROM produtos";  
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();  

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));  
                produto.setNome(resultset.getString("nome"));  
                produto.setValor(resultset.getInt("valor"));  
                produto.setStatus(resultset.getString("status"));  

                listagem.add(produto);  
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        } try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
        return listagem;
    }
    
    
    
        
}

