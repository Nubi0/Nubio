import { CourseButtonWrapper, ButtonDivider } from "../../../../styles/SCourseDeatilPage";

const CourseButton = () => {
    const heart = process.env.PUBLIC_URL + '/assets/heart-outline.svg';
    const share = process.env.PUBLIC_URL + '/assets/share.svg';
    return(
        <CourseButtonWrapper>
            <div>
                <img src={heart} alt="" />
                <button>찜하기</button>
            </div>
            <ButtonDivider />
            <div>
                <img src={share} alt="" />
                <button>공유하기</button>
            </div>
        </CourseButtonWrapper>
    );
}

export default CourseButton;