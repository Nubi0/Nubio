import { CustomCourseItemWrapper, RepresentativeImg, BookMarkImg, TitleWrapper } from "../../../../styles/SEnjoyHomePage";

const CustomCourseItem = ({ value }: CourseProps) => {
    const {title, favorite_flag, like_count, place_list} = value;
    const bookMarkUrl = process.env.PUBLIC_URL + '/assets/star.svg';
    const bookMarkFillUrl = process.env.PUBLIC_URL + '/assets/starFill.svg';
    const likeUrl = process.env.PUBLIC_URL + '/assets/heart.png';

    return(
        <CustomCourseItemWrapper>
            <RepresentativeImg src={place_list[0].img_url} alt="" />
            <TitleWrapper>
                <div>{title}</div>
                <BookMarkImg src={ favorite_flag ? bookMarkFillUrl : bookMarkUrl} alt="" />
            </TitleWrapper>
            <div>
                <img src={likeUrl} alt="" />
                <span>{like_count}</span>
            </div>
        </CustomCourseItemWrapper>
    );
}

export default CustomCourseItem;