import PlaceDetail from "../components/enjoyHome/PlaceDetail/PlaceDetail";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Footer from "../components/common/Footer";
import PlaceLikeButton from "../components/enjoyHome/PlaceDetail/PlaceCo/PlaceLikeButton";
import { PlaceDetailPageWrapper } from "../styles/SPlaceDetailPage";

const PlaceDetailPage = () => {
    return (
        <PlaceDetailPageWrapper>
            <EnjoyHeader pageName="장소 상세"  />
            <PlaceDetail />
            <PlaceLikeButton />
            <Footer />
        </PlaceDetailPageWrapper>
    )
}

export default PlaceDetailPage;