import { LocationListWrapper } from "../../../../styles/SMyCoursePage";
import LocationItem from "./LocaiontItem";

const LocationList = () => {
    const locations = [
        {
            name: 'SEOUL',
            url: process.env.PUBLIC_URL + '/assets/city/seoul.jpg'
        },
        {
            name: 'DAEGU',
            url: process.env.PUBLIC_URL + '/assets/city/gumi.jpg'
        },
        {
            name: 'GYEONGBUK',
            url: process.env.PUBLIC_URL + '/assets/city/daejeon.jpg'
        },
        {
            name: 'BUSAN',
            url: process.env.PUBLIC_URL + '/assets/city/gwangju.jpg'
        },
        {
            name: 'DAEJEON',
            url: process.env.PUBLIC_URL + '/assets/city/busan.jpg'
        },
        {
            name: 'GWANGJU',
            url: process.env.PUBLIC_URL + '/assets/city/gwangju.jpg'
        }
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