package demo.multiple.datasources.primary.entity;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="first_table")
public class FirstTableEntity {

	@Id
	private Integer id;
	@Column
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static FirstTableEntity createNew(){
		FirstTableEntity entity = new FirstTableEntity();
		Random r = new Random();
		entity.setId(r.nextInt());
		entity.setName("name_");
		return entity;
	}
}
