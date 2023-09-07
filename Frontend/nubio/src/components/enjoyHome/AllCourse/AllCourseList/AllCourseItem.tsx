import { AllCourseItemWrapper } from "../../../../styles/SAllCoursePage";
import { BigImg, DetailWrapper } from "../../../../styles/SAllCoursePage";
import { HeadContent, Title, CourseTag, FavImg, PlaceImg, PlaceItem, PlaceName, PlaceListWrapper } from "../../../../styles/SAllCoursePage";

const AllCourseItem = ({value} : CourseProps) => {
    const {title, favorite_flag, course_tags, place_list} = value;
    const starFill = process.env.PUBLIC_URL + '/assets/starFill.svg';
    const star = process.env.PUBLIC_URL + '/assets/star.svg';
    return(
        <AllCourseItemWrapper>
            <BigImg src={place_list[0].img_url} />
            <DetailWrapper>
                <HeadContent>
                    <Title>{title}</Title>
                    {course_tags.map((value:string, index:number) => {
                        return(
                        <CourseTag key={index}>{value}</CourseTag>
                        );
                    })}
                    <FavImg src={favorite_flag ? starFill : star} />
                </HeadContent>
                <PlaceListWrapper>
                {place_list.slice(1).map((value:PlaceItem, index: number) => {
                    return(
                    <PlaceItem key={index}>
                        <PlaceImg src={value.img_url} />
                        <PlaceName>{value.place_name}</PlaceName>
                    </PlaceItem>
                    );
                })}
                </PlaceListWrapper>
            </DetailWrapper>
        </AllCourseItemWrapper>
    );
}

export default AllCourseItem;