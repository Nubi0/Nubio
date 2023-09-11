import {
  PlaceInfoWrapper,
  PlaceName,
  PlaceAddress,
  PlaceHome,
  PlaceNumber,
} from "../../../../styles/SSearchBar";

type infoProps = {
  addres_name: string;
  phone: string;
  place_name: string;
  place_url: string;
};

const PlaceInfo = ({ info }: { info: infoProps }) => {
  // 이름 클릭시 상세보기
  const detailItem = () => {};
  return (
    <PlaceInfoWrapper>
      <PlaceName>{info.place_name}</PlaceName>
      <PlaceAddress>{info.addres_name}</PlaceAddress>
      <PlaceNumber>{info.phone}</PlaceNumber>
      <PlaceHome href={info.place_url}>홈페이지</PlaceHome>
    </PlaceInfoWrapper>
  );
};

export default PlaceInfo;
