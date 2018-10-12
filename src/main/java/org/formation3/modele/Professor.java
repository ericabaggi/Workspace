package org.formation3.modele;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	String name;
	// quand j'ai un seul paramètre ="" si j'ai plusieurs c'est comme un tableau{}
	@OneToMany(mappedBy = "professor", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	Set<Student> students = new HashSet<>();

	public Professor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student s) {
		students.add(s);
		s.setProfessor(this);

	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", students=" + students + "]";
	}

}
