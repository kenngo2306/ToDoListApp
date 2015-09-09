CREATE TABLE TodoUser
(
  user_id INTEGER PRIMARY KEY,
  user_name VARCHAR(50),
  user_password VARCHAR(50)
);

CREATE TABLE TodoItem
(
  item_id INTEGER PRIMARY KEY,
  description VARCHAR(200),
  due_date DATE,
  completed_date DATE,
  status_code VARCHAR(10),
  item_priority NUMBER(1)
);

ALTER TABLE ToDoItem ADD user_id INTEGER REFERENCES TodoUser(user_id);
CREATE SEQUENCE SEQ_TODOUSER;
CREATE SEQUENCE SEQ_TODOITEM;

