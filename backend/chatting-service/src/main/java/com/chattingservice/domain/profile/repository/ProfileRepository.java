package com.chattingservice.domain.profile.repository;

import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByParticipant(Participant participant);

}
