import { CustomCourseItemWrapper, RepresentativeImg, BookMarkImg, TitleWrapper } from "../../../../styles/SEnjoyHomePage";

const CustomCourseItem = ({ value }: CourseProps) => {
    const {title, favorite_flag, like_count, place_list} = value;
    const bookMarkUrl = process.env.REACT_APP_BOOKMARK_URL;
    const bookMarkFillUrl = process.env.REACT_APP_BOOKMARK_FILL_URL;
    const likeUrl = process.env.REACT_APP_LIKE_URL;

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