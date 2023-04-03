package Repositorios;

import JDBC.ConexionBaseDatos;
import Modelos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repository<Usuario> {
    private Connection conn;
    private int correlativo;

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
    public Boolean actualizar(Usuario u) {
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
        return rowsAf>0;
    }

    @Override
    public Boolean eliminar(int id) {
        int rowsAf;
        try(PreparedStatement st = conn.prepareStatement("DELETE usuarios WHERE id=?")){
            st.setInt(1,id);
            rowsAf = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAf>0;
    }

    @Override
    public Boolean agregar(Usuario u) {
        int rowsAf;
        try(PreparedStatement st = conn.prepareStatement("INSERT INTO usuarios VALUES (?,?,?,?)")){
            st.setInt(1,++correlativo);
            st.setString(2,u.getUsername());
            st.setString(3,u.getPssword());
            st.setString(4,u.getEmail());
            rowsAf = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAf>0;
    }
    public Connection getConection(){
        return this.conn;
    }
}