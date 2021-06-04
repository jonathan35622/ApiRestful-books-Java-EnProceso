package com.books.backend.apirestbooks.service;


import com.books.backend.apirestbooks.model.CategoriaModel;
import com.books.backend.apirestbooks.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity <CategoriaResponseRest> buscarCategorias();
    public ResponseEntity <CategoriaResponseRest> buscarPorId(Long id);
    public ResponseEntity <CategoriaResponseRest> crearCategoria(CategoriaModel categoria);
    public ResponseEntity <CategoriaResponseRest> actualizar(CategoriaModel categoriaModel, Long id );
    public ResponseEntity <CategoriaResponseRest> eliminarPorId(Long id);
    /*   Este método va a devolver una CategoriaResponseRest, a su vez esta devuelve  una CategoriaResponse
    * y esta una lista de categorias    */

}
