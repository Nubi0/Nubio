import { PlaceItemWrapper } from "../../../styles/SLikePage";
import PlaceInfo from "./PlaceInfo";
import PlacePhoto from "./PlacePhoto";
import PlaceReview from "./PlaceReview";

type placeProps = {
  place: {
    id: string;
    address_name: string;
    category_group_code: string;
    category_group_name: string;
    phone: string;
    place_name: string;
    place_url: string;
    img_url: string;
    road_address_name: string;
    x: string;
    y: string;
  };
};

const PlaceItem = ({ place }: placeProps) => {
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
    <PlaceItemWrapper>
      <PlaceInfo info={info} />
      <div>
        <PlacePhoto photo={photo} />
        <PlaceReview review={review} />
      </div>
    </PlaceItemWrapper>
  );
};

export default PlaceItem;
