import { LocationListWrapper } from "../../../../styles/SMyCoursePage";
import LocationItem from "./LocaiontItem";

const LocationList = () => {
    const locations = [
        {
            name: '서울',
            url: process.env.PUBLIC_URL + '/assets/city/seoul.jpg'
        },
        {
            name: '구미',
            url: process.env.PUBLIC_URL + '/assets/city/gumi.jpg'
        },
        {
            name: '대전',
            url: process.env.PUBLIC_URL + '/assets/city/daejeon.jpg'
        },
        {
            name: '광주',
            url: process.env.PUBLIC_URL + '/assets/city/gwangju.jpg'
        },
        {
            name: '부울경',
            url: process.env.PUBLIC_URL + '/assets/city/busan.jpg'
        },
    ];

    return(
        <LocationListWrapper>
            {locations.map((location, index) => {
                return(
                    <LocationItem key={index} location={location} />
                )
            })}
        </LocationListWrapper>
    );
};

export default LocationList;