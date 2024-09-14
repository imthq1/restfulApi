package com.example.demo.service;

import com.example.demo.domain.DTO.*;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User save(User user) {
        return this.userRepository.save(user);
    }
    public void Delete(long id) {
         this.userRepository.deleteById(id);
    }
    public User findById(long id) {
        return this.userRepository.findById(id);
    }
    public ResultPaginationDTO findAll(Specification<User> spec
    , Pageable pageable) {
        Page<User> pageUsers = this.userRepository.findAll(spec,pageable);
        ResultPaginationDTO rs=new ResultPaginationDTO();
        Meta mt=new Meta();

        //FE gui len
        mt.setPage(pageable.getPageNumber()+1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageUsers.getTotalPages());
        mt.setTotal(pageUsers.getTotalElements());

        rs.setMeta(mt);

        List<ResUserDTO> listUser=pageUsers.getContent()
                .stream().map(iteam->new ResUserDTO(
                        iteam.getId(),
                        iteam.getEmail(),
                        iteam.getName(),
                        iteam.getGender(),
                        iteam.getAddress(),
                        iteam.getAge(),
                        iteam.getUpdatedAt(),
                        iteam.getCreatedAt()))
                .collect(Collectors.toList());

        rs.setResult(listUser);
        return rs;
    }

    public User DTOtoUser(LoginUserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    public User GetUserByUsername(String username) {
        return this.userRepository.getUserByEmail(username);
    }
    public CreateUserDTO createUserDTO(User user) {
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }
    public UpdateUserDTO updateUserDTO(User user) {
        UpdateUserDTO userDTO = new UpdateUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setAge(user.getAge());
        userDTO.setGender(user.getGender());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }


}
