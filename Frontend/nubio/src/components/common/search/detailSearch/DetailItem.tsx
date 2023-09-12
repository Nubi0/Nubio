import { BackButton, DetailItemWrapper } from "../../../../styles/SSearchBar";
import DetailPlacePhoto from "./DetailPlacePhoto";
import DetailPlaceReview from "./DetailPlaceReview";
import DetailSearchInfo from "./DetailSearchInfo";

const DetailItem = ({ place, placeList }: placeProps & placeListProps) => {
  const info = {
    addres_name: place?.address_name,
    phone: place?.phone,
    place_name: place?.place_name,
    place_url: place?.place_url,
  };
  const photo = {
    url: place?.img_url,
  };
  const review = {
    score: 3.0,
    count: 8,
  };

  return (
    <DetailItemWrapper>
      <DetailPlacePhoto photo={photo} />
      <DetailSearchInfo info={info} />
      <DetailPlaceReview review={review} />
      <BackButton onClick={placeList}>x</BackButton>
    </DetailItemWrapper>
  );
};

export default DetailItem;
