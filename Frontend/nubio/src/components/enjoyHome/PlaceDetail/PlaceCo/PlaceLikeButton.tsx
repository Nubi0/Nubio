import { PlaceLikeButtonWrapper } from "../../../../styles/SPlaceDetailPage";

const PlaceLikeButton = () => {
    const heart = process.env.PUBLIC_URL + '/assets/heart-outline.svg';

    return(
        <PlaceLikeButtonWrapper>
            <button>
                <img src={heart} alt="" />
                <span>찜하기</span>
            </button>            
        </PlaceLikeButtonWrapper>
    );
}

export default PlaceLikeButton;