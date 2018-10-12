package org.formation3.main;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.formation3.modele.Professor;
import org.formation3.modele.Student;

public class OneToManyPersistCascadepgetStudentLazyInitialisation {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		Professor p = null;
		try {
			trx.begin();

			p = em.find(Professor.class, 4L);
			Set<Student> students = p.getStudents();
			// je dois le solliciter .size pour qu'il fasse un select et qu'il aille me
			// chercher les �l�ves. Ensuite je peux les r�cup�rer
			students.size();

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
		System.out.println(p.getStudents());
		// Je n'arrive � r�cup�rer les �tudiants du professeur car la transaction est
		// ferm�e je ne peux pas la r�cup�rer
		// il ne peut plus le r�cup�rer
	}

}
