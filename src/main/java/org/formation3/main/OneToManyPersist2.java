package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersist2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
			Professor professor = new Professor();
			professor.setName("Alain");
			Student s = new Student();
			Student s2 = new Student();
			Student s3 = new Student();
			s.setName("Claude");

			s2.setName("Laura");

			s3.setName("Clara");
			// Cette fois ci je ne set pas le professeur a chaque student . j'ajoute juste à
			// la liste du professeur les éleves.Professeur n'est pas maitre de la relation
			// il ne va pas rajouter l'idprofesseur (clé secondaire) dans student SI JE NE
			// SET PAS LE PROFESSEUR a chque eleve

			professor.getStudents().add(s);
			professor.getStudents().add(s2);
			professor.getStudents().add(s3);
			em.persist(professor);
			em.persist(s);
			em.persist(s2);
			em.persist(s3);
			trx.commit();

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
