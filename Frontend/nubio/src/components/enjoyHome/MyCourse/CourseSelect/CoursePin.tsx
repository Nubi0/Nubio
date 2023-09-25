
const CoursePin = ({value}: {value: any}) => {
    const url: Record<string, string> = {
        FD6 : process.env.PUBLIC_URL + '/assets/marker/eatM.png',
        CT1 : process.env.PUBLIC_URL + '/assets/marker/movieM.png',
        AD5 : process.env.PUBLIC_URL + '/assets/marker/hotel.png',
        AT4 : process.env.PUBLIC_URL + '/assets/marker/walkingM.png',
        CE7 : process.env.PUBLIC_URL + '/assets/marker/cafeM.png',
    }
    const categoryCode = value.category_group_code;

    const imageUrl = url[categoryCode];
    return(
        <>
            <img src={imageUrl} alt="사진" />
        </>
    )
}

export default CoursePin;