import { BackButton, DetailItemWrapper } from "../../../../styles/SSearchBar";
import PlacePhoto from "../searchList/PlacePhoto";
import PlaceReview from "../searchList/PlaceReview";
import SearchInfo from "../searchList/SearchInfo";

const DetailItem = ({ place, placeList }: placeProps & placeListProps) => {
  const info = {
    addres_name: place.address_name,
    phone: place.phone,
    place_name: place.place_name,
    place_url: place.place_url,
  };
  const photo = {
    url: place.img_url,
  };
  const review = {
    score: 3.0,
    count: 8,
  };
  return (
    <DetailItemWrapper>
      <SearchInfo info={info} />
      <div>
        <PlacePhoto photo={photo} />
        <PlaceReview review={review} />
      </div>
      <BackButton onClick={placeList}>뒤로가기</BackButton>
    </DetailItemWrapper>
  );
};

export default DetailItem;
