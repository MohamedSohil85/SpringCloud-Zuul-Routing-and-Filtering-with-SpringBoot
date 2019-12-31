package userentityservice.userentityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import userentityservice.userentityservice.exception.UserNotFoundException;
import userentityservice.userentityservice.model.UserEntity;
import userentityservice.userentityservice.repository.UserEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserEntityController {
    @Autowired
    private UserEntityRepository repository;

    @RequestMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List getUsers() throws UserNotFoundException {
        List<UserEntity> userEntityList = (List) repository.findAll();
        if (userEntityList.isEmpty()) {
            throw new UserNotFoundException("no Users are found !");
        }
        return userEntityList;
    }

    @RequestMapping(value = "/getUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        Optional<UserEntity> optionalUserEntity = repository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(optionalUserEntity.get(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/lookForUserByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<UserEntity> lookForUserByName(@PathVariable("name") String name) throws UserNotFoundException {
        List<UserEntity> userEntityList = getUsers();
        return userEntityList.stream()
                .filter(userEntity -> userEntity.getName()
                        .startsWith(name, 0)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/saveNewUser", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity addNewUser(@RequestBody UserEntity user) {
        List<UserEntity> userEntityList = (List) repository.findAll();
        for (UserEntity userEntity : userEntityList) {
            if (userEntity.getName().equals(user.getName()))
                if (userEntity.getRole().equals(user.getRole()))
                    if (userEntity.getEmail().equals(user.getEmail()))
                        return new ResponseEntity(HttpStatus.FOUND);
        }
        return new ResponseEntity(repository.save(user), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/deleteUserByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity deleteUserByName(@PathVariable("name") String name) throws UserNotFoundException{
        List<UserEntity> userEntityList = (List) repository.findAll();
        return repository.findByName(name).map(userEntity -> {
            repository.delete(userEntity);
            return new ResponseEntity("User with Name :" + name+"has deleted", HttpStatus.OK);

        }).orElseThrow(()->new UserNotFoundException("User with Name "+name+" not found !"));
    }
    @RequestMapping(value = "/findUserByRole/{role}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public UserEntity findUserbyHisRole(@PathVariable("role")String role) throws UserNotFoundException{
        Optional<UserEntity>optionalUserEntity=repository.findByRole(role);
        if(!optionalUserEntity.isPresent()){
             new UserNotFoundException("User with this Role "+role+"cannot find");
        }
        return optionalUserEntity.get();
    }
}