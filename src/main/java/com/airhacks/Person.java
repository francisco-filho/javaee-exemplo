package com.airhacks;

import javax.persistence.*;

@Entity
@SequenceGenerator(sequenceName = "public.person_id_seq", name = "idGenerator")
public class Person {
  @Id
  @GeneratedValue(generator = "idGenerator", strategy = GenerationType.SEQUENCE)
  private int id;
  private String name;
  private int age;

  public Person() {}

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
