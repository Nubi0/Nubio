package com.authenticationservice.domain.profile.repository;

import com.authenticationservice.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
