import {
  DetailPlaceAddress,
  DetailPlaceHome,
  DetailPlaceInfoWrapper,
  DetailPlaceName,
  DetailPlaceNumber,
} from "../../../../styles/SSearchBar";

const DetailSearchInfo = ({ info }: infoProps) => {
  return (
    <DetailPlaceInfoWrapper>
      <DetailPlaceName>{info.place_name}</DetailPlaceName>
      <DetailPlaceAddress>{info.addres_name}</DetailPlaceAddress>
      <DetailPlaceNumber>{info.phone}</DetailPlaceNumber>
      <DetailPlaceHome href={info.place_url}>홈페이지</DetailPlaceHome>
    </DetailPlaceInfoWrapper>
  );
};

export default DetailSearchInfo;
