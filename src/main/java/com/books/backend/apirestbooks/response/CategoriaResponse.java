package com.books.backend.apirestbooks.response;

import com.books.backend.apirestbooks.model.CategoriaModel;

import java.util.List;

public class CategoriaResponse {
    private List<CategoriaModel> categoriaModel;

    public List<CategoriaModel> getCategoriaModel() {
        return categoriaModel;
    }

    public void setCategoriaModel(List<CategoriaModel> categoriaModel) {
        this.categoriaModel = categoriaModel;
    }
}
