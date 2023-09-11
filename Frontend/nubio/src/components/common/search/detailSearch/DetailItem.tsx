import { DetailItemWrapper } from "../../../../styles/SSearchBar";
import PlacePhoto from "../searchList/PlacePhoto";
import PlaceReview from "../searchList/PlaceReview";
import PlaceInfo from "../searchList/SearchInfo";

const DetailItem = ({ place }: placeProps) => {
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
      <PlaceInfo info={info} />
      <div>
        <PlacePhoto photo={photo} />
        <PlaceReview review={review} />
      </div>
    </DetailItemWrapper>
  );
};

export default DetailItem;
