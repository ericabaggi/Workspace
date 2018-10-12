package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersist {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
			Professor professor = new Professor();
			professor.setName("fzefzen");
			Student s = new Student();
			Student s2 = new Student();
			Student s3 = new Student();
			// je sette dans la classe qui est maître de la relation et je persiste le
			// professeur et le student. => OK fonctionne. Pour persister que celui qui a la clé primaire il
			// faut ajouter la cascade 

			s.setName("ezfhjze");
			s.setProfessor(professor);
			s2.setName("fzefzea");
			s2.setProfessor(professor);
			s3.setName("fzefza");
			s3.setProfessor(professor);

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
