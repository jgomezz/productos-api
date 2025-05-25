package pe.edu.tecsup.productosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.productosapi.entities.Producto;

import java.util.List;

/**
 *
 * @author jgomez
 *
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Override
    List<Producto> findAll();

}
