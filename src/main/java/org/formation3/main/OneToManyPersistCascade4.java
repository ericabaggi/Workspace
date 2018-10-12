package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersistCascade4 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
			Professor professor = new Professor();
			professor.setName("loui");
			Student s = new Student();
			Student s2 = new Student();
			Student s3 = new Student();

			s.setName("laure");
			s2.setName("clara");
			s3.setName("laura");
			
// avec la méthode add + cascade + set student => foncionne
			professor.addStudent(s3);
			professor.addStudent(s2);
			professor.addStudent(s);

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
