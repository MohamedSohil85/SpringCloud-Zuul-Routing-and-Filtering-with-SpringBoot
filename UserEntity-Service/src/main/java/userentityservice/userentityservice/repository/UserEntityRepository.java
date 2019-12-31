package userentityservice.userentityservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import userentityservice.userentityservice.model.UserEntity;

import java.util.Optional;


@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity,Long> {

public Optional<UserEntity>findByName(String name);
public Optional<UserEntity>findByRole(String role);
}
