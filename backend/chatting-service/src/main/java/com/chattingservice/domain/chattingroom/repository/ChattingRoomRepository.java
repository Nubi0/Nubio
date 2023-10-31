package com.chattingservice.domain.chattingroom.repository;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroom.entity.constant.RoomType;
import com.chattingservice.domain.chattingroom.entity.type.RiName;
import com.chattingservice.domain.chattingroom.entity.type.SggName;
import com.chattingservice.domain.chattingroom.entity.type.SidoName;
import com.chattingservice.domain.chattingroom.entity.type.UmdName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    Optional<ChattingRoom> findById(Long id);

    boolean existsById(Long id);

    List<ChattingRoom> findBySidoName(SidoName sidoName);

    List<ChattingRoom> findBySidoNameAndSggName(SidoName sidoName, SggName sggName);

    List<ChattingRoom> findBySidoNameAndSggNameAndUmdName(SidoName sidoName, SggName sggName, UmdName umdName);

    List<ChattingRoom> findBySidoNameAndSggNameAndUmdNameAndRiName(SidoName sidoName, SggName sggName, UmdName umdName, RiName riName);

    @Query("select cr " +
            "from ChattingRoom cr " +
            "left join fetch  cr.participants p " +
            "where p.id = :participantId"
    )
    List<ChattingRoom> findMyRoomsByParticipantsId(Long participantId);


    @Query("select cr " +
            "from ChattingRoom cr " +
            "left join fetch  cr.participants p " +
            "where p.memberId = :memberId "
            + "AND p.active.value = true "
    )
    List<ChattingRoom> findMyRoomsByMemberId(String memberId);

    @Query("select cr " +
            "from ChattingRoom cr " +
            "left join fetch  cr.participants p " +
            "where cr.id = :id " +
            "AND p.memberId = :memberId"
    )
    Optional<ChattingRoom> findByIdAndParticipantMemberId(Long id, String memberId);

    @Query("select cr " +
            "from ChattingRoom cr " +
            "left join fetch  cr.participants p " +
            "where cr.id = :id " +
            "AND p.id = :participantId"
    )
    Optional<ChattingRoom> findByIdAndParticipantId(Long id, String participantId);

    @Query("select cr " +
            "from ChattingRoom cr " +
            "left join fetch  cr.participants p " +
            "where cr.roomType = 'DM' " +
            "AND p.memberId = :creator " +
            "AND p.memberId = :receiver"
    )
    ChattingRoom matchDmParticipants(String creator, String receiver);


}
