package demo.multiple.datasources.primary.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.multiple.datasources.primary.entity.FirstTableEntity;

@Repository
public interface FirstTableRepo extends CrudRepository<FirstTableEntity, Integer> {

}
