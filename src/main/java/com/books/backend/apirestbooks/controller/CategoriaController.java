package com.books.backend.apirestbooks.controller;

import com.books.backend.apirestbooks.model.CategoriaModel;
import com.books.backend.apirestbooks.response.CategoriaResponseRest;
import com.books.backend.apirestbooks.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoriaController {
    @Autowired
    private ICategoriaService iCategoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultaCategoria(){

        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.buscarCategorias();
        return response;
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable Long id ){
        /*utilizamos @PathVariable para poder obtener el id*/

        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.buscarPorId(id);

        return  response;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody CategoriaModel categoriaModel){
        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.crearCategoria(categoriaModel);
        return response;

    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody CategoriaModel categoriaModel, @PathVariable Long id){
    ResponseEntity<CategoriaResponseRest> response = iCategoriaService.actualizar(categoriaModel, id);
    return response;

    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> eliminarPorId(@PathVariable Long id ){
        ResponseEntity <CategoriaResponseRest> response = iCategoriaService.eliminarPorId(id);
        return response;

    }





}
