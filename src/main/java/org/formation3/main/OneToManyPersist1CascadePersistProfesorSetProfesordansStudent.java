package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersist1CascadePersistProfesorSetProfesordansStudent {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
			Professor professor = new Professor();
			professor.setName("laurel");
			Student s = new Student();
			Student s2 = new Student();
			Student s3 = new Student();
			// je sette dans la classe qui est maître de la relation et je persiste. La clé
			// secondaire n'est pas générée si je ne persiste professor, il manque le add
			s.setName("clara");
			s.setProfessor(professor);
			s2.setName("ophélie");
			s2.setProfessor(professor);
			s3.setName("nora");
			s3.setProfessor(professor);

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
