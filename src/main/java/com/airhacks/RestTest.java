package com.airhacks;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/test")
public class RestTest {

  @Resource(name="test", lookup = "jdbc/postgres/portal")
  private DataSource ds;

  @PersistenceContext(unitName = "primary", name = "primary")
  private EntityManager em;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Person get(){
    Person p = new Person("name", 1);
    em.persist(p);

    try {
      Connection c = ds.getConnection();
      Statement s = c.createStatement();
      ResultSet rs = s.executeQuery("select * from funcionario where chave='F3445038'");
      while (rs.next()) {
        System.out.println(rs.getString(1) + "- " + rs.getString(2));
      }
      c.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    JsonObject j =Json.createObjectBuilder()
        .add("name", "julia")
        .build();

    return p;
  }
}
