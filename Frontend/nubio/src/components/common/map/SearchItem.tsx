import {
  DirectionButtonWrapper,
  ItemInfoWrapper,
  ItemNameWrapper,
  SearchItemWrapper,
} from "../../../styles/SKakaoMap";
import { placeType } from "../../../types/kakaoMaps";

const SearchItem = ({ place, index }: { place: placeType; index: number }) => {
  const onClickStart = () => {
    localStorage.setItem("startX", `${place.x}`);
    localStorage.setItem("startY", `${place.y}`);
    localStorage.setItem("startName", `${place.place_name}`);
  };
  const onClickEnd = () => {
    localStorage.setItem("endX", `${place.x}`);
    localStorage.setItem("endY", `${place.y}`);
    localStorage.setItem("endName", `${place.place_name}`);
  };

  return (
    <SearchItemWrapper>
      <ItemNameWrapper>
        <h5>
          {index + 1}. {place.place_name}
        </h5>
        <a id="homePage" href={place.place_url}>
          상세보기
        </a>
      </ItemNameWrapper>
      <ItemInfoWrapper>
        {place.road_address_name ? (
          <p className="address">{place.road_address_name}</p>
        ) : (
          <p className="address">{place.address_name}</p>
        )}
        <p className="tel">{place.phone}</p>
      </ItemInfoWrapper>
      <DirectionButtonWrapper>
        <button onClick={onClickStart}>출발</button>
        <button onClick={onClickEnd}>도착</button>
      </DirectionButtonWrapper>
    </SearchItemWrapper>
  );
};

export default SearchItem;
