package model;

import java.io.Serializable;

import javax.persistence.*;


import java.util.Date;


/**
 * The persistent class for the TODOITEM database table.
 * 
 */
@Entity
@Table (name="TODOITEM", schema="TESTDB")
@NamedQuery(name="Todoitem.findAll", query="SELECT t FROM Todoitem t")
public class Todoitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TODOITEM_ITEMID_GENERATOR", sequenceName="SEQ_TODOITEM", schema="TESTDB", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TODOITEM_ITEMID_GENERATOR")
	@Column(name="ITEM_ID")
	private long itemId;

	@Temporal(TemporalType.DATE)
	@Column(name="COMPLETED_DATE")
	private Date completedDate;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;

	@Column(name="ITEM_PRIORITY")
	private int itemPriority;

	@Column(name="STATUS_CODE")
	private String statusCode;

	//bi-directional many-to-one association to Todouser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Todouser todouser;

	public Todoitem() {
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Date getCompletedDate() {
		return this.completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getItemPriority() {
		return this.itemPriority;
	}

	public void setItemPriority(int itemPriority) {
		this.itemPriority = itemPriority;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Todouser getTodouser() {
		return this.todouser;
	}

	public void setTodouser(Todouser todouser) {
		this.todouser = todouser;
	}

}