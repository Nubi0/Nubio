package com.chattingservice.domain.room.common;

import java.util.UUID;

public class RoomIdCreator {
    public static String createRoomId(){
        return UUID.randomUUID().toString();
    }
}
