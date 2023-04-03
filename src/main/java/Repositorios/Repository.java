package Repositorios;
import java.util.List;
public interface Repository<T> {
    List<T> listar();
    Boolean actualizar(T u);
    Boolean eliminar(int id);
    Boolean agregar(T u);
}