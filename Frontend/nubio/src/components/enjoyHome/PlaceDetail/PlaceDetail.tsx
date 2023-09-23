import { PlaceDetailWrapper } from "../../../styles/SPlaceDetailPage";
import PlaceDetailInfo from "./PlaceCo/PlaceDetailInfo";

const PlaceDetail = () => {
    const dummy1 = process.env.PUBLIC_URL + '/assets/dummy1.jpg';
    return(
        <PlaceDetailWrapper>
            <img src={dummy1} alt="" />
            <PlaceDetailInfo />
        </PlaceDetailWrapper>
    );
};

export default PlaceDetail;