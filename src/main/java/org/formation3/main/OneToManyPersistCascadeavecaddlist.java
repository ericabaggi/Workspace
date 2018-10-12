package org.formation3.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersistCascadeavecaddlist {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		Professor p = null;
		try {
			trx.begin();
			// 1 ) Si j'affecte un étudiant à un autre professeur cela fonctionne
//			Professor p2 = em.find(Professor.class, 9L);
//			Student s1= em.find(Student.class, 6L);
//			s1.setProfessor(p2);
			// Si j'affecte un nouveau professeur à un étudiant par contre comme il n'est
			// pas maitre de la relation il ne va pas gérer les clefs secondaires
			p = em.find(Professor.class, 4L);
			Student s2 = em.find(Student.class, 1L);
			p.getStudents().add(s2);
//			 p.getstudent ne fonctionne pas il faut que je crée une méthode addStudent
//			 dans Profesor. qui ajoute à la liste chaque étudiant et qui ajoute à chaque
//			 étudiant le professeur
			p.addStudent(s2);

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
