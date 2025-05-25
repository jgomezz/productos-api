package pe.edu.tecsup.productosapi.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.productosapi.entities.Producto;
import pe.edu.tecsup.productosapi.repositories.ProductoRepository;

import java.util.List;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;

    ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe registro"));
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

}
