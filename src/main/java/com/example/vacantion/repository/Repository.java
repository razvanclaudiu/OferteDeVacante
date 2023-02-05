package com.example.vacantion.repository;

import com.example.vacantion.domain.Entity;
import com.example.vacantion.repository.RepositoryException;

import java.util.Map;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */
public interface Repository<ID,E extends Entity<ID>> {

    /**
     *  returns entity
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     *          or null - if there is no entity with the given id
     * @throws IllegalArgumentException
     *                  if id is null.
     */
    E getEntity(ID id);

    /**
     * returns all entities
     * @return all entities
     */
    Map<ID,E> getAll();

    /**
     *  adds the entity to the repository
     * @param entity
     *         entity must be not null
     * @throws RepositoryException
     *            if the entity is not valid
     * @throws IllegalArgumentException
     *             if the given entity is null.     *
     */
    void add(E entity) throws RepositoryException;

    /**
     *  removes the entity with the specified id
     * @param id
     *          id must be not null
     * @throws RepositoryException
     *                   if the given id already exists.
     */
    void remove(ID id) throws RepositoryException;

    /**
     *  updates the entity to the repository
     * @param entity
     *         entity must be not null
     * @throws RepositoryException
     *            if the entity is not valid
     * @throws IllegalArgumentException
     *             if the given entity is null.     *
     */
    void update(E entity) throws RepositoryException;
}
