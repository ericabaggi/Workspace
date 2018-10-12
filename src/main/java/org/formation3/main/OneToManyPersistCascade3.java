package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersistCascade3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
			Professor professor = new Professor();
			professor.setName("fzefzef");
			Student s = new Student();
			Student s2 = new Student();
			Student s3 = new Student();
			s.setName("fzefze");
			s.setProfessor(professor);
			s2.setProfessor(professor);
			s3.setProfessor(professor);

			s2.setName("efzefze");

			s3.setName("efzefze");
			// Professeur n'est pas maitre de la relation il ne va pas rajouter
			// l'idprofesseur (clé secondaire) dans student
			// Pour qu'il rajoute la clé secondaire il faut setter le professeur par student

			// Le professeur n'est pas maitre de la relation donc si je persiste le
			// professeur sans faire un add de chaque student, les eleves ne vont pas être
			// ajoutés. Je peux pas par contre persister uniquement le student sans faire un
			// add et puisque c'est le maitre de la relation il va crée le professeur

			professor.getStudents().add(s);
			professor.getStudents().add(s2);
			professor.getStudents().add(s3);
			em.persist(professor);

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
