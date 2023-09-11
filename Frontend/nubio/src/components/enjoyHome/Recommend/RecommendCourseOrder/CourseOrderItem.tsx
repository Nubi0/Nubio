import { CourseOrderItemWrapper, LeftSide, Order, RightSide } from "../../../../styles/SRecommendPage";

type OrderItemProps = {
    index: number
}

const CourseOrderItem = ({index}: OrderItemProps) => {
    const eating = process.env.PUBLIC_URL + '/assets/eating.png';
    const cafe = process.env.PUBLIC_URL + '/assets/cafe.png';
    const movie = process.env.PUBLIC_URL + '/assets/movie.png';
    const boardgame = process.env.PUBLIC_URL + '/assets/boardgame.png';
    const walking = process.env.PUBLIC_URL + '/assets/walking.png';
    return(
        <CourseOrderItemWrapper>
            <LeftSide>
                <Order>{index}</Order>
                <img src={eating} alt="" />
                <img src={cafe} alt="" />
                <img src={movie} alt="" />
                <img src={boardgame} alt="" />
                <img src={walking} alt="" />
            </LeftSide>
            <RightSide>
                X
            </RightSide>
        </CourseOrderItemWrapper>
    );
}

export default CourseOrderItem;