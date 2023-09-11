import { PlaceDetailInfoWrapper } from "../../../../styles/SPlaceDetailPage";
import PlaceMap from "./PlaceMap";
import PlaceReview from "./PlaceReview";

const PlaceDetailInfo = () => {
    return(
        <PlaceDetailInfoWrapper>
            <PlaceMap />
            <PlaceReview />
        </PlaceDetailInfoWrapper>
    );
};

export default PlaceDetailInfo;