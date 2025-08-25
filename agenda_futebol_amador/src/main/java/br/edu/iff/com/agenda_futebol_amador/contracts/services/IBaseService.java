package br.edu.iff.com.agenda_futebol_amador.contracts.services;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T entity);
    void deleteById(Long id);
}