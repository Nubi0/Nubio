import {
  PlaceInfoWrapper,
  PlaceName,
  PlaceAddress,
  PlaceHome,
  PlaceNumber,
} from "../../../../styles/SSearchBar";

const SearchInfo = ({ info }: infoProps) => {
  return (
    <PlaceInfoWrapper>
      <PlaceName>{info.place_name}</PlaceName>
      <PlaceAddress>{info.addres_name}</PlaceAddress>
      <PlaceNumber>{info.phone}</PlaceNumber>
      <PlaceHome href={info.place_url}>홈페이지</PlaceHome>
    </PlaceInfoWrapper>
  );
};

export default SearchInfo;
