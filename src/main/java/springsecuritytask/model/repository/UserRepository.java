package springsecuritytask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springsecuritytask.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // 중복 이메일 확인
    boolean existsByUseremail(String useremail);

    //
    UserEntity findByUseremail(String useremail);
}
