import axios from "axios";
import {
  AllCourseItemWrapper,
  BigImg,
  CourseTag,
  DetailWrapper,
  FavImg,
  HeadContent,
  PlaceImg,
  PlaceItem,
  PlaceListWrapper,
  PlaceName,
  Title,
} from "@styles/SAllCoursePage";
import { useNavigate } from "react-router";

const AllCourseItem = ({ value }: { value: CourseItem }) => {
  const { title, favorite_flag, course_tags, place_list } = value;
  const starFill = process.env.PUBLIC_URL + "/assets/course/smallStarFill.svg";
  const star = process.env.PUBLIC_URL + "/assets/course/smallStar.svg";
  const noImage = process.env.PUBLIC_URL + "/assets/course/noImage.png";
  const navigate = useNavigate();
  const handleFav = () => {
    axios
      .post(
        process.env.REACT_APP_SERVER_URL +
          `/enjoy/v1/enjoy/course/favorite/${value.course_id}`
      )
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  };
  return (
    <AllCourseItemWrapper>
      <BigImg
        src={
          place_list.length > 0 &&
          (place_list[0].img_url === "not exist" ||
            place_list[0].img_url === "none")
            ? noImage
            : place_list.length > 0
            ? place_list[0].img_url
            : noImage
        }
      />

      <DetailWrapper>
        <HeadContent>
          <Title onClick={() => navigate(`/coursedetail/${value.course_id}`)}>
            {title}
          </Title>
          {course_tags.map((value: string, index: number) => {
            return <CourseTag key={index}>{value}</CourseTag>;
          })}
          <FavImg src={favorite_flag ? starFill : star} onClick={handleFav} />
        </HeadContent>
        <PlaceListWrapper>
          {place_list.slice(1).map((value: PlaceItem, index: number) => {
            return (
              <PlaceItem key={index}>
                <PlaceImg
                  src={
                    value.img_url === "not exist" ||
                    place_list[0].img_url === "none"
                      ? noImage
                      : value.img_url
                  }
                />
                <PlaceName>{value.place_name}</PlaceName>
              </PlaceItem>
            );
          })}
        </PlaceListWrapper>
      </DetailWrapper>
    </AllCourseItemWrapper>
  );
};

export default AllCourseItem;
