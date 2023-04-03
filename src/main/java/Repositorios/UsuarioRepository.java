package Repositorios;

import JDBC.ConexionBaseDatos;
import Modelos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repository<Usuario> {
    private Connection conn;

    public UsuarioRepository() throws SQLException {
        conn = ConexionBaseDatos.getConnection();
    }
    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM usuarios")){
            while(res.next()){
                Usuario u = new Usuario();
                u.setId(res.getInt("id"));
                u.setUsername(res.getString("username"));
                u.setPssword(res.getString("pssword"));
                u.setEmail(res.getString("email"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public int actualizar(Usuario u) {
        int rowsAf;
        try(PreparedStatement st =
            conn.prepareStatement("UPDATE usuarios SET username=?,pssword=?,email=? WHERE id=?")){
            st.setString(1,u.getUsername());
            st.setString(2,u.getPssword());
            st.setString(3,u.getEmail());
            st.setInt(4,u.getId());
            rowsAf = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAf;
    }

    @Override
    public int eliminar(int id) {
        int rowsAf;
        try(PreparedStatement st = conn.prepareStatement("DELETE usuarios WHERE id=?")){
            st.setInt(1,id);
            rowsAf = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAf;
    }

    @Override
    public int agregar(Usuario u) {
        int rowsAf;
        try(PreparedStatement st = conn.prepareStatement("INSERT INTO usuarios VALUES (?,?,?)")){
            st.setString(1,u.getUsername());
            st.setString(2,u.getEmail());
            st.setString(3,u.getPssword());
            rowsAf = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAf;
    }
    public Connection getConection(){
        return this.conn;
    }
}