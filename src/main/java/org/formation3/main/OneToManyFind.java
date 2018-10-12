package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;

public class OneToManyFind {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();

			Professor p = em.find(Professor.class, 2L);
			System.out.println("hello");
			System.out.println(p.getStudents());
			trx.commit();
			// on est en configuration Lazy donc em.find(professeur) ne va pas récupérer la
			// liste de tous les étudiants si je ne lui demande pas

		} catch (Exception e) {
			if (trx != null)
				trx.rollback();
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}

}
