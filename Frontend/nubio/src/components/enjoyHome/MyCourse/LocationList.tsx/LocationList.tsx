import { LocationListWrapper } from "../../../../styles/SMyCoursePage";
import LocationItem from "./LocaiontItem";

const LocationList = () => {
    const locations = [
        {
            name: '서울',
            url: process.env.PUBLIC_URL + '/assets/seoul.jpg'
        },
        {
            name: '구미',
            url: process.env.PUBLIC_URL + '/assets/gumi.jpg'
        },
        {
            name: '대전',
            url: process.env.PUBLIC_URL + '/assets/daejeon.jpg'
        },
        {
            name: '광주',
            url: process.env.PUBLIC_URL + '/assets/gwangju.jpg'
        },
        {
            name: '부울경',
            url: process.env.PUBLIC_URL + '/assets/busan.jpg'
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