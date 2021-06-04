package com.books.backend.apirestbooks.service.impl;
import com.books.backend.apirestbooks.model.CategoriaModel;
import com.books.backend.apirestbooks.model.dao.ICategoriaDao;
import com.books.backend.apirestbooks.response.CategoriaResponseRest;
import com.books.backend.apirestbooks.service.ICategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    /* creamos variable log para poder tener mensajes   */
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        log.info("Inicio metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            List<CategoriaModel> categoria = (List<CategoriaModel>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategoriaModel(categoria);
            response.setMetadata("Respuesta ok", "200", "Respuesta éxitosa");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){

            response.setMetadata("Respuesta no Ok  ", "-1 error", "Error al consultar categorías");
            log.error("error al consultar categorías: " + e.getMessage());
            e.getStackTrace();

        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
        log.info("Inicio método buscarPorId");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<CategoriaModel> list = new ArrayList<>();

        try {
            Optional<CategoriaModel> categoriaModel = categoriaDao.findById(id);
            if(categoriaModel.isPresent()){
                list.add(categoriaModel.get());
                response.getCategoriaResponse().setCategoriaModel(list);

            }else{
            log.error("La categoría no fue encontrada");
            response.setMetadata("Respuesta no ok ", "-1", "Categoría no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e ){
            log.error("La categoría no fue encontrada");
            response.setMetadata("Respuesta no ok ", "-1", "Error al consultar categoría");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta éxitosa");

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional()
    public ResponseEntity<CategoriaResponseRest> crearCategoria (CategoriaModel categoria) {
        log.info("Inicio método crear CategoriaModel");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List <CategoriaModel> list = new ArrayList<>();
        try {
            CategoriaModel guardarCategoria = categoriaDao.save(categoria);
            if(guardarCategoria != null){
                list.add(guardarCategoria);
                response.getCategoriaResponse().setCategoriaModel(list);
            }else{
                log.error("Error al grabar categoría");
                response.setMetadata("Respuesta no Ok", "-1", "Categoría no guardada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e ){
            log.error("Error al grabar categoría");
            response.setMetadata("Respuesta no Ok", "-1", "Error al grabar categoría");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok " , "200", "Categoría guardada");

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizar(CategoriaModel categoriaModel, Long id) {
        log.info("Inicio método actualizar");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List <CategoriaModel> list = new ArrayList<>();
        try {
            Optional<CategoriaModel> buscarCategoria = categoriaDao.findById(id);
            if(buscarCategoria.isPresent()){
                buscarCategoria.get().setNombre(categoriaModel.getNombre());
                buscarCategoria.get().setDescripción(categoriaModel.getDescripción());

                CategoriaModel categoriaActualizada = categoriaDao.save(buscarCategoria.get());
            if(categoriaActualizada != null){
                response.setMetadata("Respuesta ok ", "200", "Categoría actualizada");
                list.add(categoriaActualizada);
                response.getCategoriaResponse().setCategoriaModel(list);
            }else{
                log.error("error al actualizar Categoria");
                response.setMetadata("Respuesta no Ok", "-1", "Error al actualizar categoría");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
            }else{
                log.error("Categoria no existe");
                response.setMetadata("Respuesta no Ok", "-1", "No se puede actualizar categoría inexistente");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex){
            log.error("Error al actualizar categoria", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Respuesta no Ok", "-1", "Categoría no actualizada");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminarPorId(Long id) {
        log.info("Iniciamos método eliminar categoría por id ");
        CategoriaResponseRest response = new CategoriaResponseRest();
        try{
            categoriaDao.deleteById(id);
            response.setMetadata("Respuesta ok ", "200", "Categoría eliminada");
        }catch (Exception ex){
            log.error("Error al eliminar categoría", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Respuesta no Ok ", "-1", "Categoría no eliminada");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
}
