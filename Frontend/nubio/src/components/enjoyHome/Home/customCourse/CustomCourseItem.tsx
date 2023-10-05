import {
  CustomCourseItemWrapper,
  RepresentativeImg,
  BookMarkImg,
  TitleWrapper,
} from '../../../../styles/SEnjoyHomePage';
import { useNavigate } from 'react-router';

const CustomCourseItem = ({ value }: CourseProps) => {
  const { title, favorite_flag, place_list, course_id } = value;
  const bookMarkUrl = process.env.PUBLIC_URL + '/assets/smallStar.svg';
  const bookMarkFillUrl = process.env.PUBLIC_URL + '/assets/smallStarFill.svg';
  const url = place_list[0].img_url;
  const noUrl = process.env.PUBLIC_URL + '/assets/noImage.png';
  const navigate = useNavigate();
  console.log(title);
  console.log(favorite_flag);
  return (
    <CustomCourseItemWrapper>
      <RepresentativeImg
        src={place_list[0].img_url === 'not exist' ? noUrl : url}
        alt=""
        onClick={() => {
          navigate(`/coursedetail/${course_id}`);
        }}
      />
      <TitleWrapper>
        <div>{title}</div>
        <BookMarkImg src={favorite_flag ? bookMarkFillUrl : bookMarkUrl} alt="" />
      </TitleWrapper>
    </CustomCourseItemWrapper>
  );
};

export default CustomCourseItem;
