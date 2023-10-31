package com.enjoyservice.domain.node.entity;

import com.enjoyservice.domain.node.entity.type.Point;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@NoArgsConstructor
@Document("node")
public class Node {

    @MongoId
    private String id;

    @Field(name = "coursePk")
    private Long coursePk;

    @Field(name = "nodeList")
    private List<Point> nodeList;

    @Field(name = "time")
    private Integer time;

    @Field(name = "type")
    private String type;

    @Field(name = "distance")
    private Integer distance;

    @Builder
    public Node(String id, Long coursePk, List<Point> nodeList, Integer time, String type, Integer distance) {
        this.id = id;
        this.coursePk = coursePk;
        this.nodeList = nodeList;
        this.time = time;
        this.type = type;
        this.distance = distance;
    }

    public static Node of(Long coursePk, List<Point> nodeList, Integer time, String type, Integer distance) {
        return Node.builder()
                .coursePk(coursePk)
                .nodeList(nodeList)
                .time(time)
                .type(type)
                .distance(distance).build();
    }
}
