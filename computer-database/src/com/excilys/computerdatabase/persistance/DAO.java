package com.excilys.computerdatabase.persistance;

public abstract class DAO<T> {
	
	/**
	 * Permet de récupérer un objet DAO via son id
	 * @param id
	 * @return
	 */
	public abstract T find (int id);
	
	/**
	 * Exécute une requête CREATE : permet d'ajouter un tuple dans la base de données
	 * @param obj L'objet à insérer dans la base
	 * @return T Retourne l'objet ainsi créé
	 */
	public abstract T create(T obj);
	
	/**
	 * Exécute une requête UPDATE : permet de mettre à jour un tuple
	 * @param obj L'objet à mettre à jour dans la table
	 * @return T Retourne l'objet ainsi modifié
	 */
	public abstract T update(T obj);
	
	/**
	 * Exécute une requête DELETE : permet la suppression d'un tuple
	 * @param obj L'objet à supprimer
	 */
	public abstract void delete(T obj);
}
