package Repositorios;
import java.util.List;
public interface Repository<T> {
    List<T> listar();
    int actualizar(T u);
    int eliminar(int id);
    int agregar(T u);
}