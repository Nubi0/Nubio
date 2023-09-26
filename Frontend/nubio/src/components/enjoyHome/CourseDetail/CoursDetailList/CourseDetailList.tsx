import { CourseDetailListWrapper, ItemWrapper, DetailHr } from "../../../../styles/SCourseDeatilPage";
import CourseButton from "./CourseButton";
import CourseDetailItem from "./CourseDetailItem";
import { useEffect } from 'react';

const CourseDetailList = ({place_list}: {place_list: any[]}) => {
    return(
        <CourseDetailListWrapper>
            <DetailHr />
            <ItemWrapper>
                {place_list.map((place, index) => {
                    return(
                        <CourseDetailItem key={index} place={place} />
                    );
                })}
            </ItemWrapper>
            <CourseButton />
        </CourseDetailListWrapper>
    );
};

export default CourseDetailList;