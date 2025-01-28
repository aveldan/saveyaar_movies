package com.saveyaar.saveyaar_movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saveyaar.saveyaar_movies.model.UserPreference;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer>{

    @Query("SELECT new com.saveyaar.saveyaar_movies.DTO.UserPreference(up) " +
            "FROM UserPreference up " +
            "WHERE up.user_id = :user_id")
    com.saveyaar.saveyaar_movies.DTO.UserPreference findDTOByUserId(@Param("user_id") int user_id);

    @Query("SELECT up FROM UserPreference up WHERE up.user_id = :user_id")
    UserPreference findByUserId(@Param("user_id") int user_id);

    @Query("SELECT up FROM UserPreference up WHERE up.device_token = :device_token")
    UserPreference findByDeviceToken(@Param("device_token") String device_token);
}
