package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.tag.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {

    @Query("select c " +
            "from Course c " +
            "left join fetch c.coursePlaceSequences cps " +
            "inner join fetch cps.place p " +
            "left join fetch p.images " +
            "where c.region = :region")
    List<Course> findAllByRegionFetchPlace(@Param("region") Region region, Pageable pageable);

    @Query("select distinct c " +
            "from Course c " +
            "left join fetch c.coursePlaceSequences cps " +
            "join fetch cps.place p " +
            "where c.region = :region")
    List<Course> findAllByRegionToModel(@Param("region") Region region);


    @Query("select c " +
            "from Course c " +
            "left join fetch c.coursePlaceSequences cps " +
            "join fetch cps.place p " +
            "where c = :course")
    List<Course> findPlacesByCourse(@Param("course") Course course);

    Long countAllByRegion(Region region);

    @Query("select t " +
            "from Course c " +
            "left join fetch CourseTag ct on c = ct.course " +
            "join fetch Tag t on ct.tag = t " +
            "where c = :course")
    List<Tag> findTagsByCourse(@Param("course") Course course);

    @Query("select cl, c from Course c " +
            "left join fetch CourseLike cl on c = cl.course " +
            "where c = :course")
    List<CourseLike> findCourseLikesByCourse(@Param("course") Course course);

    @Query("select cl, c " +
            "from Course c " +
            "left join fetch CourseLike cl on c = cl.course " +
            "where c.id = :courseId and cl.memberId = :memberId")
    Optional<CourseLike> findCourseLikesByCourseId(@Param("courseId") Long courseId, @Param("memberId") String memberId);

    @Query("select c, ct, t " +
            "from Course c " +
            "left join fetch CourseTag ct on c = ct.course " +
            "join fetch Tag t on ct.tag = t " +
            "where c.id = :courseId")
    List<Course> findCourseAndTagsByCourseId(@Param("courseId") Long courseId);

    @Query("select c "
            + "from Course c "
            + "left join fetch  c.courseTags ct  "
            + "inner join  Tag t on ct.tag = t "
            + "where t.id IN :courseTagIds "
            + "group by c.id "
            + "having count(c.id) >= :size"
    )
    Page<Course> findAllByCourseTags(@Param("courseTagIds") List<Long> courseTagIds, @Param("size") int size, Pageable pageable);

    @Query("select c "
            + "from Course c "
            + "left join fetch  c.courseTags ct  "
            + "inner join  Tag t on ct.tag = t "
            + "where t.id IN :courseTagIds "
            + "AND c.region = :region "
            + "group by c.id "
            + "having count(c.id) >= :size"
    )
    Page<Course> findAllByCourseTagsAndRegion(@Param("courseTagIds") List<Long> courseTagIds, @Param("size") int size, @Param("region") Region region, Pageable pageable);


    @Query("select p, cps, i " +
            "from Course c " +
            "left join fetch CoursePlaceSequence cps on c = cps.course " +
            "join fetch Place p on cps.place = p " +
            "left join fetch PlaceImage i on i.place = p " +
            "where c = :course")
    List<Place> findPlacesAndImageByCourse(@Param("course") Course course);

    @Query("select distinct c " +
            "from Course c " +
            "left join fetch c.courseTags ct " +
            "inner join fetch ct.tag t " +
            "left join fetch c.coursePlaceSequences cps " +
            "inner join fetch cps.place p " +
            "left join fetch p.images img " +
            "where c.memberId = :memberId")
    Page<Course> findMyCourses(@Param("memberId") String memberId, Pageable pageable);

    @Query("select c from Course c " +
            "left join fetch c.courseFavorites cf " +
            "where cf.memberId = :memberId ")
    Page<Course> findFavoriteCourseByMember(@Param("memberId") String memberId, Pageable pageable);

}
