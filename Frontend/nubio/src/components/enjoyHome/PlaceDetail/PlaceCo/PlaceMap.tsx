import { useEffect } from 'react';
import { PlaceMapWrapper, MapWrapper, MapHeader } from "../../../../styles/SPlaceDetailPage";

declare global {
    interface Window {
      kakao: any;
    }
  }
  

const PlaceMap = () => {
    useEffect(() => {
        let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
        let options = {
            center: new window.kakao.maps.LatLng(33.450701, 126.570667),
            level: 3,
        };
        let map = new window.kakao.maps.Map(container, options);
    }, []);
    return(
        <PlaceMapWrapper>
            <MapHeader>주소주소</MapHeader>
            <MapWrapper id="map" />
        </PlaceMapWrapper>
    )
}

export default PlaceMap;