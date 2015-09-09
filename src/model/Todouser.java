package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TODOUSER database table.
 * 
 */
@Entity
@Table (name="TODOUSER", schema="TESTDB")
@NamedQuery(name="Todouser.findAll", query="SELECT t FROM Todouser t")
public class Todouser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TODOUSER_USERID_GENERATOR", sequenceName="SEQ_TODOUSER", schema="TESTDB", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TODOUSER_USERID_GENERATOR")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	//bi-directional many-to-one association to Todoitem
	@OneToMany(mappedBy="todouser")
	private List<Todoitem> todoitems;

	public Todouser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Todoitem> getTodoitems() {
		return this.todoitems;
	}

	public void setTodoitems(List<Todoitem> todoitems) {
		this.todoitems = todoitems;
	}

	public Todoitem addTodoitem(Todoitem todoitem) {
		getTodoitems().add(todoitem);
		todoitem.setTodouser(this);

		return todoitem;
	}

	public Todoitem removeTodoitem(Todoitem todoitem) {
		getTodoitems().remove(todoitem);
		todoitem.setTodouser(null);

		return todoitem;
	}

}