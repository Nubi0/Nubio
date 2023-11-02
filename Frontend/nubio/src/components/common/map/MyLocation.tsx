import { MoveMyLocation } from "../../../styles/SMap";

// import { MoveMyLocation } from "@styles/SMap";

const MyLocation = () => {
  const myLocationIcon =
    process.env.PUBLIC_URL + "./assets/search/myLocation.png";
  const moveMyLocation = () => {
    window.map.setCenter(
      new window.kakao.maps.LatLng(window.myLatitude, window.myLongitude),
    );
    // 현재 위치에 마커를 표시
    const marker = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(window.myLatitude, window.myLongitude),
    });
    marker.setMap(window.map); // 마커를 지도에 표시
  };
  return (
    <MoveMyLocation
      onClick={moveMyLocation}
      src={myLocationIcon}
      alt="내 위치"
    ></MoveMyLocation>
  );
};

export default MyLocation;
