import axios from 'axios';
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
} from '../../../../styles/SAllCoursePage';

const AllCourseItem = ({ value }: { value: any }) => {
  const { title, favorite_flag, course_tags, place_list } = value;
  const starFill = process.env.PUBLIC_URL + '/assets/starFill.svg';
  const star = process.env.PUBLIC_URL + '/assets/star.svg';
  const noImage = process.env.PUBLIC_URL + '/assets/noImage.png';
  const handleFav = () => {
    axios
      .post(`https://nubi0.com/enjoy/v1/enjoy/course/like/${value.course_id}`)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  };
  return (
    <AllCourseItemWrapper>
      <BigImg src={place_list[0].img_url === 'not exist' ? noImage : place_list[0].img_url} />
      <DetailWrapper>
        <HeadContent>
          <Title>{title}</Title>
          {course_tags.map((value: string, index: number) => {
            return <CourseTag key={index}>{value}</CourseTag>;
          })}
          <FavImg src={favorite_flag ? starFill : star} onClick={handleFav} />
        </HeadContent>
        <PlaceListWrapper>
          {place_list.slice(1).map((value: PlaceItem, index: number) => {
            return (
              <PlaceItem key={index}>
                <PlaceImg src={value.img_url === 'not exist' ? noImage : value.img_url} />
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
