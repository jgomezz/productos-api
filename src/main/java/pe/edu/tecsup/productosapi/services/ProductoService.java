package pe.edu.tecsup.productosapi.services;

import pe.edu.tecsup.productosapi.entities.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> findAll();

    Producto findById(Long id);

    void save(Producto producto);

    void deleteById(Long id);

}
