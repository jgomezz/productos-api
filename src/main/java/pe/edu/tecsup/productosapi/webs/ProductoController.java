package pe.edu.tecsup.productosapi.webs;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.tecsup.productosapi.entities.Producto;
import pe.edu.tecsup.productosapi.services.ProductoService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
public class ProductoController {

    @Value("${app.storage.path}")
    private String STORAGEPATH;

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     *  READ --> GET
     * @return
     */
    @GetMapping("/productos")
    public List<Producto> productos() {

        List<Producto> productos = productoService.findAll();

        return productos;
    }


    @GetMapping("/productos/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws Exception {

        log.info("call images: " + filename);
        Path path = Paths.get(STORAGEPATH).resolve(filename);

        log.info("Path: " + path);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build(); //
        }

        Resource resource = new UrlResource(path.toUri());

        log.info("Resource: " + resource);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename='" + resource.getFilename() + "'")
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(STORAGEPATH).resolve(filename)))
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength())).body(resource);
    }
}
