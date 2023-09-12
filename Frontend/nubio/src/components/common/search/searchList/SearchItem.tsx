import { SearchItemWrapper } from "../../../../styles/SSearchBar";
import SearchInfo from "./SearchInfo";
import PlacePhoto from "./PlacePhoto";
import PlaceReview from "./PlaceReview";

const SearchItem = ({ place }: placeProps) => {
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
    <SearchItemWrapper>
      <SearchInfo info={info} />
      <div>
        <PlacePhoto photo={photo} />
        <PlaceReview review={review} />
      </div>
    </SearchItemWrapper>
  );
};

export default SearchItem;
