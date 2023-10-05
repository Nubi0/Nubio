import { useState } from 'react';
import axios from "axios";
import { CustomCouresListWrapper } from "../../../../styles/SEnjoyHomePage";
import CustomCourseItem from "./CustomCourseItem";
import { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { setCourseList } from "../../../../redux/slice/EnjoySlice";

const CustomCourseList = () => {
    const dispatch = useDispatch();
    const itemList = useSelector((state: any) => state.enjoy.courseList);
    const [pos, setPos] = useState<any>(null);
    useEffect(() => {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                setPos({
                    latitude: position.coords.latitude,
                    longitude: position.coords.longitude
                })
            }
        )
        axios.post('https://nubi0.com/enjoy/v1/enjoy', {latitude: pos.latitude, longitude: pos.longitude})
            .then((res) => {
                dispatch(setCourseList(res.data.data.course_list));
            })
            .catch((err) => {
                console.error(err);
            })
        
    }, [])
    return(
        <CustomCouresListWrapper>
            {itemList.map((value: CourseItem, index: number) => (
                <CustomCourseItem value={value} key={index} />
            ))}
        </CustomCouresListWrapper>
    );
}

export default CustomCourseList;