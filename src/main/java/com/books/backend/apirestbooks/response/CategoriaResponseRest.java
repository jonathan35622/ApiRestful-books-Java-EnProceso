package com.books.backend.apirestbooks.response;

public class CategoriaResponseRest extends ResponseRest{
    private CategoriaResponse categoriaResponse = new CategoriaResponse();

    public CategoriaResponse getCategoriaResponse() {
        return categoriaResponse;
    }

    public void setCategoriaResponse(CategoriaResponse categoriaResponse) {
        this.categoriaResponse = categoriaResponse;
    }
}

/*
* ResponseRest es el objeto final tiene toda la estructura que queremos devolver
* como extiende de ResponseRest tiene acceso al setMetadata y al getMetadata
* */