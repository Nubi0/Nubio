package com.safeservice.api.path.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OsrmDto {

    private List<Waypoints> waypoints;
    private List<Routes> routes;
    private String code;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Waypoints {
        public List<Double> location;
        public String name;
        public double distance;
        public String hint;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Routes {
        public double distance;
        public int duration;
        public int weight;
        public String weight_name;
        public List<Legs> legs;
        public String geometry;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Legs {
        public double distance;
        public int duration;
        public int weight;
        public String summary;
        public List<Steps> steps;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Steps {
        public double distance;
        public double duration;
        public double weight;
        public List<Intersections> intersections;
        public String name;
        public String driving_side;
        public String ref;
        public String mode;
        public Maneuver maneuver;
        public String geometry;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Intersections {
        public List<Double> location;
        public List<Integer> bearings;
        public List<Boolean> entry;
        public int in;
        public int out;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Maneuver {
        public String type;
        public String modifier;
        public List<Double> location;
        public int bearing_before;
        public int bearing_after;
    }


}
