package com.books.backend.apirestbooks.model.dao;

import com.books.backend.apirestbooks.model.CategoriaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDao extends CrudRepository <CategoriaModel, Long>{


}
